package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Bean.TomatoBean;

public class TomatoDao {
	public void insert(int uId,int num,String year,String month,String day){
		Connection conn = Datebase.getConnection();
		PreparedStatement pstmt = null;
		String sql = "insert into tomato(user_id,tomato_num,tomato_year,tomato_month,tomato_day) values(?,?,?,?,?)";
		ResultSet resultSet = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uId);
			pstmt.setInt(2, num);
			pstmt.setString(3, year);
			pstmt.setString(4, month);
			pstmt.setString(5, day);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	Connection conn = Datebase.getConnection();
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	//后台查询所有番茄记录
	public List<TomatoBean> getAllTomato() {
		List<TomatoBean> TomatoList = new ArrayList<TomatoBean>();
		Connection conn = Datebase.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql = "select tomato_id,user_id,tomato_num,tomato_year,tomato_month,tomato_day from tomato";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
		
		
			while (rs.next()) {
				TomatoBean tomato = new TomatoBean(
						rs.getInt("tomato_id"),
						rs.getInt("user_id"),
						rs.getInt("tomato_num"),
						rs.getInt("tomato_year"),
						rs.getInt("tomato_month"),
						rs.getInt("tomato_day")
						);
				TomatoList.add(tomato);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return TomatoList;
	}
	/**
	 * houtai查询用户的番茄信息
	 */
	
	public List<TomatoBean> findTomato(int userid){
		try {
			Connection connection = Datebase.getConnection();
			List<TomatoBean> list = new ArrayList<>();
			String sql = "select tomato_num from tomato where user_id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, userid);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				TomatoBean tomato = new TomatoBean();
				tomato.setTomatoName("完成"+rs.getInt("tomato_num")+"个番茄时间");
				tomato.setTomato_score(rs.getInt("tomato_num")*5);
				list.add(tomato);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	 //更新删除后的id
		public boolean update(int id) {
			boolean b=false;
			Connection conn = Datebase.getConnection();

			PreparedStatement pstmt = null;
			String sql = "update tomato set tomato_id=tomato_id-1 where tomato_id>?";
			try {
				pstmt = conn.prepareStatement(sql);
			    pstmt.setInt(1, id);
			    int num=pstmt.executeUpdate();
			    if(num>0) {
			    	return true;
			    }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return b;
		}
	//根据id删除番茄
		public boolean DeleteTomatoById(int id) {
			boolean b=false;
			Connection conn = Datebase.getConnection();

			PreparedStatement pstmt = null;
			String sql = "delete from tomato where tomato_id=?";
			try {
				pstmt = conn.prepareStatement(sql);
			    pstmt.setInt(1, id);
			    int num=pstmt.executeUpdate();
			    if(num>0) {
			    	return true;
			    }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return b;
		}
		/**
		  *  前台查询用户番茄的信息
		 * @return
		 */
//		public List<TomatoBean> findTTomato(int userid){
//			try {
//				Connection connection = Datebase.getConnection();
//				List<TomatoBean> list = new ArrayList<>();
//				String sql = "select tomato_num from tomato where user_id = ?";
//				PreparedStatement ps = connection.prepareStatement(sql);
//				ps.setInt(1, userid);
//				ResultSet rs = ps.executeQuery();
//				
//				while(rs.next()) {
//					TomatoBean tomato = new TomatoBean();
//					tomato.setTomatoName("完成"+rs.getInt("tomato_num")+"个番茄时间");
//					tomato.setTomato_score(rs.getInt("tomato_score"));
//					list.add(tomato);
//				}
//				return list;
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				return null;
//			}
//		}
	

}



