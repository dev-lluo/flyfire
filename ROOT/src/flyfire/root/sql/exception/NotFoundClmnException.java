package flyfire.root.sql.exception;

@SuppressWarnings("serial")
public class NotFoundClmnException extends RuntimeException {
	private final static String default_msg = "[%s]没有找到[%s]";
	public NotFoundClmnException(Class<?> clzz,String clmn){
		super(String.format(default_msg, clzz.getName(),clmn));
	}
}
