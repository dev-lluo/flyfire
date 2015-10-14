package flyfire.root.context;

import java.util.HashMap;
import java.util.Map;


public class FlyFire {
	public static final FlyFire $ = new FlyFire();
	private FlyFire(){}
	
	/*上传进度*/
	private Map<String,Progress> uploadProgress = new HashMap<String,Progress>();
	public Progress getUploadProgress(String id) {
		if(FlyFire.$.uploadProgress.containsKey(id)){
			Progress progress = FlyFire.$.uploadProgress.get(id);
			if(progress.illegal()){
				FlyFire.$.print("remove["+id+"]");
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
	
	/*输出*/
	private static Print out = new JdkPrint();
	
	public void setPrint(Print print){
		FlyFire.out = print;
	}
	
	public void print(Object obj){
		FlyFire.out.print(obj);
	}
	
	public void print(Object obj,int level){
		FlyFire.out.print(obj, level);
	}
	
}
