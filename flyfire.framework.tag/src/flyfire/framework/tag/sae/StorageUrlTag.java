package flyfire.framework.tag.sae;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

import flyfire.framework.tag.FOut;

public class StorageUrlTag extends FOut {

	
	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}


	private static final String tempS = "http://";
	
	private static final String tempC0 = "-";
	
	private static final String tempC1 = ".stor.sinaapp.com/";
	
	
	private String identity;
	
	private String storage;
	
	private String url;

	@Override
	protected void out(JspWriter writer) throws IOException {
		// TODO Auto-generated method stub
		writer.write(StorageUrlTag.tempS+this.identity+StorageUrlTag.tempC0+this.storage+StorageUrlTag.tempC1+this.url);
	}
	
	

}
