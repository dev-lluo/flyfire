package flyfire0store.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class FileServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			FileInputStream inputStream = null;
			OutputStream outputStream = null;
			String uri = request.getRequestURI();
			try {
				
				response.setHeader("content-disposition",
						"attachment;filename=" + URLEncoder.encode(uri.substring(uri.lastIndexOf(File.separator)+1), "UTF-8"));
				inputStream = new FileInputStream("saestor://flyfire0store" + uri);
				outputStream = response.getOutputStream();
				int len = 0;
				byte[] buffer = new byte[1024];

				while ((len = inputStream.read(buffer)) > 0) {
					outputStream.write(buffer, 0, len);
				}

			} catch (Exception e) {
				
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
	//saestor://flyfire0store/main_bg.jpg
	//saestor://domain/test.txt

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
