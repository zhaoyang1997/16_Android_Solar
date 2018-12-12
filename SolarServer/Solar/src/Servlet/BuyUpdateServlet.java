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

import Dao.UpdateDao;


/**
 * Servlet implementation class BuyUpdateServlet
 */
@WebServlet("/BuyUpdateServlet")
public class BuyUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyUpdateServlet() {
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
   
        if(res!= null) {
        	System.out.println(res);
        	JSONObject obj=new JSONObject(res);
        	int userId = obj.getInt("user_id");
        	int imageId = obj.getInt("image_id");
        	String imageUrl = obj.getString("imageurl");
        	String score = obj.getString("score");
        	int upscore = Integer.parseInt(score);
        	
        	UpdateDao updateDao = new UpdateDao();
        	updateDao.UpdateBought(userId, imageId,imageUrl);
        	updateDao.UpdateUserScore(userId, upscore);

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
