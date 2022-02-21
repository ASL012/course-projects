package am.MyBean;

public class Login{
	int id;
	int type;//0为管理员，1为学生，2为老师，3为企业
	String password="";
	String backNews="";
	boolean success=false; 
	public void setId(int num){
		id=num;
	}
	public int getId(){
		return id;
	}
	public void setType(int num){
		type=num;
	}
	public int getType(){
		return type;
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
