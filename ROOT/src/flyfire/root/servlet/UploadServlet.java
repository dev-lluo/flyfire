package flyfire.root.servlet;


import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import flyfire.root.listener.UploadProgressListener;
import flyfire.root.util.UUID;


@SuppressWarnings("all")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    private static String tempDir = "";
    
    private static String storeDir = "";
    
    
    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	super.init();
    	UploadServlet.storeDir = this.getServletContext().getRealPath("/upload/store/");
    	UploadServlet.tempDir = this.getServletContext().getRealPath("/upload/temp/");
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// Check that we have a file upload requestss
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			return;
		}
		
		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		// Set factory constraintsx
		factory.setSizeThreshold(1024);	//yourMaxMemorySize
		factory.setRepository(new File(UploadServlet.tempDir));	//yourTempDirectory
			
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		//setProgressListener
		upload.setProgressListener(new UploadProgressListener(UUID.$.create()));
		
		// Parse the request
		List items = null;
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// Process the uploaded items
		Iterator iter = items.iterator();
		while (iter.hasNext()) {
			FileItem item = (FileItem)iter.next();
			
			//整个表单的所有域都会被解析，要先判断一下是普通表单域还是文件上传域
			if (item.isFormField()) {
				String name = item.getFieldName();
				String value = item.getString();
				System.out.println(name + ":" + value);
			} else {
				String fieldName = item.getFieldName();
				String fileName = item.getName();
				String contentType = item.getContentType();
				boolean isInMem = item.isInMemory();
				long sizeInBytes = item.getSize();
				System.out.println(fieldName + ":" + fileName);
				System.out.println("类型：" + contentType);
				System.out.println("是否在内在：" + isInMem);
				System.out.println("文件大小:" + sizeInBytes);
				
				String sPName =fileName.substring(fileName.lastIndexOf('.'));
				
				File uploadedFile = new File( UploadServlet.storeDir+ UUID.$.create()+sPName);
				try {
					item.write(uploadedFile);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					throw new RuntimeException(e);
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}

