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

import Bean.PictureBean;
import Dao.PictureDao;

/**
 * Servlet implementation class SolarSettingServlet
 */
@WebServlet("/SolarSettingServlet")
public class SolarSettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SolarSettingServlet() {
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
		PictureDao picturedao=new PictureDao();
		List<PictureBean> pictures=picturedao.Query();
		JSONArray array=new JSONArray();		
		for(PictureBean picture:pictures) {
			int userid=picture.getUser_id();
			int imageid=picture.getImage_id();
			String imageurl=picture.getImage_url();
			
			JSONObject obj1=new JSONObject();
			obj1.put("userid",userid);
			obj1.put("imageid", imageid);
			obj1.put("url", imageurl);
			
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
