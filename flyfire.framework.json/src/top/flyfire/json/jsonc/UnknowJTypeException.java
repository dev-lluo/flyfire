package top.flyfire.json.jsonc;

@SuppressWarnings("serial")
public class UnknowJTypeException extends RuntimeException {

	public UnknowJTypeException(String json) {
		super("UnknowJTypeException#"+System.currentTimeMillis()+"#"+json);
		// TODO Auto-generated constructor stub
	}
}