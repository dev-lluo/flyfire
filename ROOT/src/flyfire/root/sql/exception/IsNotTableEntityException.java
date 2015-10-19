package flyfire.root.sql.exception;

@SuppressWarnings("serial")
public class IsNotTableEntityException extends RuntimeException {
	
	private final static String default_msg = "[%s]����һ����";
	
	public IsNotTableEntityException(@SuppressWarnings("rawtypes") final Class clzz){
		super(String.format(default_msg, clzz.getName()));
	}

}
