package flyfire.root.context;

public class Progress {
	private long pBytesRead;
	private long pContentLength;
	private int pItems;
	private boolean end;
	private String msg;
	public long getpBytesRead() {
		return pBytesRead;
	}
	public void setpBytesRead(long pBytesRead) {
		this.pBytesRead = pBytesRead;
	}
	public long getpContentLength() {
		return pContentLength;
	}
	public void setpContentLength(long pContentLength) {
		this.pContentLength = pContentLength;
	}
	public int getpItems() {
		return pItems;
	}
	public void setpItems(int pItems) {
		this.pItems = pItems;
	}
	public boolean isEnd() {
		return end;
	}
	public void setEnd(boolean end) {
		this.end = end;
	}
	public String isMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}