package flyfire.root.context;

import java.util.HashMap;
import java.util.Map;

public class FlyFire {
	public static final FlyFire $ = new FlyFire();
	private FlyFire(){}
	private Map<String,Progress> uploadProgress = new HashMap<String,Progress>();
	public Progress getUploadProgress(String id) {
		if(FlyFire.$.uploadProgress.containsKey(id)){
			Progress progress = FlyFire.$.uploadProgress.get(id);
			if(progress.illegal()){
				System.out.println("remove["+id+"]");
				FlyFire.$.uploadProgress.remove(id);
			}
			return progress;
		}else{
			Progress progress = new Progress();
			progress.setMsg("请稍候。。。");
			FlyFire.$.uploadProgress.put(id, progress);
			return progress;
		}
	}
	public void setUploadProgress(String id,long pBytesRead, long pContentLength, int pItems) {
		Progress temp = null;
		if(FlyFire.$.uploadProgress.containsKey(id)){
			temp = FlyFire.$.uploadProgress.get(id);
			temp.flushFlag();
		}else{
			temp = new Progress();
			temp.setMsg("请稍候。。。");
			FlyFire.$.uploadProgress.put(id, temp);
		}
		temp.setpBytesRead(pBytesRead);
		temp.setpContentLength(pContentLength);
		temp.setpItems(pItems);
		if(pBytesRead==pContentLength){
			temp.setEnd(true);
			temp.setMsg("上传成功！！！");
		}else{
			temp.setMsg("正在上传。。。");
		}
	}
}
