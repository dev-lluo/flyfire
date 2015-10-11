package flyfire.root.istener;


import org.apache.commons.fileupload.ProgressListener;

public class UploadProgressListener implements ProgressListener {

	public UploadProgressListener() {
		super();
		// TODO Auto-generated constructor stub
		System.out.println("Progress Listened!");
	}

	@Override
	public void update(long pBytesRead, long pContentLength, int pItems) {
		// TODO Auto-generated method stub

		System.out.println("现在已经处理了" + pBytesRead
				+ "数据，总数据量" + pContentLength
				+ "正在处理" + pItems);

	}

}

