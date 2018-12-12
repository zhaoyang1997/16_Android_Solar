package BackServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.ImgDao;

/**
 * Servlet implementation class UpdateImageScoreServlet
 */
@WebServlet("/UpdateImageScoreServlet")
public class UpdateImageScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateImageScoreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UpdateImageScoreServlet开始执行");
		String id=request.getParameter("imageid");
        String score=request.getParameter("score");
        int imageid=Integer.parseInt(id);
		int imagescore=Integer.parseInt(score);
		ImgDao imagedao=new ImgDao();
		boolean b=imagedao.updateScore(imagescore,imageid);
		if(b==true) {
			System.out.println("积分修改成功");
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
