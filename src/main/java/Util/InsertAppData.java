package Util;

import Jsoup_app.APP;
import Jsoup_app.yyb;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InsertAppData {
    public static void InsertDB() throws ClassNotFoundException, SQLException, IOException {
        List<String> updateappList = new ArrayList<>();
        Dealtxt.ReadPkgName("src/PkgName.txt");

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
        for (String pkg : Dealtxt.pkglist) {
            app = yyb.yybspider(pkg);
            if (app.getFlag()) {
                PreparedStatement pstmt = null;//初始化动态SQL语句
                String update = "INSERT into pachong_result (download_count ,spell_name ,APP_name ,version_number ,file_name ,update_data,last_update_time,company_name,download_url,app_describe,package_name) VALUES(?,?,?,?,?,?,?,?,?,?,?) ";
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
                System.out.println(pstmt.toString());
                try{
                    System.out.println("更新状态：" + pstmt.executeUpdate());
                }catch (Exception e){
                    System.out.println(e);
                    System.out.println("更新异常");
                }

                updateappList.add(app.getAppname());


            }

            System.out.println("=======================================================");
        }
        stmt.close();
        conn.close();
    }

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        InsertAppData.InsertDB();
    }
}
