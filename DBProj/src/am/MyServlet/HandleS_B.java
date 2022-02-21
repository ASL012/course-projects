package am.MyServlet;
import am.MyBean.*;

import java.sql.*;
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*;
import javax.swing.JOptionPane; 

public class HandleS_B extends HttpServlet{
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
		int pass=1;
		Connection con;
		String condition="";
		PreparedStatement sql; 
		ResultSet rs;		
		StringBuffer result=new StringBuffer();
	    Result resultbean=new Result();
	    request.setAttribute("resultbean", resultbean);
	    
	    
		String URL="jdbc:postgresql://localhost:5432/postgres";
		String USER = "postgres";
		String PASSWORD = "WUHAI9822";
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			if(type==1)
			{
				condition="select campus,building,room,volume,date,time,pass from classroom,classroomtime,sborrowlist where sborrowlist.infoid=classroomtime.infoid and classroomtime.roomid=classroom.roomid and id=? and type=? and pass!=?";
				sql=con.prepareStatement(condition);
				sql.setInt(1, id);
				sql.setInt(2, type);
				sql.setInt(3, pass);
				rs=sql.executeQuery();
				result.append("<table border=\"1\"><tr><th width=\"80\">校区</th><th width=\"80\">教学楼</th><th width=\"80\">教室</th><th width=\"80\">容纳人数</th><th width=\"80\" >日期</th><th width=\"80\" >时间</th><th width=\"80\">审核状态</th> </tr>");
			    while(rs.next())
			    {
			    	String rr=rs.getInt(7)+"";//pass
			    	result.append("<tr>");
			    	result.append("<td>"+rs.getString(1)+"</td>");
			    	result.append("<td>"+rs.getString(2)+"</td>");
			    	result.append("<td>"+rs.getString(3)+"</td>");
			    	result.append("<td>"+rs.getInt(4)+"</td>");
			    	result.append("<td>"+rs.getString(5)+"</td>");
			    	result.append("<td>"+rs.getString(6)+"</td>");
			    	int status=rs.getInt(7);
			    	String ss="";
			    	if(status==0)
			    	{
			    		ss="审核中";
			    	}
			    	else
			    	{
			    		ss="已拒绝";
			    	}
			    	result.append("<td>"+ss+"</td>");
			    	result.append("</tr>");
			    	
			    }
			    result.append("</table>");
			    resultbean.setResult(result);
			}
			else if(type==2)
			{
				condition="select campus,building,room,volume,date,time,pass from classroom,classroomtime,tborrowlist where tborrowlist.infoid=classroomtime.infoid and classroomtime.roomid=classroom.roomid and id=? and type=? and pass!=?";
				sql=con.prepareStatement(condition);
				sql.setInt(1, id);
				sql.setInt(2, type);
				sql.setInt(3, pass);
				rs=sql.executeQuery();
				result.append("<table border=\"1\"><tr><th width=\"80\">校区</th><th width=\"80\">教学楼</th><th width=\"80\">教室</th><th width=\"80\">容纳人数</th><th width=\"80\" >日期</th><th width=\"80\" >时间</th><th width=\"80\">审核状态</th> </tr>");
			    while(rs.next())
			    {
			    	String rr=rs.getInt(7)+"";//infoid
			    	result.append("<tr>");
			    	result.append("<td>"+rs.getString(1)+"</td>");
			    	result.append("<td>"+rs.getString(2)+"</td>");
			    	result.append("<td>"+rs.getString(3)+"</td>");
			    	result.append("<td>"+rs.getInt(4)+"</td>");
			    	result.append("<td>"+rs.getString(5)+"</td>");
			    	result.append("<td>"+rs.getString(6)+"</td>");
			    	int status=rs.getInt(7);
			    	String ss="";
			    	if(status==0)
			    	{
			    		ss="审核中";
			    	}
			    	else
			    	{
			    		ss="已拒绝";
			    	}
			    	result.append("<td>"+ss+"</td>");
			    	result.append("</tr>");
			    	
			    }
			    result.append("</table>");
			    resultbean.setResult(result);
			}
			else
			{
				condition="select campus,building,room,volume,date,time,pass from classroom,classroomtime,borrowlist where borrowlist.infoid=classroomtime.infoid and classroomtime.roomid=classroom.roomid and id=? and type=? and pass!=?";
				sql=con.prepareStatement(condition);
				sql.setInt(1, id);
				sql.setInt(2, type);
				sql.setInt(3, pass);
				rs=sql.executeQuery();
				result.append("<table border=\"1\"><tr><th width=\"80\">校区</th><th width=\"80\">教学楼</th><th width=\"80\">教室</th><th width=\"80\">容纳人数</th><th width=\"80\" >日期</th><th width=\"80\" >时间</th><th width=\"80\">审核状态</th> </tr>");
			    while(rs.next())
			    {
			    	String rr=rs.getInt(7)+"";//infoid
			    	result.append("<tr>");
			    	result.append("<td>"+rs.getString(1)+"</td>");
			    	result.append("<td>"+rs.getString(2)+"</td>");
			    	result.append("<td>"+rs.getString(3)+"</td>");
			    	result.append("<td>"+rs.getInt(4)+"</td>");
			    	result.append("<td>"+rs.getString(5)+"</td>");
			    	result.append("<td>"+rs.getString(6)+"</td>");
			    	int status=rs.getInt(7);
			    	String ss="";
			    	if(status==0)
			    	{
			    		ss="审核中";
			    	}
			    	else
			    	{
			    		ss="已拒绝";
			    	}
			    	result.append("<td>"+ss+"</td>");
			    	result.append("</tr>");
			    	
			    }
			    result.append("</table>");
			    resultbean.setResult(result);
			}

		    con.close();
		    RequestDispatcher dispatcher=request.getRequestDispatcher("searchbook.jsp");
			dispatcher.forward(request, response);
				
			
		}catch(SQLException exp)
		{
			System.out.println(exp);
			
		}
		
		
		
		
		
		
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		doPost(request,response);
	}

}
