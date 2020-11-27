package Util;

import Jsoup_app.APP;

import java.sql.*;

public class ConnectMysql {

    public static void connnect147(APP app) {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://192.168.205.147:3306/appinfo?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        String username = "root";
        String password = "3/cgohl0z7zpYYdj1kA=";
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            System.out.println(conn);
            System.out.println("连接147数据库成功");
//            String select = "SELECT version_number FROM pachong_result_copy WHERE package_name =" + " '" + app.getPackagename() + "'";
//            ResultSet rs = stmt.executeQuery(select);
//
//            rs.next();//下面的getString方法一定要有next在前面
//            String version_DB = rs.getString("version_number");
//            System.out.println(version_DB);
//
//
//            rs.close();
//            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        connnect147(null);
    }
}
