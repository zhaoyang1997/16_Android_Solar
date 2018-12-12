package BackServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.SingBean;
import Dao.SingDao;

/**
 * Servlet implementation class AddSingServlet
 */
@WebServlet("/AddSingServlet")
public class AddSingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddSingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("AddSingServlet开始执行");
		String sing_url=request.getParameter("sing_url");
		String sing_name=request.getParameter("sing_name");
		SingBean sing=new SingBean();
		SingDao singdao=new SingDao();
		int num=singdao.getSingNum();
		sing.setSing_id(num+1);
	    sing.setSing_url(sing_url);
	    sing.setSing_name(sing_name);
		boolean b=singdao.insertSing(sing);
		if(b==true) {
			System.out.println("插入一个铃声");
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
