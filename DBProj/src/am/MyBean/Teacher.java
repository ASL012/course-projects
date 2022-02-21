package am.MyBean;

public class Teacher {
	int id;
	String password="";
	String name="";
	String gender="";
	int age;
	String institute="";
	String tel="";
	String title="";
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
	public void setName(String user)
	{
		name=user;
	}
	public String getName()
	{
		return name;
	}
	public void setGender(String sex)
	{
		gender=sex;
	}
	public String getGender()
	{
		return gender;
	}
	public void setAge(int old)
	{
		age=old;
	}
	public int getAge()
	{
		return age;
	}
	public void setInstitute(String academy)
	{
		institute=academy;
	}
	public String getInstitute()
	{
		return institute;
	}
	public void setTel(String phone)
	{
		tel=phone;
	}
	public String getTel()
	{
		return tel;
	}
	public void setTitle(String head)
	{
		title=head;
	}
	public String getTitle()
	{
		return title;
	}
	public void setBackNews(String s){
		backNews=s; 
	}
	public String getBackNews(){
		return backNews; 
	}


}
