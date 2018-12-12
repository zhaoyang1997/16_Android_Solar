package Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import Bean.SingBean;
import Dao.SingDao;

/**
 * Servlet implementation class LookSingServlet
 */
@WebServlet("/LookSingServlet")
public class LookSingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LookSingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		SingDao singdao=new SingDao();
		List<SingBean> sings=singdao.Query();
		JSONArray array=new JSONArray();
		
		for(SingBean sing:sings) {
			
			String name=sing.getSing_name();
			
			
			System.out.println(name);
			
			JSONObject obj1=new JSONObject();
			
			obj1.put("name", name);
			
			array.put(obj1);
		}
		response.getWriter().append(array.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
