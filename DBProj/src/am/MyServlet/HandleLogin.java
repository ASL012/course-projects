package am.MyServlet;
import am.MyBean.*;

import java.sql.*;
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*;
import javax.swing.JOptionPane; 

public class HandleLogin extends HttpServlet {
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		
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
		System.out.println("Login DoPost...");
		Connection con;
		PreparedStatement sql; 
		ResultSet rs;
		String backNews="";
		HttpSession session=request.getSession(true);
		Login loginbean=null;
		try {
			loginbean=(Login)session.getAttribute("login");
			if(loginbean==null)
			{
				loginbean=new Login();
				session.setAttribute("login", loginbean);
			}
		}catch(Exception ee)
		{
			loginbean=new Login();
			session.setAttribute("login", loginbean);
		}
	
	    String strid=request.getParameter("id").trim();
	    int id = 0;
	    if (strid != null && !"".equals(strid))
			id = Integer.parseInt(strid);
		String password=request.getParameter("password").trim();
		System.out.println("Id: " + id);
		System.out.println("Password: " + password);
		
		int type;
		boolean ok=loginbean.getSuccess();           
		if(ok==true&&id==(loginbean.getId()))
		{
			backNews=id+"已经登陆了";
			loginbean.setBackNews(backNews);
			type=loginbean.getType();	
			if(type==0)
			{
				JOptionPane.showMessageDialog(null, "您已经登陆了！", "提示消息", JOptionPane.ERROR_MESSAGE); 
				RequestDispatcher dispatcher=request.getRequestDispatcher("management.jsp");
				dispatcher.forward(request, response);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "您已经登陆了！", "提示消息", JOptionPane.ERROR_MESSAGE); 
				RequestDispatcher dispatcher=request.getRequestDispatcher("personal.jsp");
				dispatcher.forward(request, response);
			}
			
		}
        else
        {
        	System.out.println("Select from database...");
    	    String URL="jdbc:postgresql://localhost:5432/postgres";
			String USER = "postgres";
			String PASSWORD = "WUHAI9822";
		
			boolean boo=(id!=0&&password.length()>0);
			try{
				System.out.println("Connection...... ");
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				System.out.println("Connection: " + con);
				String condition="";
				String stc = handleString(request.getParameter("stc"));
				System.out.println("stc:  " + stc);
				
				if(stc.equals("学生登陆"))
				{
					System.out.println("学生");
					type=1;
					condition="select * from student where id=? and password=?";
				}
				else if(stc.equals("教师登陆"))
				{
					System.out.println("老师");
					type=2;
					condition="select * from teacher where id=? and password=?";
				}
				else if(stc.equals("企业登陆"))
				{
					System.out.println("企业");
					type=3;
					condition="select * from company where id=? and password=?";
				}
				else
				{
					System.out.println("管理员");
					type=0;
					condition="select * from manager where id=? and password=?";
				}
				sql=con.prepareStatement(condition);
				if(boo)
				{
					sql.setInt(1, id);
					sql.setString(2, password);
					rs=sql.executeQuery();
					boolean m=rs.next();
					if(m==true)
					{
						backNews="登陆成功";
						loginbean.setBackNews(backNews);
						loginbean.setSuccess(true);
						loginbean.setId(id);
						loginbean.setType(type);
						if(type==0)
						{
							RequestDispatcher dispatcher=request.getRequestDispatcher("try_review");
							dispatcher.forward(request, response);
						}
						else
						{
							RequestDispatcher dispatcher=request.getRequestDispatcher("personal.jsp");
							dispatcher.forward(request, response);
						}
						
						
					}
					else
					{
						backNews="该用户不存在或者密码错误！";
						loginbean.setBackNews(backNews);
						loginbean.setSuccess(false);
						loginbean.setId(id);
						loginbean.setPassword(password);	
						JOptionPane.showMessageDialog(null, "该用户不存在或者密码错误！", "格式输入错误", JOptionPane.ERROR_MESSAGE); 
						RequestDispatcher dispatcher=request.getRequestDispatcher("login.jsp");	
						dispatcher.forward(request, response);
				
					}
					
				}
				else
				{
					backNews="用户名和密码不能为空!";
					loginbean.setBackNews(backNews);
					loginbean.setSuccess(false);
					System.out.println("Select from .");
					JOptionPane.showMessageDialog(null, "账号和密码不能为空！", "格式输入错误", JOptionPane.ERROR_MESSAGE); 
					RequestDispatcher dispatcher=request.getRequestDispatcher("login.jsp");
					dispatcher.forward(request, response);
		
				}
				con.close();
				
		
            }catch(SQLException exp)
			{
            	backNews=""+exp;
            	loginbean.setBackNews(backNews);
				loginbean.setSuccess(false);
			}
	
        }
		

	}
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		doPost(request,response);
	}
	

}
