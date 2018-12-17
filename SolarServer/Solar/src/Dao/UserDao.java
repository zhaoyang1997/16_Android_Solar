package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import Bean.UserBean;

public class UserDao {
	
	Bean.UserBean user =  new Bean.UserBean();
	public UserDao() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserBean getUser(int myid){
		UserBean user = new UserBean();
		Connection conn = Datebase.getConnection();
		PreparedStatement pstmt = null;
		String sql = "select user_email,user_name,user_score,user_image from user where user_id=?";
		ResultSet resultSet = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, myid);
			resultSet = pstmt.executeQuery();
			while(resultSet.next()) {
				
				user.setUser_score(resultSet.getInt("user_score"));
				user.setName(resultSet.getString("user_name"));
				System.out.println(user.getName());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	/**
	 * 先查出用户已有分数
	 * 
	 * */
	public int  SelectscoreByuserId(int userId) {
		int score = 0;
		Connection conn=Datebase.getConnection();
		PreparedStatement pstmt=null;
		String sql="select user_score from user where user_id=?";
       
            // 获取PreparedStatement对象
            try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, userId);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					 
					 score=rs.getInt("user_score");
					System.out.println(score);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return score;
	}
/**
 * 
 * 改用户score
 
 * */
	
	public void UpdateUserById(int userId,int newscore) {
		//获取数据库连接
		Connection conn=Datebase.getConnection();
		PreparedStatement pstmt=null;
		
		String sql="update  user set user_score=? where user_id=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, newscore);
			pstmt.setInt(2,userId);//完成一项加分
			
			boolean rs=pstmt.execute();
			System.out.println(rs);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 通过userName查userId*/
	
	public int selectuserId(UserBean user) {
		int id=0;
		Connection conn=Datebase.getConnection();
		PreparedStatement pstmt=null;
		String sql="select user_id from user where user_name=?";
       
            // 获取PreparedStatement对象
            try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, user.getName());
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					 System.out.print(rs.getInt("user_id"));
					 user.setId(rs.getInt("user_id"));
					id=user.getId();
					
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return id;
           
		
		
		
	}
	/**
	 * 往User数据库里插入数据
	 * 
	 * */
	public void insert_data(UserBean user){
		
	Connection conn=Datebase.getConnection();
	PreparedStatement pstmt=null;
	String sql="insert into user(user_name,user_password,user_email,user_phone)values(?,?,?,?)";
	try {
		pstmt=conn.prepareStatement(sql,java.sql.Statement.RETURN_GENERATED_KEYS);
		
		pstmt.setString(1, user.getName());
		pstmt.setString(2, user.getPassword());
		pstmt.setString(3, user.getEmail());
		pstmt.setString(4, user.getTelephone());
		
		pstmt.execute();
		ResultSet rs = pstmt.getGeneratedKeys();
//		user.setId(rs.getInt(rs.getInt("user_id")));
//		System.out.println(user.getId());
		while(rs.next()) {

			
			
		}
	
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	

}

	 /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 用户对象
     */
    public UserBean login(String username, String password){
        UserBean user = null;
        // 获取数据库连接Connection对象
        Connection conn=Datebase.getConnection();
        // 根据用户名及密码查询用户信息
        String sql = "select * from user where user_name = ? and user_password = ?";
        try {
            // 获取PreparedStatement对象
            PreparedStatement ps = conn.prepareStatement(sql);
            // 对SQL语句的占位符参数进行动态赋值
            ps.setString(1, username);
            ps.setString(2, password);
            // 执行查询获取结果集
            ResultSet rs = ps.executeQuery();
            // 判断结果集是否有效
            if(rs.next()){
                // 实例化一个用户对象
                user = new UserBean();
                // 对用户对象属性赋值
                //查出user_id
                user.setId(rs.getInt("user_id"));
                user.setName(rs.getString("user_name"));
                user.setPassword(rs.getString("user_password"));
                System.out.println(rs.getInt("user_id"));
                System.out.println(user.getId());
              
               

            }
            // 释放此 ResultSet 对象的数据库和 JDBC 资源
            rs.close();
            // 释放此 PreparedStatement 对象的数据库和 JDBC 资源
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            // 关闭数据库连接
        	Datebase.closeConnection(conn);
        }
        return user;
    }
    /**
     * 判断用户名在数据库中是否存在
     * @param username 用户名
     * @return 布尔值
     */
    public boolean userIsExist(String username){
        // 获取数据库连接Connection对象
        Connection conn = Datebase.getConnection();
        // 根据指定用户名查询用户信息
        String sql = "select * from user where user_name = ?";
        try {
            // 获取PreparedStatement对象
            PreparedStatement ps = conn.prepareStatement(sql);
            // 对用户对象属性赋值
            ps.setString(1, username);
            // 执行查询获取结果集
            ResultSet rs = ps.executeQuery();
            // 判断结果集是否有效
            if(!rs.next()){
                // 如果无效则证明此用户名可用
                return true;
            }
            // 释放此 ResultSet 对象的数据库和 JDBC 资源
            rs.close();
            // 释放此 PreparedStatement 对象的数据库和 JDBC 资源
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            // 关闭数据库连接
        	Datebase.closeConnection(conn);
        }
        return false;
    }

    public UserBean userBean;
	Connection conn = Datebase.getConnection();
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	 //更新删除后的id
	public boolean update(int id) {
		boolean b=false;
		Connection conn = Datebase.getConnection();

		PreparedStatement pstmt = null;
		String sql = "update user set user_id=user_id-1 where user_id>?";
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
	//根据id删除用户
	public boolean DeleteUserById(int id) {
		boolean b=false;
		Connection conn = Datebase.getConnection();

		PreparedStatement pstmt = null;
		String sql = "delete from user where user_id=?";
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
	 * 插入数据
	 */
	/**
	 * 查询所有用户
	 */
	public List<UserBean> getAllUser() {
		List<UserBean> UserList = new ArrayList<UserBean>();
		Connection conn = Datebase.getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "select user_id,user_name,user_email,user_phone,user_password,user_score,user_image from user";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
		
		
			while (rs.next()) {
				UserBean user = new UserBean(
						rs.getInt("user_id"),
						rs.getString("user_name"),
						
						rs.getString("user_password"),
						rs.getString("user_email"),
						rs.getString("user_phone"),
						rs.getInt("user_score"),
						rs.getString("user_image")
						);
				UserList.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return UserList;
	}
	/**
	 * 密码问题页面查询用户数据
	 * @return
	 */
	public UserBean getUser(UserBean user) {
		userBean = new UserBean();
		String sql = "select * from user where user_name = ?";
		try {
		    pstmt = conn.prepareStatement(sql);
		    pstmt.setString(1,user.getName());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				userBean.setId(rs.getInt("user_id"));
				userBean.setName(rs.getString("user_name"));
				userBean.setTelephone(rs.getString("user_phone"));
				userBean.setEmail(rs.getString("user_email"));
				System.out.println(userBean.getEmail());
				userBean.setPassword(rs.getString("user_password"));
				userBean.setUser_score(rs.getInt("user_score"));
				userBean.setUser_image(rs.getString("user_image"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userBean;
	}
	/**
	 * 修改用户密码
	 * @param user
	 */
	public void updatePassword(UserBean user) {
		String sql = "update user set user_password = ? where user_id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(2, user.getId());
			pstmt.setString(1, user.getPassword());
			int i = pstmt.executeUpdate();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public UserBean user1;
	/**
	  *  查询用户的信息
	 * @return
	 */
	public void findUser(int userid){
		try {
			Connection connection = Datebase.getConnection();
			String sql = "select user_image,user_score from user where user_id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, userid);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				user1 =new UserBean();
				user1.setUser_score(rs.getInt("user_score"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
	

}



