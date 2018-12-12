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

import org.json.JSONArray;
import org.json.JSONObject;

import Bean.UserBean;
import Dao.UserDao;

/**
 * Servlet implementation class registerServlet
 */
@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerServlet() {
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
	        UserDao ud=new UserDao();
	       
	        String ss=js.getString("username");
	        if(ud.userIsExist(ss)) {//如果此用户名可用
	        	UserBean user=new UserBean();
	        	user.setName(js.getString("username"));
		        user.setPassword(js.getString("password"));
		        user.setEmail(js.getString("email"));
		        user.setTelephone(js.getString("phone"));
		       
			       ud.insert_data(user);
			       writer.write("注册成功");
		             writer.flush();
		             writer.close();
	        }else {
	        	 writer.write("用户名已存在，请更改用户名");
	             writer.flush();
	             writer.close();
	        }
	        
	      
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
