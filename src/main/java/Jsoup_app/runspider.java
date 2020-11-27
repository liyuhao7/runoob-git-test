package Jsoup_app;

import Jsoup_app.APP;
import Jsoup_app.yyb;
import Util.Dealtxt;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class runspider {

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        List<String> updateappList = new ArrayList<>();
        Dealtxt.ReadPkgName("src/PkgName.txt");
        Dealtxt.readTopApp("src/TopAppSort.txt");

        //连接147数据库
        System.out.println("正在连接147数据库...");
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://192.168.205.147:3306/appinfo?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        String username = "root";
        String password = "3/cgohl0z7zpYYdj1kA=";
        Connection conn = null;
        Class.forName(driver);
        conn = DriverManager.getConnection(url, username, password);
        Statement stmt = conn.createStatement();
        System.out.println("连接147数据库成功");

        //从txt文件中读取包名和name
        System.out.println("读取包名/爬取信息........");
        APP app = new APP();
        int sqlcounter;//用于标识数据库语句生效的行数

        for (String pkg : Dealtxt.pkglist) {
            app = yyb.yybspider(pkg);
            if (app.getFlag()) {
                //如果app的下载量是0，去其他应用商店爬取信息
                if (!app.getDownloadcounter().equals("0下载")) {

                    PreparedStatement pstmt = null;//初始化动态SQL语句
                    String update = "UPDATE pachong_result SET download_count = ?,spell_name = ?,APP_name = ?,version_number = ?,file_name = ?,update_data = ?,last_update_time = ?,company_name = ?,download_url = ?,app_describe = ? WHERE package_name = ? AND version_number!=?";
                    //把所有信息更新一次
                    pstmt = conn.prepareStatement(update);//预编译SQL语句

                    //设置动态字段
                    pstmt.setString(1, app.getDownloadcounter());
                    pstmt.setString(2, app.getSpellname());
                    pstmt.setString(3, app.getAppname());
                    pstmt.setString(4, app.getVersionnumber());
                    pstmt.setString(5, app.getFilename());
                    pstmt.setString(6, app.getUpdatedata());
                    pstmt.setString(7, app.getSqlupdatetime());
                    pstmt.setString(8, app.getCompanyname());
                    pstmt.setString(9, app.getDownloadurl());
                    pstmt.setString(10, app.getAppdescribe());
                    pstmt.setString(11, app.getPackagename());
                    pstmt.setString(12, app.getVersionnumber());
                    System.out.println(pstmt.toString());
                    sqlcounter = pstmt.executeUpdate();
                    System.out.println("更新状态：" + sqlcounter);
                    if (sqlcounter != 0) {
                        System.out.println("加入更新列表");
                        updateappList.add(app.getSpellname());
                    }
                } else {//豌豆荚应用商店
                    System.out.println("跳转到豌豆荚");

                }
            }


            System.out.println("=======================================================");
        }
        stmt.close();
        conn.close();
        System.out.println("数据库关闭，开始记录每日更新的app");

        //输出更新的APP
        java.util.Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String tt = dateFormat.format(now);//获取当日日期
        File file = new File("src/UpdateApp.txt");
        file.createNewFile();//直接覆盖掉旧文件
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(tt + "更新app的个数：" + updateappList.size() + "\n");

        //更新应用分级
        ArrayList<String > Alist = new ArrayList<>();
        ArrayList<String > Blist = new ArrayList<>();
        ArrayList<String > Clist = new ArrayList<>();
        ArrayList<String > nolist = new ArrayList<>();
//        String updateAppDesc = null;
        String updateAppName = null;
        for (int i = 0; i < updateappList.size(); i++) {
            updateAppName = updateappList.get(i);
            if (Dealtxt.AMap.containsKey(updateAppName)){
                Alist.add(Dealtxt.AMap.get(updateAppName));
            }else if (Dealtxt.BMap.containsKey(updateAppName)){
                Blist.add(Dealtxt.BMap.get(updateAppName));
            }else if (Dealtxt.CMap.containsKey(updateAppName)){
                Clist.add(Dealtxt.CMap.get(updateAppName));
            }else {
                nolist.add(updateAppName);
            }

        }
        bufferedWriter.write("A级更新应用如下：\n");
        for (String str:Alist
             ) {
            bufferedWriter.write(str+"\n");
        }
        bufferedWriter.write("B级更新应用如下：\n");
        for (String str:Blist
        ) {
            bufferedWriter.write(str+"\n");
        }
        bufferedWriter.write("C级更新应用如下：\n");
        for (String str:Clist
        ) {
            bufferedWriter.write(str+"\n");
        }
        bufferedWriter.write("name未对应更新应用如下：\n");
        for (String str:nolist
        ) {
            bufferedWriter.write(str+"\n");
        }
        bufferedWriter.flush();
        bufferedWriter.close();

    }
}
