package flyfire.root.api;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class APIService {

	private static String basePath = "d:/web";

	public void start() throws Exception {
		@SuppressWarnings("resource")
		ServerSocket ser = new ServerSocket(10000);
		while (true) {
			Socket socket = ser.accept();

			new Thread(new Server(socket)).start();
		}
	}

	class Server implements Runnable {

		private Socket socket;

		public Server(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			PrintWriter writer = null;
			BufferedReader reader = null;
			BufferedReader freader = null;
			try {
				InputStream in = socket.getInputStream();
				OutputStream out = socket.getOutputStream();
				writer = new PrintWriter(new OutputStreamWriter(out));

				reader = new BufferedReader(new InputStreamReader(in));

				String firstLine = reader.readLine();
				String url = firstLine.split(" ")[1];
				String path = basePath + url;
				freader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));

				writer.write("HTTP/1.1 200 OK\r\n");
				writer.write("Content-type:text/html;charset=UTF-8\r\n");
				writer.write("\r\n");

				String s = "";
				while ((s = freader.readLine()) != null) {
					writer.print(s);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
			} finally {
				writer.close();
				try {
					reader.close();
					freader.close();
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					throw new RuntimeException(e);
				}
			}

		}

	}
}
