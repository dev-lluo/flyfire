package flyfire.root.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Store {
	private HttpServletRequest request;
	private HttpServletResponse response;
	public Store(HttpServletRequest request,HttpServletResponse response){
		this.request = request;
		this.response = response;
	}
	
	public HttpServletRequest request(){
		return this.request;
	}
	public HttpServletResponse response(){
		return this.response;
	}
}
