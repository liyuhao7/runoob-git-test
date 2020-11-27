package Util;

import java.io.*;
import java.sql.*;

public class dianshangMysql {
    public static void main(String[] args) throws FileNotFoundException {

        File file1 = new File("src/淘宝.txt");
        File file2 = new File("src/淘宝.txt");
        File file3 = new File("src/京东.txt");

        BufferedReader bufferedReader1 = new BufferedReader(new FileReader(file1));
        BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file2));
        BufferedReader bufferedReader3 = new BufferedReader(new FileReader(file3));

        String str1 = null;
        String str2 = null;
        String str3 = null;



        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://192.168.91.9:3306/everdata_knowledge?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        String username = "root";
        String password = "EverSec99813!@#";
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            System.out.println("连接电商数据库成功");
            while ((str1 = bufferedReader1.readLine())!=null){
                PreparedStatement pstmt = null;//初始化动态SQL语句
                String update = "INSERT INTO http_product_rule(rule, top_category, product_name, shop_name, app_name, priority, effective) VALUES (?, ?, ?, ?, ?, ?, ?) ";
                //把所有信息更新一次
                pstmt = conn.prepareStatement(update);//预编译SQL语句

                //设置动态字段
                pstmt.setString(1, str1.split(",")[0]);
                pstmt.setString(2, str1.split(",")[1]);
                pstmt.setString(3, str1.split(",")[4]);
                pstmt.setString(4, str1.split(",")[5]);
                pstmt.setString(5, str1.split(",")[6]);
                pstmt.setInt(6, str1.split(",")[0].length());
                pstmt.setString(7, "1");

                System.out.println(pstmt.toString());
                try{
                    System.out.println("更新状态：" + pstmt.executeUpdate());
                }catch (Exception e){
                    System.out.println("更新异常");
                }
            }


            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
