package Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import Bean.Manager;


public class ManagerDao {
	/**
	  *  查询管理员的信息
	 * @return
	 */
	public List<Manager> findManager(){
		try {
			Connection connection = Datebase.getConnection();
			Statement stmt =  (Statement) connection.createStatement();
			List<Manager> list = new ArrayList<>();
			ResultSet rs = stmt.executeQuery("select manager_name,manager_password from manager");
			while(rs.next()) {
				Manager manager = new Manager();
				manager.setUsername(rs.getString("manager_name"));
				manager.setPassword(rs.getString("manager_password"));
				list.add(manager);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
