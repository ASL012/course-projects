package am.MyServlet;
import am.MyBean.*;
import java.sql.*;
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*;
import javax.swing.JOptionPane; 

public class HandleSearch extends HttpServlet{
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
		if(choice.equals("查询"))
		{
			HttpSession session=request.getSession(true);
			Login loginbean=(Login)session.getAttribute("login");
			int id=loginbean.getId();
			int type=loginbean.getType();
			Connection con;
			String condition="";
			PreparedStatement sql; 
			ResultSet rs;
			StringBuffer result=new StringBuffer();
		    Result resultbean=new Result();
		    request.setAttribute("resultbean", resultbean);
		   
			String campus=handleString(request.getParameter("campus"));
			String building=handleString(request.getParameter("building"));
			int volume=Integer.parseInt(handleString(request.getParameter("volume")));
			String date=handleString(request.getParameter("date"));
			String time=handleString(request.getParameter("time"));
			
			
			String URL="jdbc:postgresql://localhost:5432/postgres";
		    String USER = "postgres";
		    String PASSWORD = "WUHAI9822";
		    try
		    {
		    	System.out.println("Connection...... ");
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				System.out.println("Connection: " + con);
				condition="select campus,building,room,volume,date,time,infoid from classroom, classroomtime where classroom.roomid=classroomtime.roomid and state=1 and campus=? and building=? and volume=? and date=? and time=? ";
				sql=con.prepareStatement(condition);
				sql.setString(1, campus);
				sql.setString(2, building);
				sql.setInt(3, volume);
				sql.setString(4, date);
				sql.setString(5, time);
			    rs=sql.executeQuery();
			    result.append("<form action=\"try_book\" method=post><table border=\"1\"><tr><th width=\"20\"></th><th width=\"80\">校区</th><th width=\"80\">教学楼</th><th width=\"80\">教室</th><th width=\"80\">容纳人数</th><th width=\"80\" >日期</th><th width=\"80\" >时间</th> </tr>");
			    
			    while(rs.next())
			    {
			    	String rr=rs.getInt(7)+"";//infoid
			    	result.append("<tr>");
			    	result.append("<td><input type=\"radio\" name=\"book\" value="+rr+"></td>");
			    	result.append("<td>"+rs.getString(1)+"</td>");
			    	result.append("<td>"+rs.getString(2)+"</td>");
			    	result.append("<td>"+rs.getString(3)+"</td>");
			    	result.append("<td>"+rs.getInt(4)+"</td>");
			    	result.append("<td>"+rs.getString(5)+"</td>");
			    	result.append("<td>"+rs.getString(6)+"</td>");
			    	result.append("</tr>");
			    	
			    }
			    result.append("</table><br><input type=\"submit\" name=\"apply\" value=\"申请预约\"/></form>");
			    System.out.println(result);
			    resultbean.setResult(result);
			    con.close();
			    RequestDispatcher dispatcher=request.getRequestDispatcher("searchroom.jsp");
				dispatcher.forward(request, response);
			    
			
		    }catch(SQLException exp)
		    {
		    	System.out.println(exp);
		    }
		    
		}
		else if(choice.equals("返回主页"))
		{
			 RequestDispatcher dispatcher=request.getRequestDispatcher("index.jsp");
			 dispatcher.forward(request, response);
		}
		else if(choice.equals("返回个人中心"))
		{
			RequestDispatcher dispatcher=request.getRequestDispatcher("personal.jsp");
			 dispatcher.forward(request, response);
			
		}
		
	
		
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		doPost(request,response);
	}

	

}
