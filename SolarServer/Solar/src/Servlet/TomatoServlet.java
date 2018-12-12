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

import Dao.TomatoDao;

/**
 * Servlet implementation class TomatoServlet
 */
@WebServlet("/TomatoServlet")
public class TomatoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TomatoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("222222222222222222222222222");
		InputStream is=request.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String res = reader.readLine();
        System.out.println("222222222222222222222222222");
   
        if(res!= null) {
        	System.out.println(res);
        	JSONObject obj=new JSONObject(res);
        	int userId = obj.getInt("user_id");
        	int num = obj.getInt("num");
        	String year = obj.getString("year");
        	String month = obj.getString("month");
        	String day = obj.getString("day");
        	TomatoDao tomatoDao = new TomatoDao();
        	tomatoDao.insert(userId, num, year, month, day);
        	System.out.println(userId);
        	System.out.println(num);
        	System.out.println(year);
        	System.out.println(month);
        	System.out.println(day);


        }
        response.getWriter().append("ok");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
