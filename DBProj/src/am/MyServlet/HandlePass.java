package am.MyServlet;
import am.MyBean.*;

import java.sql.*;
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*;
import javax.swing.JOptionPane; 

public class HandlePass extends HttpServlet{
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
		Connection con;
		String condition="";
		PreparedStatement sql; 
		ResultSet rs;		
		String URL="jdbc:postgresql://localhost:5432/postgres";
		String USER = "postgres";
		String PASSWORD = "WUHAI9822";
		int pass=1;
		int state=1;
		
		int exist=0;//表示infoid存在于哪一个表

		String choice=handleString(request.getParameter("Button"));
		try
		{
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			if(choice.equals("批准租借"))
			{
				  String strinfoid[]=(request.getParameterValues("apply"));
				 
				  for(int i=0;i<strinfoid.length;i++)
				  {
					  int infoid=Integer.parseInt(strinfoid[i]);
					  condition="select count(*) num from sborrowlist where infoid='"+infoid+"'";
					  sql=con.prepareStatement(condition);
					  rs=sql.executeQuery();
					  if(rs.next())
					  {
						  exist=rs.getInt("num");
					  }
					  if(exist==1)
					  {
						  condition="update sborrowlist set pass=? where infoid='"+infoid+"'";
						  sql=con.prepareStatement(condition);
						  sql.setInt(1, pass);
						  int m=sql.executeUpdate();
					  }
					  else
					  {
						  condition="select count(*) num from tborrowlist where infoid='"+infoid+"'";
						  sql=con.prepareStatement(condition);
						  rs=sql.executeQuery();
						  if(rs.next())
						  {
							  exist=rs.getInt("num");
						  }
						  if(exist==1)
						  {
							  condition="update tborrowlist set pass=? where infoid='"+infoid+"'";
							  sql=con.prepareStatement(condition);
							  sql.setInt(1, pass);
							  int m=sql.executeUpdate();
						  }
						  else
						  {
							  condition="update borrowlist set pass=? where infoid='"+infoid+"'";
							  sql=con.prepareStatement(condition);
							  sql.setInt(1, pass);
							  int m=sql.executeUpdate();
							  
						  }
						  
					  }
					  exist=0;
				
				  }
				  JOptionPane.showMessageDialog(null, "批准成功！", "提示消息", JOptionPane.ERROR_MESSAGE); 
				  RequestDispatcher dispatcher=request.getRequestDispatcher("index.jsp");
				  dispatcher.forward(request, response);
			}
			else if(choice.equals("不予通过"))
			{ 

				  String strinfoid[]=(request.getParameterValues("apply"));
				  for(int i=0;i<strinfoid.length;i++)
				  {
					  	  
					  int infoid=Integer.parseInt(strinfoid[i]);
					  condition="select count(*) num from sborrowlist where infoid='"+infoid+"'";
					  sql=con.prepareStatement(condition);
					  rs=sql.executeQuery();
					  if(rs.next())
					  {
						  exist=rs.getInt("num");
					  }
					  if(exist==1)
					  {
						  condition="update sborrowlist set pass=? where infoid='"+infoid+"'";
						  sql=con.prepareStatement(condition);
						  pass=2;
						  sql.setInt(1, pass);
						  int n=sql.executeUpdate();
					  }
					  else
					  {
						  condition="select count(*) num from tborrowlist where infoid='"+infoid+"'";
						  sql=con.prepareStatement(condition);
						  rs=sql.executeQuery();
						  if(rs.next())
						  {
							  exist=rs.getInt("num");
						  }
						  if(exist==1)
						  {
							  condition="update tborrowlist set pass=? where infoid='"+infoid+"'";
							  sql=con.prepareStatement(condition);
							  pass=2;
							  sql.setInt(1, pass);
							  int n=sql.executeUpdate();
						  }
						  else
						  {
							  condition="update borrowlist set pass=? where infoid='"+infoid+"'";
							  sql=con.prepareStatement(condition);
							  pass=2;
							  sql.setInt(1, pass);
							  int n=sql.executeUpdate();
							  
						  }
						  
					  }
					  exist=0;	  	  
					  condition="update classroomtime set state=? where infoid='"+infoid+"'";
					  sql=con.prepareStatement(condition);
					  sql.setInt(1, state);
					  int n=sql.executeUpdate();
				  }
			
				  JOptionPane.showMessageDialog(null, "拒绝成功！", "提示消息", JOptionPane.ERROR_MESSAGE); 
				  RequestDispatcher dispatcher=request.getRequestDispatcher("index.jsp");
				  dispatcher.forward(request, response);
				
			}
			else
			{
				  RequestDispatcher dispatcher=request.getRequestDispatcher("index.jsp");
				  dispatcher.forward(request, response);
			}
			
		}catch(SQLException exp)
	    {
	    	System.out.println(exp);
	    }
	
		
	}
	
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		doPost(request,response);
	}
	

}
