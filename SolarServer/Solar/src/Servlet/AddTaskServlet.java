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
import Dao.TaskDao;

/**
 * Servlet implementation class AddTaskServlet
 */
@WebServlet("/AddTaskServlet")
public class AddTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTaskServlet() {
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
        	task.setTaskName(js.getString("Tname"));
        	int userId = js.getInt("userid");
        	System.out.println(task.getTaskName());
        	task.setDateTime(js.getInt("Ttime"));
        	System.out.println(task.getDateTime());
        	task.setYear(js.getString("Tyear"));
        	task.setMonth(js.getString("Tmonth"));
        	task.setDay(js.getString("Tday"));
    
        	//首先要查到用户的UserID--根据username查
        	//先设为固定
        	TaskDao td=new TaskDao();
        	
        	//添加到显示表
        	td.insertTask(userId,task.getTaskName(),task.getDateTime(),task.getYear(),task.getMonth(),task.getDay());
        	
        	response.sendRedirect("ShowTaskServlet");
        	writer.write("添加任务成功");
        	
        	writer.flush();
        	writer.close();
        	
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
