package am.MyServlet;
import am.MyBean.*;
import java.sql.*;
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*;
import javax.swing.JOptionPane; 

public class HandlePerson extends HttpServlet{
	public void init(ServletConfig config) throws ServletException{
		super.init();
		try{ 
			Class.forName("org.postgresql.Driver"); 
		}catch(Exception e){} 	
	}
	public String handleString(String s){
		try{
			String a=new String(s.getBytes("ISO-8859-1"),"UTF-8");
			s=new String(a);
		}
		catch(Exception ee){}
		return s;
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		HttpSession session=request.getSession(true);
		Login loginbean=(Login)session.getAttribute("login");
		boolean ok=true;
		if(loginbean==null)
		{
			ok=false;
			response.sendRedirect("login.jsp");//未登陆
		}
		if(ok==true)
		{
			continueWork(request,response);
		}
	}
	
	public void continueWork(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		String choice=handleString(request.getParameter("Button"));
		if(choice.equals("修改个人信息"))
		{
		    RequestDispatcher dispatcher=request.getRequestDispatcher("try_getold");
			dispatcher.forward(request, response);
		}
		else if(choice.equals("讲厅查询&租借"))
		{
			 RequestDispatcher dispatcher=request.getRequestDispatcher("searchroom.jsp");
			 dispatcher.forward(request, response);
		}
		else if(choice.equals("预约记录"))
		{
			RequestDispatcher dispatcher=request.getRequestDispatcher("try_sb");
			 dispatcher.forward(request, response);
			
		}
		else if(choice.equals("租借记录"))
		{
			RequestDispatcher dispatcher=request.getRequestDispatcher("try_sr");
			dispatcher.forward(request, response);
			
		}
		else
		{
			RequestDispatcher dispatcher=request.getRequestDispatcher("index.jsp");
			 dispatcher.forward(request, response);
		}
		
		
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		doPost(request,response);
	}
	


}
