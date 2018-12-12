package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Bean.PictureBean;

public class PictureDao {
	public List<PictureBean> Query() {
		List<PictureBean> list=new ArrayList<PictureBean>();
		Connection conn = Datebase.getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "select user_id,images_id,image_url from buy";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();	
			while (rs.next()) {
				PictureBean picture = new PictureBean(
						rs.getInt("user_id"),
						rs.getInt("images_id"),
						rs.getString("image_url"));
				list.add(picture);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
