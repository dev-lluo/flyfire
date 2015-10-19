package flyfire.root.sql.exception;

@SuppressWarnings("serial")
public class NotFoundEntityIdException extends RuntimeException {
	private final static String default_msg = "[%s]û�з���Id";
	public NotFoundEntityIdException(@SuppressWarnings("rawtypes") final Class clzz){
		super(String.format(default_msg, clzz.getName()));
	}
}
