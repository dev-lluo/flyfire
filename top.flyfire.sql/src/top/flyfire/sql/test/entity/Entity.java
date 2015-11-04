package top.flyfire.sql.test.entity;

import top.flyfire.sql.ann.Table;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import top.flyfire.sql.ann.Default;
import top.flyfire.sql.ann.Id;
import top.flyfire.sql.ann.Length;
import top.flyfire.sql.ann.NotNull;
import top.flyfire.sql.ann.Numeric;
import top.flyfire.sql.ann.SQLType;
import top.flyfire.sql.ann.Type;
import top.flyfire.sql.ann.Unique;

@Retention(RetentionPolicy.RUNTIME)
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
	@Length(20)
	String eName() default "e_name";
	
	@Type(SQLType.MYSQL_DATETIME)
	@Default("new java.sql.Timestamp(System.currentTimeMillis())")
	String eTimestamp() default "e_timestamp";
	
	@Type(SQLType.MYSQL_DECIMAL)
	@Numeric(precision=5,scale=3)
	String eNum1() default "e_num1";
	
	@Type(SQLType.MYSQL_FLOAT)
	@Numeric(precision=5,scale=3)
	String eNum2() default "e_num2";
	
	@Type(SQLType.MYSQL_INTEGER)
	@Numeric(precision=5)
	String eNum3() default "e_num3";
}
