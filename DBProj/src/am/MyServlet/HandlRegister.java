package am.MyServlet;
import am.MyBean.*;

import java.sql.*;
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*;
import javax.swing.JOptionPane; 

public class HandlRegister extends HttpServlet{
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
		//request.setCharacterEncoding("UTF-8");
		System.out.println("Reg DoPost...");
		Connection con;
		PreparedStatement sql; 
		ResultSet rs;
		String backNews="";
		Register registerbean=new Register();
		request.setAttribute("register", registerbean);
		String strid=request.getParameter("id").trim();
		
	    int id = 0;
	    boolean isnum=true;
	    if (strid != null && !"".equals(strid))
	    {
	    	 for(int i=0;i<strid.length();i++)
	 	    {
	 	    	if(!(strid.charAt(i)<='9'&&strid.charAt(i)>='0'))//判断账号是否为数字
	 	    	{
	 	    		isnum=false;
	 	    	}
	 	    	else
	 	    	{
	 	    		id = Integer.parseInt(strid);
	 	    	}
	 	    }
	    }
	
		String password=request.getParameter("password").trim();
		String passwordcon=request.getParameter("passwordcon").trim();
		String name=request.getParameter("name").trim();
		
		System.out.println("Name: " + name);
		name = handleString(name);
		System.out.println("New Name: " + name);
		
		boolean coin=password.equals(passwordcon);//判断两次密码输入是否一致
		boolean boo=password.length()>0&&coin&&isnum&&id!=0;//判断是否符合注册
		
		System.out.println("Id: " + id);
		System.out.println("Password: " + password);
		
	    String URL="jdbc:postgresql://localhost:5432/postgres";
		String USER = "postgres";
		String PASSWORD = "WUHAI9822";
		try
		{
			System.out.println("Connection...... ");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connection: " + con);
			String condition="";
			String cts = handleString(request.getParameter("cts"));
			System.out.println("cts: " + cts);
			if(cts.equals("学生注册"))
			{
				condition="insert into student(id,name,password) values(?,?,?)";
				System.out.println("学生");
			}
			else if(cts.equals("教师注册"))
			{
				condition="insert into teacher(id,name,password) values(?,?,?)";
				System.out.println("教师");
			}
			else
			{
				condition="insert into company(id,companyname,password) values(?,?,?)";
				System.out.println("企业" );
			}
			sql=con.prepareStatement(condition);
			
			if(boo)
			{
				System.out.println("Insert: " );
				sql.setInt(1, id);
				sql.setString(2,name);
				sql.setString(3,password);
				int m=sql.executeUpdate();
				if(m!=0)
				{
					backNews="注册成功！";
					registerbean.setBackNews(backNews);
					registerbean.setId(id);
					registerbean.setName(name);
					registerbean.setPassword(password);
					RequestDispatcher dispatcher=request.getRequestDispatcher("showreg.jsp");	
					dispatcher.forward(request, response);
				}
				
			}
			else if(!isnum)
			{
				backNews="账号不能包含非法字符！";
				registerbean.setBackNews(backNews);
				JOptionPane.showMessageDialog(null, "账号不能包含非法字符！", "格式输入错误", JOptionPane.ERROR_MESSAGE); 
				RequestDispatcher dispatcher=request.getRequestDispatcher("register.jsp");	
				dispatcher.forward(request, response);
			}
			else 
			{
				if(!coin)
				{
					backNews="两次密码输入不一致！";
					registerbean.setBackNews(backNews);
					JOptionPane.showMessageDialog(null, "两次密码输入不一致！", "格式输入错误", JOptionPane.ERROR_MESSAGE); 
				}
				else
				{
					backNews="输入不能为空！";
					registerbean.setBackNews(backNews);
					JOptionPane.showMessageDialog(null, "输入不能为空！", "格式输入错误", JOptionPane.ERROR_MESSAGE); 
					
				}
			
				RequestDispatcher dispatcher=request.getRequestDispatcher("register.jsp");	
				dispatcher.forward(request, response);
				
			}
			con.close();
			
			
		}catch(SQLException exp)
		{
			backNews="该账号已被注册"+exp;
			registerbean.setBackNews(backNews);
			JOptionPane.showMessageDialog(null, "该账号已被注册", "格式输入错误", JOptionPane.ERROR_MESSAGE); 
			RequestDispatcher dispatcher=request.getRequestDispatcher("register.jsp");	
			dispatcher.forward(request, response);
		}
			
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		doPost(request,response);
	}

}
