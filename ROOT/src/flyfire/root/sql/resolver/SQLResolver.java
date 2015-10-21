package flyfire.root.sql.resolver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import flyfire.root.sql.picture.ClmnPic;
import flyfire.root.sql.picture.DateClmnPic;
import flyfire.root.sql.picture.DecimalClmnPic;
import flyfire.root.sql.picture.IntClmnPic;
import flyfire.root.sql.picture.StringClmnPic;
import flyfire.root.sql.picture.TblPic;

public abstract class SQLResolver {
	public class Result{
		private String sql;
		private ArrayList<Object> params;
		
		public String getSql(){
			return this.sql;
		}
		public ArrayList<Object> getParams(){
			return this.params;
		}
		
		public Result(String sql,ArrayList<Object> params){
			this.sql = sql;
			this.params = params;
		}
	}
	
	public class SQLCondition{
		private String fieldName;
		private String operator;
		private Object value;
		public String getFieldName() {
			return fieldName;
		}
		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}
		public String getOperator() {
			return operator;
		}
		public void setOperator(String operator) {
			this.operator = operator;
		}
		public Object getValue() {
			return value;
		}
		public void setValue(Object value) {
			this.value = value;
		}
	}
	
	public abstract Result forCreate(Class<?> clzz);
	
	
	public abstract Result forUpdate(Object obj,SQLCondition...conditions);
	
	public abstract Result forSelect(Class<?> clzz,SQLCondition...conditions);
	
	public abstract Result forDelete(Class<?> clzz,SQLCondition...conditions);
	
	public abstract Result forInsert(Object obj);
	
	
	@SuppressWarnings("rawtypes")
	public StringBuffer primaryKey(TblPic tblPic){
		StringBuffer buffer = new StringBuffer("PRIMARY KEY(");
		List<ClmnPic> ids = tblPic.findIds();
		for(Iterator<ClmnPic> i = ids.iterator();i.hasNext();){
			buffer.append(i.next().getName());
			buffer.append(",");
			i.remove();
		}
		return buffer.replace(buffer.length()-1, buffer.length(), ")\r\n");
	}
	
	@SuppressWarnings("rawtypes")
	public StringBuffer getType(ClmnPic pic){
		StringBuffer buffer = new StringBuffer();
		if(pic instanceof StringClmnPic){
			buffer.append("VARCHAR(");
			buffer.append(pic.getMaxVal());
			buffer.append(")");
			buffer.append(" default '");
			buffer.append(pic.getDefVal());
			buffer.append("'");
		}else if(pic instanceof DecimalClmnPic){
			DecimalClmnPic decimalClmnPic = (DecimalClmnPic) pic;
			buffer.append("DECIMAL(");
			buffer.append(decimalClmnPic.getScale());
			buffer.append(",");
			buffer.append(decimalClmnPic.getPrecision());
			buffer.append(")");
			buffer.append(" default ");
			buffer.append(pic.getDefVal());
		}else if(pic instanceof IntClmnPic){
			buffer.append("BIGINT");
			buffer.append(" default ");
			buffer.append(pic.getDefVal());
		}else if(pic instanceof DateClmnPic){
			buffer.append("DATETIME");
		}else{
			throw new RuntimeException("unknown type...");
		}
		if(!pic.isNl()){
			buffer.append(" NOT NULL");
		}
		if(pic.isUn()){
			buffer.append(" UNIQUE");
		}
		return buffer.append(",\r\n");
	}
}
