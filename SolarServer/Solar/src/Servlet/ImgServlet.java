package Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import Bean.MyImgBean;
import Dao.ImgDao;

/**
 * Servlet implementation class ImgServlet
 */
@WebServlet("/ImgServlet")
public class ImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImgServlet() {
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
		
//		10.7.89.78
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("name");
		
		ImgDao dao = new ImgDao();
		List<MyImgBean> myImgs = dao.getAllImgs();
		JSONArray array = new JSONArray();
		
		for(MyImgBean myImg:myImgs) {
			int id = myImg.getId();
			String image = myImg.getImageSrc();
			String price = myImg.getPrice();
			
			System.out.println(id);
			System.out.println(image);
			System.out.println(price);
			
			JSONObject obj = new JSONObject();
			obj.put("id", id);
			obj.put("image", image);
			obj.put("price", price);
			array.put(obj);
			
		}

//		request.setAttribute("myImgs", myImgs);
//		request.getRequestDispatcher("index.jsp").forward(request, response);
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
