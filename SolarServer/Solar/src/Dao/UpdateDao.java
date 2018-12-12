package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class UpdateDao {
	
	public void UpdateBought(int userId,int ImgId,String imageurl) {
		Connection conn = Datebase.getConnection();
		PreparedStatement pstmt = null;
		String sql = "insert into buy(user_id,images_id,image_url) values(?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setInt(2,ImgId);
			pstmt.setString(3, imageurl);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void UpdateUserScore(int userId,int score) {
		Connection conn = Datebase.getConnection();
		PreparedStatement pstmt = null;
		String sql = "update user set user_score=user_score-? where user_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, score);
			pstmt.setInt(2,userId);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
