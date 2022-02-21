package am.MyServlet;
import am.MyBean.*;

import java.sql.*;
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*;
import javax.swing.JOptionPane; 

public class HandleBook extends HttpServlet{
	
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
		int id=loginbean.getId();
		int type=loginbean.getType();
		String strinfoid=handleString(request.getParameter("book"));
		int infoid=Integer.parseInt(strinfoid);
		int pass=0;//0为等待审核，1为通过，2为被拒绝
		int state=0;//0表示被预约或租借，1为可预约
		
		Connection con;
		String condition="";
		PreparedStatement sql; 
		ResultSet rs;		
		String URL="jdbc:postgresql://localhost:5432/postgres";
		String USER = "postgres";
		String PASSWORD = "WUHAI9822";
		int m;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			
			if(type==1)
			{
				condition="insert into sborrowlist values(?,?,?,?)";
				sql=con.prepareStatement(condition);
				sql.setInt(1, id);
				sql.setInt(2, type);
				sql.setInt(3,infoid);
				sql.setInt(4,pass);
				m=sql.executeUpdate();
			}
			else if(type==2)
			{
				condition="insert into tborrowlist values(?,?,?,?)";
				sql=con.prepareStatement(condition);
				sql.setInt(1, id);
				sql.setInt(2, type);
				sql.setInt(3,infoid);
				sql.setInt(4,pass);
				m=sql.executeUpdate();
			}
			else
			{
				condition="insert into borrowlist values(?,?,?,?)";
				sql=con.prepareStatement(condition);
				sql.setInt(1, id);
				sql.setInt(2, type);
				sql.setInt(3,infoid);
				sql.setInt(4,pass);
				m=sql.executeUpdate();
			}
			
		
			if(m==1)
			{
				m=0;
				condition="update classroomtime set state=? where infoid='"+infoid+"'";
				sql=con.prepareStatement(condition);
				sql.setInt(1, state);
				m=sql.executeUpdate();
				if(m==1)
				{
					JOptionPane.showMessageDialog(null, "预约成功！", "提示消息", JOptionPane.ERROR_MESSAGE); 
					RequestDispatcher dispatcher=request.getRequestDispatcher("personal.jsp");
					dispatcher.forward(request, response);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "预约失败！", "提示消息", JOptionPane.ERROR_MESSAGE); 
					RequestDispatcher dispatcher=request.getRequestDispatcher("searchroom.jsp");
					dispatcher.forward(request, response);
				}
			
			}
			else
			{
				JOptionPane.showMessageDialog(null, "预约失败！", "提示消息", JOptionPane.ERROR_MESSAGE); 
				RequestDispatcher dispatcher=request.getRequestDispatcher("searchroom.jsp");
				dispatcher.forward(request, response);
			}
			con.close();
			
		}catch(SQLException exp)
		{
			System.out.println(exp);
			
		}
		

		
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		doPost(request,response);
	}
	

}
