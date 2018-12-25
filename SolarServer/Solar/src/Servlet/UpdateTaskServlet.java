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
 * Servlet implementation class UpdateTaskServlet
 */
@WebServlet("/UpdateTaskServlet")
public class UpdateTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateTaskServlet() {
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
        	task.setTaskName(js.getString("Tname"));
        	System.out.println(task.getTaskId());
        	task.setDateTime(js.getInt("Ttime"));
        	System.out.println(task.getDateTime());
        	task.setYear(js.getString("Tyear"));
        	
        	task.setMonth(js.getString("Tmonth"));
        	task.setDay(js.getString("Tday"));
        	TaskDao td=new TaskDao();
        	
        	//根据TaskId去数据库中修改Task显示表
        	
        	td.UpdateTaskBYId(task.getTaskId(), task.getTaskName(), task.getDateTime(), task.getYear(),task.getMonth(), task.getDay());
        
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
