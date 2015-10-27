package top.flyfire.sql.test.entity;

import top.flyfire.sql.ann.Table;
import top.flyfire.sql.ann.Default;
import top.flyfire.sql.ann.Id;
import top.flyfire.sql.ann.Length;
import top.flyfire.sql.ann.NotNull;
import top.flyfire.sql.ann.SQLType;
import top.flyfire.sql.ann.Type;
import top.flyfire.sql.ann.Unique;

@Table(name="entity",face="TestEntity")
public @interface Entity {
	@Id
	@Type(SQLType.MYSQL_VARCHAR)
	@Length(32)
	String eId() default "e_id";
	
	@Unique
	@Type(SQLType.MYSQL_VARCHAR)
	@Length(10)
	String eCode() default "e_code";
	
	@Type(SQLType.MYSQL_VARCHAR)
	@NotNull
	String eName() default "e_name";
	
	@Type(SQLType.MYSQL_DATETIME)
	@Default("NOW")
	String eTimestamp() default "e_timestamp";
}
