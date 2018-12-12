package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Datebase {
	
	//
	/**
	 * 来获取对数据库的连接
	  */
	public static Connection getConnection() {
		Connection conn=null;//com.mysql.jdbc.driver旧版本
		try {
			//新版本Class.forName("com.mysql.cj.jdbc.Driver");
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接
		//	conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/pagingdemo?serverTimezone=GMT&useUnicode=true&characterEncoding=utf-8&useSSL=false","root","");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/solar?serverTimezone=GMT&useUnicode=true&characterEncoding=utf-8&useSSL=false","root","");	
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
		
	}
	 /**
     * 关闭数据库连接
     * @param conn Connection对象
     */
    public static void closeConnection(Connection conn){
        // 判断conn是否为空
        if(conn != null){
            try {
                conn.close();   // 关闭数据库连接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}