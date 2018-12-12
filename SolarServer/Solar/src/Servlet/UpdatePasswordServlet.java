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

import org.json.JSONObject;

import Bean.UserBean;
import Dao.UserDao;

/**
 * Servlet implementation class UpdatePasswordServlet
 */
@WebServlet("/UpdatePasswordServlet")
public class UpdatePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		InputStream is=request.getInputStream();	
		InputStreamReader inputStreamReader = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String res = reader.readLine();
        JSONObject js=null;
        if(res==null) {
        	System.out.println("为空");
        }else {
        	js = new JSONObject(res);
        	UserBean userBean = new UserBean();
        	int userId = js.getInt("userId");
        	String password = js.getString("password");
        	userBean.setId(userId);
        	userBean.setPassword(password);
        	UserDao userDao = new UserDao();
        	userDao.updatePassword(userBean);
            System.out.println(res);
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
