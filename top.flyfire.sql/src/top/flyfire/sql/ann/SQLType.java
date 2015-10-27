package top.flyfire.sql.ann;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public enum SQLType {
	MYSQL_SMALLINT(Integer.class,"SMALLINT"),
	MYSQL_INTEGER(Long.class,"INTEGER"),
	MYSQL_BIGINT(BigInteger.class,"BIGINT"),
	MYSQL_FLOAT(Float.class,"FLOAT"),
	MYSQL_DOUBLE(Double.class,"DOUBLE"),
	MYSQL_DECIMAL(BigDecimal.class,"DECIMAL"),
	
	MYSQL_VARCHAR(String.class,"VARCHAR"),
	MYSQL_CHAR(String.class,"CHAR"),
	
	MYSQL_BOOLEAN(Boolean.class,"BOOLEAN"),
	
	MYSQL_TIMESTAMP(Timestamp.class,"TIMESTAMP"),
	MYSQL_DATETIME(Timestamp.class,"DATETIME"),
	MYSQL_TIME(Time.class,"TIME"),
	MYSQL_DATE(Date.class,"DATE"),
	MYSQL_YEAR(Date.class,"YEAR")
	
	;private SQLType(Class<?> type,String sqlType){
		this.setType(type);
		this.setSqlType(sqlType);
	}
	public Class<?> getType() {
		return type;
	}
	public void setType(Class<?> type) {
		this.type = type;
	}
	public String getSqlType() {
		return sqlType;
	}
	public void setSqlType(String sqlType) {
		this.sqlType = sqlType;
	}
	private Class<?> type;
	private String sqlType;
}
