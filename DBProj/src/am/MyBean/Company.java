package am.MyBean;

public class Company {
	int id;
	String password="";
	String companyname="";
	String tel="";
	String backNews="";
	public void setId(int num)
	{
		id=num;
	}
	public int getId()
	{
		return id;
	}
	public void setPassword(String pw)
	{
		password=pw;
	}
	public String getPassword()
	{
		return password;
	}
	public void setCompanyname(String user)
	{
		companyname=user;
	}
	public String getCompanyname()
	{
		return companyname;
	}
	public void setTel(String phone)
	{
		tel=phone;
	}
	public String getTel()
	{
		return tel;
	}
	public void setBackNews(String s){
		backNews=s; 
	}
	public String getBackNews(){
		return backNews; 
	}


}
