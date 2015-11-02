package top.flyfire.base.kval;

public class StringKVal implements KVal<String, Object> {

	private String key;
	
	private Object val;
	
	public StringKVal(String key,Object val) {
		// TODO Auto-generated constructor stub
		this.key = key;
		this.val = val;
	}
	
	@Override
	public String key() {
		// TODO Auto-generated method stub
		return this.key;
	}

	@Override
	public Object val() {
		// TODO Auto-generated method stub
		return this.val;
	}

}
