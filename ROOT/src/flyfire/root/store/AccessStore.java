package flyfire.root.store;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import flyfire.root.context.Store;

public class AccessStore extends Store {

	public AccessStore(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		// TODO Auto-generated constructor stub
	}
	
	private String name;
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	private String pwd;
	public void setPwd(String pwd){
		this.pwd = pwd;
	}
	public String getPwd(){
		return this.pwd;
	}

}
