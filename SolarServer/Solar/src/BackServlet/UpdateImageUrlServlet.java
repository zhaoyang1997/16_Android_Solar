package BackServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.ImgDao;

/**
 * Servlet implementation class UpdateImageUrlServlet
 */
@WebServlet("/UpdateImageUrlServlet")
public class UpdateImageUrlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateImageUrlServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UpdateImageServlet开始执行");
		String id=request.getParameter("imageid");
        String url=request.getParameter("modify");
		int imageid=Integer.parseInt(id);
		ImgDao imagedao=new ImgDao();
		boolean b=imagedao.updateUrl(url,imageid);
		if(b==true) {
			System.out.println("路径修改成功");
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
