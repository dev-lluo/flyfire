package flyfire.root.context;

public interface Print {
	
	public final static int PRNT_ERR = -1;
	public final static int PRNT_WAR = 0;
	public final static int PRNT_LOG = 1;
	
	
	void print(Object obj);
	void print(Object obj ,int level);
}
