package top.flyfire.base;

import java.io.IOException;
import java.io.InputStream;

public class StreamUtil {
	
	
	public static String toString(InputStream ins){
		StringBuffer buffer = new StringBuffer();
		byte[] by = new byte[FFContext.BUFFER_SIZE];
		try {
			for(int len = ins.read(by);len>0;len = ins.read(by)){
				buffer.append(new String(by,0,len));
			}
			return buffer.toString();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	
	public static void run(InputStream ins,Task task){
		byte[] by = new byte[FFContext.BUFFER_SIZE];
		try {
			for(int len = ins.read(by);len>0;len = ins.read(by)){
				task.exec(by, len);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	public interface Task{
		void exec(byte[] by ,int len)throws IOException;
	}
}
