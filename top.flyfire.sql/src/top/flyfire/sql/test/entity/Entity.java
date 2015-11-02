package top.flyfire.sql.test.entity;

import top.flyfire.sql.ann.Table;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import top.flyfire.sql.ann.Default;
import top.flyfire.sql.ann.Id;
import top.flyfire.sql.ann.Length;
import top.flyfire.sql.ann.NotNull;
import top.flyfire.sql.ann.SQLType;
import top.flyfire.sql.ann.Type;
import top.flyfire.sql.ann.Unique;

@Retention(RetentionPolicy.RUNTIME)
@Table(name="entity",face="TestEntity")
public @interface Entity {
	@Id
	@Type(SQLType.MYSQL_VARCHAR)
	@Length(32)
	String eId();
	
	@Unique
	@Type(SQLType.MYSQL_VARCHAR)
	@Length(10)
	String eCode();
	
	@Type(SQLType.MYSQL_VARCHAR)
	@NotNull
	String eName();
	
	@Type(SQLType.MYSQL_DATETIME)
	@Default("NOW")
	String eTimestamp();
}
