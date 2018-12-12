package BackServlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.TaskBean;
import Dao.TaskDao;

/**
 * Servlet implementation class SeeAllTaskServlet
 */
@WebServlet("/SeeAllTaskServlet")
public class SeeAllTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SeeAllTaskServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TaskDao taskdao=new TaskDao();
		List<TaskBean> tasks=taskdao.getAllTask();
		request.setAttribute("tasks", tasks);
		request.getRequestDispatcher("tasklist.jsp").forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
				doGet(request, response);
			}

		}

