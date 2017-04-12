package servlet;

public class LoginBean {
	private String name,pass;
	
	public void setName(String n){
		this.name = n;
	}
	
	public String getName(){
		return name;
	}
	
	public void setPass(String p){
		this.pass = p;
	}
	
	public String getPass(){
		return pass;
	}
	
	public boolean validate(){
		if(pass.equals("truc")){
			return true;
		}
		else{
			return false;
		}
	}
	
}
