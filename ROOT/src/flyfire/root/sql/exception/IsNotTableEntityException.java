package flyfire.root.sql.exception;

@SuppressWarnings("serial")
public class IsNotTableEntityException extends RuntimeException {
	
	private final static String default_msg = "[%s]不是一个表实体";
	
	public IsNotTableEntityException(@SuppressWarnings("rawtypes") final Class clzz){
		super(String.format(default_msg, clzz.getName()));
	}

}
