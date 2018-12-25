package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SumDao {

	public int sumTomatoYear(String month,String year,int id) {
		int sumTomatoYear=0;
		Connection conn=Datebase.getConnection();
		PreparedStatement pstmt=null;
		String sql="select tomato_num from tomato where tomato_month=? and tomato_year=? and user_id=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, month);
			pstmt.setString(2, year);
			pstmt.setInt(3, id);
			ResultSet rs=pstmt.executeQuery();
			int sum=0;
			while(rs.next()) {
				sum=rs.getInt("tomato_num");
				sumTomatoYear=sumTomatoYear+sum;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			if(pstmt!= null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 		
			if(conn!= null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		}

		
		return sumTomatoYear;
	}
	
	public int sumTaskYear(String month,String year,int id) {
		int sumTaskYear=0;
		Connection conn=Datebase.getConnection();
		PreparedStatement pstmt=null;
		String sql="select count(*) from task where task_month=? and task_year=? and user_id=? and task_state=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, month);
			pstmt.setString(2, year);
			pstmt.setInt(3, id);
			pstmt.setString(4, "完成");
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				sumTaskYear=rs.getInt("count(*)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			if(pstmt!= null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 		
			if(conn!= null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		}

		return sumTaskYear;
	}
	
	public int sumTaskYearNotFinish(String month,String year,int id) {
		int sumTaskYear=0;
		Connection conn=Datebase.getConnection();
		PreparedStatement pstmt=null;
		String sql="select count(*) from task where task_month=? and task_year=? and user_id=? and task_state=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, month);
			pstmt.setString(2, year);
			pstmt.setInt(3, id);
			pstmt.setString(4, "未完成");
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				sumTaskYear=rs.getInt("count(*)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			if(pstmt!= null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 		
			if(conn!= null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		}

		return sumTaskYear;
	}
	
	public int sumTomato(String date,String month,String year,int id) {
		Connection conn=Datebase.getConnection();
		PreparedStatement pstmt=null;
		String sql="select tomato_num from tomato where tomato_day=? and tomato_month=? and tomato_year=? and user_id=?";
		int sumTomato=0;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,date);
			pstmt.setString(2, month);
			pstmt.setString(3, year);
			pstmt.setInt(4, id);
			ResultSet rs=pstmt.executeQuery();
			int num=0;
			while(rs.next()) {
				sumTomato=rs.getInt("tomato_num");
				sumTomato=sumTomato+num;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally
		{
			if(pstmt!= null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 		
			if(conn!= null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		}

		return sumTomato;
	}
	
	public int sumTask(String date,String month,String year,int id) {
		Connection conn=Datebase.getConnection();
		PreparedStatement pstmt=null;
		String sql="select count(*) from task where task_day=? and task_month=? and task_year=? and user_id=? and task_state=?";
		int sumTask=0;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,date);
			pstmt.setString(2, month);
			pstmt.setString(3, year);
			pstmt.setInt(4, id);
			pstmt.setString(5, "完成");
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				sumTask=rs.getInt("count(*)");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally
		{
			if(pstmt!= null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 		
			if(conn!= null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		}

		return sumTask;
	}
	
	public int sumTaskNotFinish(String date,String month,String year,int id) {
		Connection conn=Datebase.getConnection();
		PreparedStatement pstmt=null;
		String sql="select count(*) from task where task_day=? and task_month=? and task_year=? and user_id=? and task_state=?";
		int sumTask=0;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,date);
			pstmt.setString(2, month);
			pstmt.setString(3, year);
			pstmt.setInt(4, id);
			pstmt.setString(5, "未完成");
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				sumTask=rs.getInt("count(*)");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally
		{
			if(pstmt!= null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 		
			if(conn!= null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		}

		return sumTask;
	}
}
