package am.MyServlet;
import am.MyBean.*;

import java.sql.*;
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*;
import javax.swing.JOptionPane; 

public class HandleModinfo extends HttpServlet{
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
		String choice=handleString(request.getParameter("Button"));
		if(choice.equals("确定"))
		{

			if(type==1)//学生
			{
				Student sbean=new Student();
				session.setAttribute("sbean", sbean);
				String name=handleString(request.getParameter("name").trim());
				String gender=handleString(request.getParameter("gender").trim());
				String strage=handleString(request.getParameter("age").trim());
				String backNews="";
				int age= Integer.parseInt(strage);
				String institute=handleString(request.getParameter("institute").trim());
				String tel=handleString(request.getParameter("tel").trim());	
				String password=handleString(request.getParameter("password").trim());
				String passwordcon=handleString(request.getParameter("passwordcon").trim());
				boolean coin=password.equals(passwordcon)&&password.length()>0;//判断两次密码输入是否一致
				
				if(coin)
				{
					try {
						con = DriverManager.getConnection(URL, USER, PASSWORD);
						condition="update student set name=?, gender=?, age=?, institute=?, tel=?, password=? where id='"+id+"'";
						sql=con.prepareStatement(condition);
						sql.setString(1, name);
						sql.setString(2, gender);
						sql.setInt(3,age);
						sql.setString(4, institute);
						sql.setString(5, tel);
						sql.setString(6, password);		
						int m=sql.executeUpdate();
						if(m==1)
						{
							backNews="修改信息成功";
							sbean.setBackNews(backNews);
							sbean.setName(name);
							sbean.setAge(age);
							sbean.setGender(gender);
							sbean.setInstitute(institute);
							sbean.setTel(tel);
							sbean.setPassword(password);
							JOptionPane.showMessageDialog(null, "修改信息成功！", "提示消息", JOptionPane.ERROR_MESSAGE); 
							RequestDispatcher dispatcher=request.getRequestDispatcher("personal.jsp");
							dispatcher.forward(request, response);
							
							
						}
						else
						{
							backNews="输入不能为空或信息含有非法字符";
							sbean.setBackNews(backNews);
							JOptionPane.showMessageDialog(null, "输入不能为空或信息含有非法字符！", "格式输入错误", JOptionPane.ERROR_MESSAGE); 
							RequestDispatcher dispatcher=request.getRequestDispatcher("s_modinfo.jsp");
							dispatcher.forward(request, response);
						}
						con.close();
						
						
					}catch(SQLException exp)
					{
					
						sbean.setBackNews(""+exp);
					}
				}
				else
				{
					backNews="两次密码输入不能为空且要保持一致！";
					sbean.setBackNews(backNews);
					JOptionPane.showMessageDialog(null, "两次密码输入不能为空且要保持一致！", "格式输入错误", JOptionPane.ERROR_MESSAGE); 
					RequestDispatcher dispatcher=request.getRequestDispatcher("s_modinfo.jsp");
					dispatcher.forward(request, response);
				}
				
				
				
			}
			
			
			
			else if(type==2)//老师
			{
				Teacher tbean=new Teacher();
				request.setAttribute("tbean", tbean);
				String name=handleString(request.getParameter("name").trim());
				String gender=handleString(request.getParameter("gender").trim());
				String strage=handleString(request.getParameter("age").trim());
				String backNews="";
				int age= Integer.parseInt(strage);
				String institute=handleString(request.getParameter("institute").trim());
				String tel=handleString(request.getParameter("tel").trim());	
				String password=handleString(request.getParameter("password").trim());
				String passwordcon=handleString(request.getParameter("passwordcon").trim());
				String title=handleString(request.getParameter("title").trim());
				boolean coin=password.equals(passwordcon)&&password.length()>0;//判断两次密码输入是否一致
				
				if(coin)
				{
					try {
						con = DriverManager.getConnection(URL, USER, PASSWORD);
						condition="update teacher set name=?, gender=?, age=?, institute=?, tel=?, password=?,title=? where id='"+id+"'";
						sql=con.prepareStatement(condition);
						sql.setString(1, name);
						sql.setString(2, gender);
						sql.setInt(3,age);
						sql.setString(4, institute);
						sql.setString(5, tel);
						sql.setString(6, password);		
						sql.setString(7, title);
				
						int m=sql.executeUpdate();
						if(m==1)
						{
							backNews="修改信息成功";
							tbean.setBackNews(backNews);
							tbean.setName(name);
							tbean.setAge(age);
						    tbean.setGender(gender);
							tbean.setInstitute(institute);
							tbean.setTel(tel);
							tbean.setTitle(title);
							tbean.setPassword(password);
							JOptionPane.showMessageDialog(null, "修改信息成功！", "提示消息", JOptionPane.ERROR_MESSAGE); 
							RequestDispatcher dispatcher=request.getRequestDispatcher("personal.jsp");
							dispatcher.forward(request, response);
							
						}
						else
						{
							backNews="输入不能为空或信息含有非法字符";
							tbean.setBackNews(backNews);
							JOptionPane.showMessageDialog(null, "输入不能为空或信息含有非法字符！", "格式输入错误", JOptionPane.ERROR_MESSAGE); 
							RequestDispatcher dispatcher=request.getRequestDispatcher("t_modinfo.jsp");
							dispatcher.forward(request, response);
						}
						con.close();
						
						
					}catch(SQLException exp)
					{
					
						tbean.setBackNews(""+exp);
					}
				}
				else
				{
					backNews="两次密码输入不能为空且要保持一致！";
					tbean.setBackNews(backNews);
					JOptionPane.showMessageDialog(null, "两次密码输入不能为空且要保持一致！", "格式输入错误", JOptionPane.ERROR_MESSAGE); 
					RequestDispatcher dispatcher=request.getRequestDispatcher("t_modinfo.jsp");
					dispatcher.forward(request, response);
				}
				
				

				
			}
			
		
			else//企业
			{
				System.out.println("进入 ");
				Company cbean=new Company();
				request.setAttribute("cbean", cbean);
				System.out.println("创建 ");
				String name=handleString(request.getParameter("companyname").trim());
				String backNews="";
				String tel=handleString(request.getParameter("tel").trim());	
				String password=handleString(request.getParameter("password").trim());
				String passwordcon=handleString(request.getParameter("passwordcon").trim());
				boolean coin=password.equals(passwordcon)&&password.length()>0;//判断两次密码输入是否一致
				System.out.println(coin);
				
				
				if(coin)
				{
					try {
						System.out.println("Connection......企业 ");
						con = DriverManager.getConnection(URL, USER, PASSWORD);
						condition="update company set companyname=?, tel=?, password=? where id='"+id+"'";
						sql=con.prepareStatement(condition);
						sql.setString(1, name);
						sql.setString(2, tel);
						sql.setString(3, password);		
						int m=sql.executeUpdate();
						if(m==1)
						{
							backNews="修改信息成功";
							cbean.setBackNews(backNews);
							cbean.setCompanyname(name);
							cbean.setTel(tel);
							cbean.setPassword(password);
							JOptionPane.showMessageDialog(null, "修改信息成功！", "提示消息", JOptionPane.ERROR_MESSAGE); 
							RequestDispatcher dispatcher=request.getRequestDispatcher("personal.jsp");
							dispatcher.forward(request, response);
							
						}
						else
						{
							backNews="输入不能为空或信息含有非法字符";
							cbean.setBackNews(backNews);
							JOptionPane.showMessageDialog(null, "输入不能为空或信息含有非法字符！", "格式输入错误", JOptionPane.ERROR_MESSAGE); 
							RequestDispatcher dispatcher=request.getRequestDispatcher("c_modinfo.jsp");
							dispatcher.forward(request, response);
						}
						con.close();
						
						
					}catch(SQLException exp)
					{
					
						cbean.setBackNews(""+exp);
					}
				}
				else
				{
					backNews="两次密码输入不能为空且要保持一致！";
					cbean.setBackNews(backNews);
					JOptionPane.showMessageDialog(null, "两次密码输入不能为空且要保持一致！", "格式输入错误", JOptionPane.ERROR_MESSAGE); 
					RequestDispatcher dispatcher=request.getRequestDispatcher("c_modinfo.jsp");
					dispatcher.forward(request, response);
				}
				

			}
	
			
		}
		else if(choice.equals("取消"))
		{
			 RequestDispatcher dispatcher=request.getRequestDispatcher("personal.jsp");
			 dispatcher.forward(request, response);
		}

		
	}
	

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		doPost(request,response);
	}

}
