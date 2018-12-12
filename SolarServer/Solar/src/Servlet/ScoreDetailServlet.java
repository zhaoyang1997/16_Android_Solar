package Servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import Bean.TaskBean;
import Bean.TomatoBean;
import Dao.TaskDao;
import Dao.TomatoDao;
import Dao.UserDao;

/**
 * Servlet implementation class ScoreDetailServlet
 */
@WebServlet("/ScoreDetailServlet")
public class ScoreDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScoreDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		UserDao userdao = new UserDao();
		TaskDao taskdao = new TaskDao();
		TomatoDao tomatodao = new TomatoDao();
		
		InputStream is=request.getInputStream();	
		InputStreamReader inputStreamReader = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String res = reader.readLine();
        JSONObject object = new JSONObject(res);
        
        int id = object.getInt("userId");
        List<TaskBean> list = taskdao.findTask(id);
		List<TomatoBean> lists = tomatodao.findTomato(id);
		userdao.findUser(id);
		
		object = null;
		object.put("user_score",userdao.user1.getUser_score());
		for(int i=0;i<list.size();i++) {
			object.put("task_name["+i+"]",list.get(i).getTaskName());
			object.put("task_score["+i+"]",list.get(i).getTaskScore());
		}
		for(int i=0;i<lists.size();i++) {
			object.put("tomato_name["+i+"]",lists.get(i).getTomatoName());
			object.put("tomato_score["+i+"]",lists.get(i).getTomato_score());
		}
		object.put("num1", list.size());
		object.put("num2", lists.size());
		response.getWriter().append(object.toString()).append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
