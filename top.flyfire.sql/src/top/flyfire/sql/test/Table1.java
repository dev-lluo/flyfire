package top.flyfire.sql.test;

import top.flyfire.sql.ann.Id;
import top.flyfire.sql.ann.Table;
import top.flyfire.sql.ann.Unique;

@Table(name="table_1")
public @interface Table1 {
	@Id
	String tid() default "t_id";
	@Unique
	String tname() default "t_name";
}
