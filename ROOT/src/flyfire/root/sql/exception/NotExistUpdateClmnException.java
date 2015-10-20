package flyfire.root.sql.exception;

@SuppressWarnings("serial")
public class NotExistUpdateClmnException extends RuntimeException {
	private final static String default_msg = "[%s]不存在更新列";
	
	public NotExistUpdateClmnException(@SuppressWarnings("rawtypes") final Class clzz){
		super(String.format(default_msg, clzz.getName()));
	}
}
