package BackServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.Manager;
import Dao.ManagerDao;

/**
 * Servlet implementation class BackLoginServlet
 */
@WebServlet("/BackLoginServlet")
public class BackLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BackLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		System.out.println(username);
		System.out.println(password);
		
		ManagerDao md = new ManagerDao();
		List<Manager> list = md.findManager();
		for(int i=0;i<list.size();i++) {
			String name = list.get(i).getUsername();
			if(name.equals(username)) {
				if(password.equals(list.get(i).getPassword())) {
					request.getRequestDispatcher("index.jsp").forward(request,response);
				}else {
					response.sendRedirect("error.jsp");
				}
			}
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
