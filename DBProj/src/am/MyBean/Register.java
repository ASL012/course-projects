package am.MyBean;

public class Register{
	int id;
	String name;
	String password="";
	String backNews="";
	boolean success=false; 
	public void setId(int num){
		id=num;
	}
	public int getId(){
		return id;
	}
	public void setName(String user){
		name=user;
	} 
	public String getName() {
		return name; 
	}
	public void setPassword(String pw){
		password=pw;
	} 
	public String getPassword() {
		return password; 
	}
	public void setBackNews(String s){
		backNews=s; 
	}
	public String getBackNews(){
		return backNews; 
	}
	public void setSuccess(boolean b){
		success=b;
	} 
	public boolean getSuccess() {
		return success;
	}
}
