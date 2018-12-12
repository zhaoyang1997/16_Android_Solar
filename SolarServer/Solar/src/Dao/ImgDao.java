package Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import Bean.ImageBean;
import Bean.MyImgBean;

public class ImgDao {

	public List<MyImgBean> getAllImgs(){
		List<MyImgBean> myImgs = new ArrayList<>();
		Connection conn = Datebase.getConnection();
		java.sql.PreparedStatement pstmt = null;
		String sql = "select images_id,images_src,images_score from images";
		ResultSet resultSet = null;
		try {
			pstmt = conn.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			while(resultSet.next()) {
				MyImgBean myImg = new MyImgBean();
				myImg.setId(resultSet.getInt("images_id"));
				myImg.setPrice(resultSet.getString("images_score"));
				myImg.setImageSrc(resultSet.getString("images_src"));
				myImgs.add(myImg);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myImgs;
	}
	
	//添加一条产品信息
	public boolean insertImage(ImageBean image) {
		Connection conn = Datebase.getConnection();
		java.sql.PreparedStatement pstmt = null;
		try {
			String sql="insert into images(images_id,images_src,images_score) values(?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, image.getImage_id());
			pstmt.setString(2,image.getImage_src());
			pstmt.setInt(3,image.getImage_score());
			int num=pstmt.executeUpdate();
			if(num>0) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return false;
	
	}
//得到产品的数量
	public int getImageNum() {
		int num = 0;
		List<ImageBean> List = new ArrayList<ImageBean>();
		Connection conn = Datebase.getConnection();
        java.sql.PreparedStatement pstmt = null;
		String sql = "select *  from images";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				num++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}
//更新删除后的id
public boolean update(int id) {
	boolean b=false;
	Connection conn = Datebase.getConnection();

	java.sql.PreparedStatement pstmt = null;
	String sql = "update images set images_id=images_id-1 where images_id>?";
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
//根据id删除任务
		public boolean DeleteImageById(int id) {
			boolean b=false;
			Connection conn = Datebase.getConnection();

			java.sql.PreparedStatement pstmt = null;
			String sql = "delete from images where images_id=?";
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
* 查询所有的壁纸
*/
public List<ImageBean> getAllImage() {
List<ImageBean> ImageList = new ArrayList<ImageBean>();
Connection conn = Datebase.getConnection();
ResultSet rs = null;
java.sql.PreparedStatement pstmt = null;
String sql = "select images_id,images_src,images_score from images";
try {
	pstmt = conn.prepareStatement(sql);
	rs = pstmt.executeQuery();


	while (rs.next()) {
		ImageBean image = new ImageBean(
				rs.getInt("images_id"),
				rs.getString("images_src"),
				rs.getInt("images_score")
				);
		ImageList.add(image);
	}
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
return ImageList;
}
//修改路径
public boolean updateUrl(String url, int imageid) {
boolean b=false;
Connection conn = Datebase.getConnection();
java.sql.PreparedStatement pstmt = null;
try {
	String sql="update images set images_src=? where images_id=?";
	pstmt=conn.prepareStatement(sql);
	pstmt.setString(1, url);
	pstmt.setInt(2, imageid);
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
//修改积分
public boolean updateScore(int imagescore, int imageid) {
boolean b=false;
Connection conn = Datebase.getConnection();
java.sql.PreparedStatement pstmt = null;
try {
	String sql="update images set images_score=? where images_id=?";
	pstmt=conn.prepareStatement(sql);
	pstmt.setInt(1, imagescore);
	pstmt.setInt(2, imageid);
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


}
