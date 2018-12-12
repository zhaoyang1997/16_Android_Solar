package BackServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.SingDao;

/**
 * Servlet implementation class UpdateSingServlet
 */
@WebServlet("/UpdateSingServlet")
public class UpdateSingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateSingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UpdateSingServlet开始执行");
		String id=request.getParameter("singid");
        String url=request.getParameter("modify");
		int singid=Integer.parseInt(id);
		SingDao singdao=new SingDao();
		boolean b=singdao.updateUrl(url,singid);
		if(b==true) {
			System.out.println("路径修改成功");
			request.getRequestDispatcher("SeeAllSingServlet").forward(request, response);
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
