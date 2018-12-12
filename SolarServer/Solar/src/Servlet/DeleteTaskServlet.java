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
 * Servlet implementation class DeleteTaskServlet
 */
@WebServlet("/DeleteTaskServlet")
public class DeleteTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteTaskServlet() {
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
        	TaskBean task=new TaskBean();
        	JSONObject object=new JSONObject(res);
        	task.setTaskId(object.getInt("TaskId"));
        	TaskDao td=new TaskDao();
        	td.DeleteTaskById(task.getTaskId());
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
