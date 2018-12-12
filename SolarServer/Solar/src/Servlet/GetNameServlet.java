package Servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.User;
import org.json.JSONObject;

import Bean.UserBean;
import Dao.UserDao;

/**
 * Servlet implementation class GetNameServlet
 */
@WebServlet("/GetNameServlet")
public class GetNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetNameServlet() {
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
        if(res==null) {
	   System.out.println("没有数据传入");
        }
        else {
        
        	JSONObject obj=new JSONObject(res);
        	int userId = obj.getInt("user_id");
        	
        	System.out.println(userId);
        	
        	UserDao userDao = new UserDao();
    		UserBean user = userDao.getUser(userId);
    		String userName=user.getName();
    		int score = user.getUser_score();
    		System.out.println(score);
    		System.out.println(userName);
    		
    		JSONObject object = new JSONObject();
    		object.put("user", userName);
    		object.put("score", score);
    		response.getWriter().append(object.toString());
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
