package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Bean.SingBean;

public class SingDao {
	public List<SingBean> Query() {
		List<SingBean> list=new ArrayList<SingBean>();
		Connection conn = Datebase.getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "select * from sing";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				SingBean sing=new SingBean(rs.getInt("sing_id"),
						rs.getString("sing_url"),
						rs.getString("sing_name")); 
				list.add(sing);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	//添加一条产品信息
		public boolean insertSing(SingBean sing) {
			Connection conn = Datebase.getConnection();
			PreparedStatement pstmt = null;
			try {
				String sql="insert into sing(sing_id,sing_url,sing_name) values(?,?,?)";
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, sing.getSing_id());
				pstmt.setString(2,sing.getSing_url());
				pstmt.setString(3,sing.getSing_name());
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
		/**
		 * 查询所有的铃声
		 */
		public List<SingBean> getAllSing() {
			List<SingBean> singList = new ArrayList<SingBean>();
			Connection conn = Datebase.getConnection();
			ResultSet rs = null;
			PreparedStatement pstmt = null;
			String sql = "select * from sing";
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
			
			
				while (rs.next()) {
					SingBean sing = new SingBean(
							rs.getInt("sing_id"),
							rs.getString("sing_url"),
							rs.getString("sing_name")
							);
					singList.add(sing);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return singList;
		}
		//得到图片的数量
		public int getSingNum() {
			int num = 0;
			List<SingBean> List = new ArrayList<SingBean>();
			Connection conn = Datebase.getConnection();
	        PreparedStatement pstmt = null;
			String sql = "select *  from sing";
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

			PreparedStatement pstmt = null;
			String sql = "update sing set sing_id=sing_id-1 where sing_id>?";
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
		//根据id删除铃声
				public boolean DeleteSingById(int id) {
					boolean b=false;
					Connection conn = Datebase.getConnection();

					PreparedStatement pstmt = null;
					String sql = "delete from sing where sing_id=?";
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

				//修改路径
		public boolean updateUrl(String url, int singid) {
			boolean b=false;
			Connection conn = Datebase.getConnection();
			PreparedStatement pstmt = null;
	       try {
				String sql="update sing set sing_url=? where sing_id=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, url);
				pstmt.setInt(2, singid);
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
