package Servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import Bean.TaskBean;
import Bean.UserBean;
import Dao.TaskDao;

/**
 * Servlet implementation class ShowTaskServlet
 */
@WebServlet("/ShowTaskServlet")
public class ShowTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowTaskServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
//        if(res==null) {
//        	System.out.println("没有数据传入");
//        	
//        }else {
			UserBean user=new UserBean();
			user.setId(1);
        	
    		TaskDao td=new TaskDao();
    		//返回的是一个Task列表
    		List<TaskBean> list=td.selectTaskById(user);
    		//去循环得到的任务名称的list
    		JSONArray array=new JSONArray();
    		for(TaskBean i:list) {
    			//把数据编码成JSON格式
    			JSONObject obj1=new JSONObject();
    			System.out.println(i);
    			obj1.put("taskName",i.getTaskName());
    			obj1.put("taskTime",i.getDateTime());
    			obj1.put("taskId",i.getTaskId());
    			obj1.put("taskState",i.getTaskState());
    			array.put(obj1);}
    		response.getWriter().append(array.toString()).append(request.getContextPath());
			
		}
		
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
