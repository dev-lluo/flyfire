package top.flyfire.sql.api;

public interface IdCreate {
	String create();
	String create(String prefix);
	String create(String prefix,int length);
}
