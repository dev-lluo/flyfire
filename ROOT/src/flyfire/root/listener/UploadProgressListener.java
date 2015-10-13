package flyfire.root.listener;


import org.apache.commons.fileupload.ProgressListener;

import flyfire.root.context.FlyFire;

public class UploadProgressListener implements ProgressListener {
	
	private final String  progressId ;

	public UploadProgressListener(String id) {
		super();
		// TODO Auto-generated constructor stub
		progressId = id;
	}

	@Override
	public void update(long pBytesRead, long pContentLength, int pItems) {
		// TODO Auto-generated method stub
		FlyFire.$.setUploadProgress(progressId, pBytesRead, pContentLength, pItems);
		System.out.println("["+progressId +"]现在已经处理了" + pBytesRead
				+ "数据，总数据量" + pContentLength
				+ "正在处理" + pItems);

	}

}

