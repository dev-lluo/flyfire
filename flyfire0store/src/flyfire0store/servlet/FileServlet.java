package flyfire0store.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class FileServlet extends HttpServlet {

	private static Map<String,String> contentTypeMap = new HashMap<String,String>();
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		InputStream istream = this.getClass().getResourceAsStream("content-type.properties");
		Properties properties = new Properties();
		try {
			properties.load(istream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		Set<Entry<Object, Object>> entrySet = properties.entrySet();
		for(Iterator<Entry<Object, Object>> i = entrySet.iterator();i.hasNext();){
			Entry<Object, Object> entry = i.next();
			contentTypeMap.put(entry.getKey().toString(), entry.getValue().toString());
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			FileInputStream inputStream = null;
			OutputStream outputStream = null;
			String uri = request.getRequestURI();
			try {
				
//				response.setHeader("content-disposition",
//						"attachment;filename=" + URLEncoder.encode(uri.substring(uri.lastIndexOf(File.separator)+1), "UTF-8"));
//				response.setHeader("Accept-Ranges", "bytes");
//				response.setHeader("Cache-Control", "max-age=7200");
//				response.setHeader("Connection","keep-alive");
				String key = ".*";
				if(uri.lastIndexOf(".")>=0){
					key = uri.substring(uri.lastIndexOf("."));
				}
				if(contentTypeMap.containsKey(key)){
					response.setHeader("Content-Type",contentTypeMap.get(key));
				}else{
					throw new RuntimeException(key);
				}

				  
				inputStream = new FileInputStream("saestor://flyfire0store" + uri);
				outputStream = response.getOutputStream();
				int len = 0;
				byte[] buffer = new byte[1024];

				while ((len = inputStream.read(buffer)) > 0) {
					outputStream.write(buffer, 0, len);
				}

			} catch (Exception e) {
				response.getWriter().write("{\"code\":404,\"msg\":\""+uri+" isn't exsit...\",\"e\":\""+e+"\"}");
			} finally {
				if (inputStream != null) {
					try {
						inputStream.close();
					} catch (Exception e) {
						response.getWriter().write("{\"code\":404,\"msg\":\""+uri+" isn't exsit...\"}");
					}

				}
			}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
