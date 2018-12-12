package BackServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.TaskDao;

/**
 * Servlet implementation class DeleteTTaskServlet
 */
@WebServlet("/DeleteTTaskServlet")
public class DeleteTTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteTTaskServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DeleteTaskServlet开始执行");
		String id=request.getParameter("taskid");
		int taskid=Integer.parseInt(id);
		TaskDao taskdao=new TaskDao();
		boolean b=taskdao.DeleteTaskByIId(taskid);
		if(b==true) {
			System.out.println("删除成功");
			boolean c=taskdao.update(taskid);
			if(c==true) {
				System.out.println("任务id更新成功");
			}
			request.getRequestDispatcher("SeeAllTaskServlet").forward(request, response);
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
