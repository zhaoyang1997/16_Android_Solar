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
 * Servlet implementation class TestUserServlet
 */
@WebServlet("/TestUserServlet")
public class TestUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		InputStream is=request.getInputStream();	
		InputStreamReader inputStreamReader = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String res = reader.readLine();
        JSONObject jsonObject = new JSONObject(res);
        UserBean user = new UserBean();
        user.setId(jsonObject.getInt("userId"));
		UserDao userDao = new UserDao();	
		userDao.getUser(user);
		//将数据编码格式成JSon格式	
		JSONObject js = new JSONObject();
		js.put("name",user.getName());
		js.put("phone", user.getTelephone());
		js.put("email", user.getEmail());
		System.out.println(user.getEmail());
		response.getWriter().append(js.toString()).append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
