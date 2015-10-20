package flyfire.root.store;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import flyfire.root.context.Store;

public class EntityStore extends Store {

	public EntityStore(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		// TODO Auto-generated constructor stub
	}
	
	public Class<?> getClzz() {
		try {
			return Class.forName(this.clzz);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public void setClzz(String clzz) {
		this.clzz = clzz;
	}

	private String clzz;

}
