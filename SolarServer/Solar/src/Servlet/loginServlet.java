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

import Bean.UserBean;
import Dao.UserDao;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
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
        	UserBean user=new UserBean();
	        JSONObject js=new JSONObject(res);
	        String username=js.getString("username");
	        System.out.println(username);
	        String password=js.getString("password");
	        user.setName(username);
	        user.setPassword(password);
	        UserDao ud=new UserDao();
	       
	        if(ud.login(username,password)==null) {
	        System.out.println("登录名或密码不正确！");
//	        //不返回响应给服务器端
//	       
         
	        }else {
	        	//
	        	user= ud.login(username,password);
	        	//查出user_id
	        	//把数据编码成JSON格式
    			JSONObject obj1=new JSONObject();	
    			System.out.println(user.getId());
    			obj1.put("userId",user.getId());
    			//加上这一句
    			writer.write(String.valueOf(obj1));
//	        	  writer.write("登录成功");
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
