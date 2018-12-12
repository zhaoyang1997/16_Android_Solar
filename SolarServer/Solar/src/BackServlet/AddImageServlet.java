package BackServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.ImgDao;
import Bean.ImageBean;
import Dao.ImgDao;

/**
 * Servlet implementation class AddImageServlet
 */
@WebServlet("/AddImageServlet")
public class AddImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("AddImageServlet开始执行");
		String url=request.getParameter("image_src");
		String image_score=request.getParameter("image_score");
		int score=Integer.parseInt(image_score);
		Bean.ImageBean image=new Bean.ImageBean();
		ImgDao imagedao=new ImgDao();
		int num=imagedao.getImageNum();
		image.setImage_id(num+1);
	    image.setImage_src(url);
	    image.setImage_score(score);
		boolean b=imagedao.insertImage(image);
		if(b==true) {
			System.out.println("插入一个图片");
			request.getRequestDispatcher("SeeAllImageServlet").forward(request, response);
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
