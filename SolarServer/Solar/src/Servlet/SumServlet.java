package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import Dao.SumDao;

/**
 * Servlet implementation class SumServlet
 */
@WebServlet("/SumServlet")
public class SumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SumServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String year=request.getParameter("year");
		String month=request.getParameter("month");
		String date=request.getParameter("day");
		String num=request.getParameter("num");
		String userId=request.getParameter("userId");
		int id=Integer.parseInt(userId);
		List<Integer> sum=new ArrayList<>();
		SumDao sumdao=new SumDao();
		int sumTomato=0;
		int sumTask=0;
		int sumTaskNotFinish=0;
		if(num.equals("1")) {
			for(int i=0;i<7;i++) {
				int tomato=sumdao.sumTomato(date, month, year, id);
				sumTomato=sumTomato+tomato;
				int task=sumdao.sumTask(date, month, year, id);
				sumTask=sumTask+task;
				int sumdate=tomato+task;
				sum.add((Integer)sumdate);
				int notTask=sumdao.sumTaskNotFinish(date, month, year, id);
				sumTaskNotFinish=sumTaskNotFinish+notTask;
				int date1=Integer.parseInt(date);
				int month1=Integer.parseInt(month);
				int year1=Integer.parseInt(year);
				date1--;
				if(date1==0) {
					month1--;
					if(month1==0) {
						year1--;
					}
					if(month1==11||month1==9||month1==6||month1==4) {
						date1=30;
					}else if(month1==2) {
						if(((year1%4)==0&&(year1%100)!=0)||(year1%400==0)) {   
							date1=28;
						}else {
							date1=29;
						}
					}else {
						date1=31;
					}
					
				}
				if(date1==9) {
					date="09";
				}else if(date1==8) {
					date="08";
				}else if(date1==7) {
					date="07";
				}else if(date1==6) {
					date="06";
				}else if(date1==5) {
					date="05";
				}else if(date1==4) {
					date="04";
				}else if(date1==3) {
					date="03";
				}else if(date1==2) {
					date="02";
				}else if(date1==1) {
					date="01";
				}else {
					date=String.valueOf(date1);
				}
				if(month1==1) {
					month="01";
				}else if(month1==2) {
					month="02";
				}
				else if(month1==3) {
					month="03";
				}
				else if(month1==4) {
					month="04";
				}
				else if(month1==5) {
					month="05";
				}
				else if(month1==6) {
					month="06";
				}
				else if(month1==7) {
					month="07";
				}
				else if(month1==8) {
					month="08";
				}
				else if(month1==9) {
					month="09";
				}else {
					month=String.valueOf(month1);
				}
				year=String.valueOf(year1);
			}
		}else if(num.equals("2")) {
			for(int i=0;i<30;i++) {
				int tomato=sumdao.sumTomato(date, month, year, id);
				sumTomato=sumTomato+tomato;
				int task=sumdao.sumTask(date, month, year, id);
				sumTask=sumTask+task;
				int sumdate=tomato+task;
				sum.add((Integer)sumdate);
				int notTask=sumdao.sumTaskNotFinish(date, month, year, id);
				sumTaskNotFinish=sumTaskNotFinish+notTask;
				int date1=Integer.parseInt(date);
				int month1=Integer.parseInt(month);
				int year1=Integer.parseInt(year);
				date1--;
				if(date1==0) {
					month1--;
					if(month1==0) {
						year1--;
					}
					if(month1==11||month1==9||month1==6||month1==4) {
						date1=30;
					}else if(month1==2) {
						if(((year1%4)==0&&(year1%100)!=0)||(year1%400==0)) {   
							date1=28;
						}else {
							date1=29;
						}
					}else {
						date1=31;
					}
					
				}
				if(date1==9) {
					date="09";
				}else if(date1==8) {
					date="08";
				}else if(date1==7) {
					date="07";
				}else if(date1==6) {
					date="06";
				}else if(date1==5) {
					date="05";
				}else if(date1==4) {
					date="04";
				}else if(date1==3) {
					date="03";
				}else if(date1==2) {
					date="02";
				}else if(date1==1) {
					date="01";
				}else {
					date=String.valueOf(date1);
				}
				if(month1==1) {
					month="01";
				}else if(month1==2) {
					month="02";
				}
				else if(month1==3) {
					month="03";
				}
				else if(month1==4) {
					month="04";
				}
				else if(month1==5) {
					month="05";
				}
				else if(month1==6) {
					month="06";
				}
				else if(month1==7) {
					month="07";
				}
				else if(month1==8) {
					month="08";
				}
				else if(month1==9) {
					month="09";
				}else {
					month=String.valueOf(month1);
				}
				year=String.valueOf(year1);
				
			}
		}
		else if(num.equals("3")) {
			for(int i=0;i<12;i++) {
				int tomato=sumdao.sumTomatoYear(month, year, id);
				sumTomato=sumTomato+tomato;
				System.out.println(tomato+"tomato");
				int task=sumdao.sumTaskYear(month, year, id);
				sumTask=sumTask+task;
				System.out.println(task+"task");
				int sumdate=tomato+task;
				sum.add((Integer)sumdate);
				int notTask=sumdao.sumTaskYearNotFinish(month, year, id);
				sumTaskNotFinish=sumTaskNotFinish+notTask;
				int month1=Integer.parseInt(month);
				month1--;
				if(month1==0) {
					int year1=Integer.parseInt(year);
					year1--;
					year=String.valueOf(year1);
				}
				if(month1==1) {
					month="01";
				}else if(month1==2) {
					month="02";
				}
				else if(month1==3) {
					month="03";
				}
				else if(month1==4) {
					month="04";
				}
				else if(month1==5) {
					month="05";
				}
				else if(month1==6) {
					month="06";
				}
				else if(month1==7) {
					month="07";
				}
				else if(month1==8) {
					month="08";
				}
				else if(month1==9) {
					month="09";
				}else {
					month=String.valueOf(month1);
				}
			}
			
		}
		
		JSONArray array=new JSONArray();
		for(Integer i:sum) {
			JSONObject obj=new JSONObject();
			obj.put("sum", i);
			array.put(obj);
		}
		JSONObject obj1=new JSONObject();
		obj1.put("sumTomato", sumTomato);
		array.put(obj1);
		JSONObject obj2=new JSONObject();
		obj2.put("sumTask", sumTask);
		array.put(obj2);
		JSONObject obj3=new JSONObject();
		obj3.put("sumTaskNotFinish", sumTaskNotFinish);
		array.put(obj3);
		response.getWriter().append(array.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
