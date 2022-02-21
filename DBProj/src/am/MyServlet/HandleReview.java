package am.MyServlet;
import am.MyBean.*;

import java.sql.*;
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*;
import javax.swing.JOptionPane; 

public class HandleReview extends HttpServlet{
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
		int pass=0;//等待审核
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
	    try
	    {
	    	System.out.println("Connection...... ");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connection: " + con);
			condition="select campus,building,room,volume,date,time,name,classroomtime.infoid from student,classroom,classroomtime,sborrowlist where sborrowlist.infoid=classroomtime.infoid and classroom.roomid=classroomtime.roomid and sborrowlist.id=student.id and pass=0 and type=1";
			sql=con.prepareStatement(condition);	
		    rs=sql.executeQuery();
		    result.append("<form action=\"try_pass\" method=post><table border=\"1\"><tr><th width=\"20\"></th><th width=\"80\">校区</th><th width=\"80\">教学楼</th><th width=\"80\">教室</th><th width=\"80\">容纳人数</th><th width=\"80\" >日期</th><th width=\"80\" >时间</th><th width=\"80\" >用户</th><th width=\"80\" >类别</th> </tr>");
		    String type="学生";
		    while(rs.next())//学生申请信息
		    {
		    	String rr=rs.getInt(8)+"";//infoid
		    	
		    	result.append("<tr>");
		    	result.append("<td><input type=\"checkbox\" name=\"apply\" value="+rr+"></td>");    	
		    	result.append("<td>"+rs.getString(1)+"</td>");
		    	result.append("<td>"+rs.getString(2)+"</td>");
		    	result.append("<td>"+rs.getString(3)+"</td>");
		    	result.append("<td>"+rs.getInt(4)+"</td>");
		    	result.append("<td>"+rs.getString(5)+"</td>");
		    	result.append("<td>"+rs.getString(6)+"</td>");
		    	result.append("<td>"+rs.getString(7)+"</td>");
		    	result.append("<td>"+type+"</td>");
		    	result.append("</tr>");
		    	
		    }
		    
		    condition="select campus,building,room,volume,date,time,name,classroomtime.infoid from teacher,classroom,classroomtime,tborrowlist where tborrowlist.infoid=classroomtime.infoid and classroom.roomid=classroomtime.roomid and tborrowlist.id=teacher.id and pass=0 and type=2";
			sql=con.prepareStatement(condition);	
		    rs=sql.executeQuery();
		    type="教师";
		    while(rs.next())//教师申请信息
		    {
		    	String rr=rs.getInt(8)+"";//infoid
		    	
		    	result.append("<tr>");
		    	result.append("<td><input type=\"checkbox\" name=\"apply\" value="+rr+"></td>");	    	
		    	result.append("<td>"+rs.getString(1)+"</td>");
		    	result.append("<td>"+rs.getString(2)+"</td>");
		    	result.append("<td>"+rs.getString(3)+"</td>");
		    	result.append("<td>"+rs.getInt(4)+"</td>");
		    	result.append("<td>"+rs.getString(5)+"</td>");
		    	result.append("<td>"+rs.getString(6)+"</td>");
		    	result.append("<td>"+rs.getString(7)+"</td>");
		    	result.append("<td>"+type+"</td>");
		    	result.append("</tr>");
		    	
		    }
		    
		    condition="select campus,building,room,volume,date,time,companyname,classroomtime.infoid from company,classroom,classroomtime,borrowlist where borrowlist.infoid=classroomtime.infoid and classroom.roomid=classroomtime.roomid and borrowlist.id=company.id and pass=0 and type=3";
			sql=con.prepareStatement(condition);	
		    rs=sql.executeQuery();
		    type="企业";
		    while(rs.next())//企业申请信息
		    {
		    	String rr=rs.getInt(8)+"";//infoid
		    	
		    	result.append("<tr>");
		    	result.append("<td><input type=\"checkbox\" name=\"apply\" value="+rr+"></td>");	    	
		    	result.append("<td>"+rs.getString(1)+"</td>");
		    	result.append("<td>"+rs.getString(2)+"</td>");
		    	result.append("<td>"+rs.getString(3)+"</td>");
		    	result.append("<td>"+rs.getInt(4)+"</td>");
		    	result.append("<td>"+rs.getString(5)+"</td>");
		    	result.append("<td>"+rs.getString(6)+"</td>");
		    	result.append("<td>"+rs.getString(7)+"</td>");
		    	result.append("<td>"+type+"</td>");
		    	result.append("</tr>");
		    	
		    }
		        
		    result.append("</table><br><input type=\"submit\" name=\"Button\" value=\"批准租借\"/> <input type=\"submit\" name=\"Button\" value=\"不予通过\" /><input type=\"submit\" name=\"Button\" value=\"返回主页\" /></form>");
		    System.out.println(result);
		    resultbean.setResult(result);
		    con.close();
		    RequestDispatcher dispatcher=request.getRequestDispatcher("management.jsp");
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
