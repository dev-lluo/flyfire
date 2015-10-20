package flyfire.root.context;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import flyfire.root.util.JsonC;

public class Store {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private PrintWriter out;
	public Store(HttpServletRequest request,HttpServletResponse response){
		this.request = request;
		this.response = response;
		this.response.setCharacterEncoding("UTF-8");
		try {
			this.out = this.response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public HttpServletRequest request(){
		return this.request;
	}
	public HttpServletResponse response(){
		return this.response;
	}
	
	public void append(Object obj){
		this.out.append(JsonC.convert(obj));
	}
	
	public void append(String str){
		this.out.append(str);
	}
	
	public void responseJson(){
		this.response.setContentType("application/json");
		this.out.close();
	}
	
	public void responseText(){
		this.response.setContentType("text/plain");
		this.out.close();
	}
}
