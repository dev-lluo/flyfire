package flyfire.root.api;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class APIClient {
	
	
	class Request{
		private final static String GET = "GET";
		private final static String POST = "POST";
		private URL url;
		private HttpURLConnection conn;
		private InputStream is;
		
		public Request(String url){
			try {
				this.url = new URL(url);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
			}
			
		}
		
		public InputStream get(){
			return this.connect(GET);
		}
		
		public InputStream post(){
			return this.connect(POST);
		}
		
		public InputStream connect(String method){
			return this.$connect(method, true, false, null, null);
		}
		
		private final InputStream $connect(String method,boolean doOutPut,boolean doInPut,Map<String,String> textMap,Map<String,String> fileMap){
			try{
				this.conn = (HttpURLConnection)url.openConnection();
				this.conn.setRequestMethod(method);
				this.conn.setRequestProperty("User-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.97 Safari/537.11");
				if(doInPut){
					this.conn.setDoInput(doInPut);
					this.conn.setRequestProperty("Connection", "Keep-Alive");
					if(textMap!=null){
						
					}
				}
				return null;
			}catch(Exception e){
				throw new RuntimeException(e);
			}
		}
		
	}
	
}
