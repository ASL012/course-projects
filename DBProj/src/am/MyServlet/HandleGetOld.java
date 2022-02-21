package am.MyServlet;
import am.MyBean.*;

import java.sql.*;
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*;
import javax.swing.JOptionPane; 

public class HandleGetOld extends HttpServlet{
	public void init(ServletConfig config) throws ServletException{
		super.init();
		try{ 
			Class.forName("org.postgresql.Driver"); 
		}catch(Exception e){} 	
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

		HttpSession session=request.getSession(true);
		Login loginbean=(Login)session.getAttribute("login");
		int id=loginbean.getId();
		int type=loginbean.getType();
		Connection con;
		String condition="";
		PreparedStatement sql; 
		ResultSet rs;	
	
		
		String URL="jdbc:postgresql://localhost:5432/postgres";
	    String USER = "postgres";
	    String PASSWORD = "WUHAI9822";
	    if(type==1)//学生
	    {
	    	Student sbean=new Student();
			session.setAttribute("sbean", sbean);
	    	try
		    {
		    	System.out.println("Connection...... ");
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				System.out.println("Connection: " + con);
				condition="select * from student where id='"+id+"'";
			    sql=con.prepareStatement(condition);
			    rs=sql.executeQuery();
			    if(rs.next()) {
			    	sbean.setName(rs.getString(3));
			    	sbean.setGender(rs.getString(4));
			    	sbean.setAge(rs.getInt(5));
			    	sbean.setInstitute(rs.getString(6));
			    	sbean.setTel(rs.getString(7));
			    }
					
		    }catch(SQLException exp)
		    {
		    	sbean.setBackNews(""+exp);
		    }
		    RequestDispatcher dispatcher=request.getRequestDispatcher("s_modinfo.jsp");
			dispatcher.forward(request, response);
	    }
	    else if(type==2)//老师
	    {
	    	Teacher tbean=new Teacher();
			request.setAttribute("tbean", tbean);
	    	try
		    {
		    	System.out.println("Connection...... ");
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				System.out.println("Connection: " + con);
				condition="select * from teacher where id='"+id+"'";
			    sql=con.prepareStatement(condition);
			    rs=sql.executeQuery();
			    if(rs.next()) {
			    	tbean.setName(rs.getString(3));
			    	tbean.setGender(rs.getString(4));
			    	tbean.setAge(rs.getInt(5));
			    	tbean.setInstitute(rs.getString(6));
			    	tbean.setTitle(rs.getString(7));
			    	tbean.setTel(rs.getString(8));
			    }
					
		    }catch(SQLException exp)
		    {
		    	tbean.setBackNews(""+exp);
		    }
		    RequestDispatcher dispatcher=request.getRequestDispatcher("t_modinfo.jsp");
			dispatcher.forward(request, response);
	    	
	    	
	    }
	    else//企业
	    {
	    	Company cbean=new Company();
			request.setAttribute("cbean", cbean);
	    	try
		    {
		    	System.out.println("Connection...... ");
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				System.out.println("Connection: " + con);
				condition="select * from company where id='"+id+"'";
			    sql=con.prepareStatement(condition);
			    rs=sql.executeQuery();
			    if(rs.next()) {
			    	cbean.setCompanyname(rs.getString(3));
			    	cbean.setTel(rs.getString(4));
			    }
					
		    }catch(SQLException exp)
		    {
		    	cbean.setBackNews(""+exp);
		    }
		    RequestDispatcher dispatcher=request.getRequestDispatcher("c_modinfo.jsp");
			dispatcher.forward(request, response);
	    	
	    }
		
	
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		doPost(request,response);
	}

}
