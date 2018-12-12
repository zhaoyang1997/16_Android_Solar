package BackServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.ImgDao;

/**
 * Servlet implementation class DeleteImageServlet
 */
@WebServlet("/DeleteImageServlet")
public class DeleteImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DeleteImageServlet开始执行");
		String id=request.getParameter("imageid");
		int imageid=Integer.parseInt(id);
		ImgDao imagedao=new ImgDao();
		boolean b=imagedao.DeleteImageById(imageid);
		if(b==true) {
			System.out.println("删除成功");
			boolean c=imagedao.update(imageid);
			if(c==true) {
				System.out.println("图片id更新成功");
			}
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
