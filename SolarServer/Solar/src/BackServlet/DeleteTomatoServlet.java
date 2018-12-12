package BackServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.TomatoDao;

/**
 * Servlet implementation class DeleteTomatoServlet
 */
@WebServlet("/DeleteTomatoServlet")
public class DeleteTomatoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteTomatoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DeleteTomatoServlet开始执行");
		String id=request.getParameter("tomatoid");
		int tomatoid=Integer.parseInt(id);
		TomatoDao tomatodao=new TomatoDao();
		boolean b=tomatodao.DeleteTomatoById(tomatoid);
		if(b==true) {
			System.out.println("删除成功");
			boolean c=tomatodao.update(tomatoid);
			if(c==true) {
				System.out.println("番茄id更新成功");
			}
			request.getRequestDispatcher("SeeAllTomatoServlet").forward(request, response);
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
