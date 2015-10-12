package flyfire.root.context;

import java.util.HashMap;
import java.util.Map;

public class FlyFire {
	public static final FlyFire $ = new FlyFire();
	private FlyFire(){}
	private Map<String,Progress> uploadProgress = new HashMap<String,Progress>();
	public class Progress {
		private long pBytesRead;
		private long pContentLength;
		private int pItems;
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
		
	}
	public Progress getUploadProgress(String id) {
		if(FlyFire.$.uploadProgress.containsKey(id)){
			return FlyFire.$.uploadProgress.get(id);
		}else{
			return new Progress();
		}
	}
	public void setUploadProgress(String id,long pBytesRead, long pContentLength, int pItems) {
		Progress temp = null;
		if(FlyFire.$.uploadProgress.containsKey(id)){
			temp = FlyFire.$.uploadProgress.get(id);
		}else{
			temp = new Progress();
			FlyFire.$.uploadProgress.put(id, temp);
		}
		temp.setpBytesRead(pBytesRead);
		temp.setpContentLength(pContentLength);
		temp.setpItems(pItems);
	}
}
