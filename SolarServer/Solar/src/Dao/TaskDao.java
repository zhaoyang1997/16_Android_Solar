package Dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Bean.TaskBean;
import Bean.UserBean;

public class TaskDao {
	Connection conn = Datebase.getConnection();
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	

	/**
	  *  查询用户任务的信息
	 * @return
	 */
	public List<TaskBean> findTask(int userid){
		try {
			List<TaskBean> list = new ArrayList<>();
			Connection connection = Datebase.getConnection();
			String sql = "select task_name,task_score from task where user_id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, userid);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				TaskBean task = new TaskBean();
				task.setTaskName(rs.getString("task_name"));
				task.setTaskScore(rs.getInt("task_score"));
				list.add(task);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	//查询任务xianshi表，根据UserId,返回的是一List的列表
	public List<TaskBean> selectTaskById(UserBean user){
		List<TaskBean> list=new ArrayList<TaskBean>();
		//获取数据库连接
		Connection conn=Datebase.getConnection();
		PreparedStatement pstmt=null;
		//task_show_tag 为1时才显示列表
		String sql="select task_id,task_name,task_time,task_state from task where user_id=? and task_show_tag=?";
		//获取PreparedStatement对象
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, user.getId());
			pstmt.setString(2, "1");
			//获取结果集
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				TaskBean task=new TaskBean();
				task.setTaskName(rs.getString("task_name"));
				task.setDateTime(rs.getInt("task_time"));
				task.setTaskId(rs.getInt("task_id"));
				task.setTaskState(rs.getString("task_state"));
				System.out.println(task.getDateTime());
				list.add(task);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
		
		
	}
	//有新任务添加到显示表
	public void insertTask(int userId,String Taskname,int TaskTime,String TaskYear,String TaskMonth,String TaskDay) {
				//获取数据库连接
				Connection conn=Datebase.getConnection();
				PreparedStatement pstmt=null;
				String sql="insert into task(user_id,task_name,task_time,task_year,task_month,task_day,task_state,task_show_tag,task_score)values(?,?,?,?,?,?,?,?,?)";
				try {
					pstmt=conn.prepareStatement(sql,java.sql.Statement.RETURN_GENERATED_KEYS);
					pstmt.setInt(1, userId);
					pstmt.setString(2,Taskname);
					pstmt.setInt(3,TaskTime);
					pstmt.setString(4, TaskYear);
					pstmt.setString(5,TaskMonth);
					pstmt.setString(6, TaskDay);
					pstmt.setString(7, "未完成");
					
					//规定“1”为显示状态
					pstmt.setString(8,"1");
					pstmt.setInt(9, 0);//分数定为0
					

					pstmt.execute();
					ResultSet rs = pstmt.getGeneratedKeys();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
	}
	
		

	//根据TaskId修改任务表
	public void UpdateTaskBYId(int TaskId,String Taskname,int TaskTime,String TaskYear,String TaskMonth,String TaskDay) {
		
		//获取数据库连接
				Connection conn=Datebase.getConnection();
				PreparedStatement pstmt=null;
				String sql="update  task set task_name=?,task_time=?,task_year=?,task_month=?,task_day=? where task_id=?";
				try {
					pstmt=conn.prepareStatement(sql);
					pstmt.setString(1,Taskname);
					pstmt.setInt(2,TaskTime);
					pstmt.setString(3, TaskYear);
					pstmt.setString(4,TaskMonth);
					pstmt.setString(5, TaskDay);
					pstmt.setInt(6, TaskId);
					System.out.println(Taskname);
					pstmt.executeUpdate();
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	
	//根据TaskId删除任务显示表
	//实际上shi改task_show_tag为2，让其不在页面显示
	public void DeleteTaskById(int TaskId) {
		//获取数据库连接
		Connection conn=Datebase.getConnection();
		PreparedStatement pstmt=null;
		String sql="update  task set task_show_tag=? where task_id=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "2");
			pstmt.setInt(2, TaskId);
			boolean rs=pstmt.execute();
			System.out.println(rs);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}		
		//根据TaskId改任务的状态为完成
		public void UpdateStateById(int TaskId,int TaskTime) {
			//获取数据库连接
			Connection conn=Datebase.getConnection();
			PreparedStatement pstmt=null;
			int score=TaskTime/5;
			String sql="update  task set task_state=?,task_score=? where task_id=?";
			try {
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "完成");
				pstmt.setInt(2,score);//完成一项加分
				pstmt.setInt(3, TaskId);
				boolean rs=pstmt.execute();
				System.out.println(rs);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		
		
		
		 //更新删除后的id
		public boolean update(int id) {
			boolean b=false;
			Connection conn = Datebase.getConnection();

			PreparedStatement pstmt = null;
			String sql = "update task set task_id=task_id-1 where task_id>?";
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
				public boolean DeleteTaskByIId(int id) {
					boolean b=false;
					Connection conn = Datebase.getConnection();

					PreparedStatement pstmt = null;
					String sql = "delete from task where task_id=?";
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
		 * 查询所有的任务
		 */
				public List<TaskBean> getAllTask() {
					List<TaskBean> TaskList = new ArrayList<TaskBean>();
					Connection conn = Datebase.getConnection();
					ResultSet rs = null;
					PreparedStatement pstmt = null;
					String sql = "select task_id,user_id,task_name,task_state,task_score,task_year,task_month,task_day from task";
					try {
						pstmt = conn.prepareStatement(sql);
						rs = pstmt.executeQuery();
					
					
						while (rs.next()) {
							TaskBean task = new TaskBean(
									rs.getInt("task_id"),
									rs.getInt("user_id"),
									rs.getString("task_name"),
									rs.getString("task_state"),
									rs.getString("task_year"),
									rs.getString("task_month"),
									rs.getString("task_day"),
									rs.getInt("task_score")
									);
							TaskList.add(task);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return TaskList;
				}
		/**
		 * 查询用户的任务信息
		 */
		public List<TaskBean> getTask(int userid){
					try {
						List<TaskBean> list = new ArrayList<>();
						Connection connection = Datebase.getConnection();
					String sql = "select task_name,task_score from task where user_id = ? and task_show_tag= ? ";
						PreparedStatement ps = connection.prepareStatement(sql);
						ps.setInt(1,userid);
						ps.setString(2, "2");
						ResultSet rs = ps.executeQuery();
						
						while(rs.next()) {
							TaskBean task = new TaskBean();
							task.setTaskName(rs.getString("task_name"));
							task.setTaskScore(rs.getInt("task_score"));
							list.add(task);
						}
						return list;
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return null;
					}
				}

	}



