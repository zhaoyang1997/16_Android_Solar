package Servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import Bean.TaskBean;
import Bean.UserBean;
import Dao.TaskDao;
import Dao.UserDao;

/**
 * Servlet implementation class UpdateTaskStateServlet
 */
@WebServlet("/UpdateTaskStateServlet")
public class UpdateTaskStateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateTaskStateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InputStream is=request.getInputStream();
		
		InputStreamReader inputStreamReader = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String res = reader.readLine();
        //返回给客户端信息
        OutputStream os=response.getOutputStream();
        OutputStreamWriter opw=new OutputStreamWriter(os);
        BufferedWriter writer=new BufferedWriter(opw);
        if(res==null) {
        	System.out.println("没有数据传入");
        }else {
        	System.out.println("有数据传入");
        	JSONObject js=new JSONObject(res);
        	TaskBean task=new TaskBean();
        	task.setTaskId(js.getInt("TaskId"));
        	task.setTaskScore(js.getInt("TaskTime"));
        	
        	System.out.println(task.getTaskScore());
        	
        	TaskDao td=new TaskDao();
        	td.UpdateStateById(task.getTaskId(),task.getTaskScore());
        	System.out.println("完成状态修改");
        	UserBean user=new UserBean();
        	user.setId(js.getInt("UserId"));
        	//先查出用户已有分数
        	UserDao ud=new UserDao();
        	int score=0;
        	score=ud.SelectscoreByuserId(user.getId());
        	int newscore=score+js.getInt("TaskTime")/5;
        	System.out.println(newscore);
        	//改用户表中，用户的总分数
        	ud.UpdateUserById(user.getId(), newscore);
        	
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
