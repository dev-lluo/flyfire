package flyfire.root.sql.resolver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import flyfire.root.sql.exception.NotExistUpdateClmnException;
import flyfire.root.sql.picture.ClmnPic;
import flyfire.root.sql.picture.TblPic;

public class MySqlResolver extends SQLResolver {
	
	public static final SQLResolver $ = new MySqlResolver();

	@SuppressWarnings("rawtypes")
	@Override
	public Result forCreate(Class<?> clzz) {
		// TODO Auto-generated method stub
		TblPic tblPic = EntityResolver.resolve(clzz);
		StringBuffer buffer = new StringBuffer("CREATE TABLE "+tblPic.getName() +"(\r\n");
		Set<Entry<String, ClmnPic>> set = tblPic.getClmns().entrySet();
		for(Iterator<Entry<String, ClmnPic>> i =set.iterator();i.hasNext();){
			Entry<String, ClmnPic> entry = i.next();
			buffer.append(entry.getValue().getName());
			buffer.append(" ");
			buffer.append(this.getType(entry.getValue()));
		}
		buffer.append(this.primaryKey(tblPic));
		buffer.append(")");
		
		return new Result(buffer.toString(),new ArrayList<>(0));
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Result forUpdate(Object obj, SQLCondition... conditions) {
		// TODO Auto-generated method stub
		if(obj==null){
			return null;
		}else{
			Class<?> clzz = obj.getClass();
			TblPic tblPic = EntityResolver.resolve(clzz);
			ArrayList<Object> params = new ArrayList<Object>();
			StringBuffer buffer = new StringBuffer("UPDATE ");
			buffer.append(tblPic.getName());
			buffer.append(" SET ");
			List<ClmnPic> clmns = tblPic.findClmnIgnoredIds();
			for(Iterator<ClmnPic> i = clmns.iterator();i.hasNext();){
				ClmnPic clmn = i.next();
				Object val = clmn.get(obj);
				if(val!=null){
					params.add(val);
					buffer.append(clmn.getName());
					buffer.append("=?");
					buffer.append(" ,");
				}
			}
			if(params.isEmpty()){
				throw new NotExistUpdateClmnException(clzz);
			}
			List<ClmnPic> ids = tblPic.findIds();
			if(!ids.isEmpty()||conditions.length>0){
				buffer.replace(buffer.length()-1, buffer.length(), "WHERE ");
			}
			for(Iterator<ClmnPic> i = ids.iterator();i.hasNext();){
				ClmnPic clmn = i.next();
				Object val = clmn.get(obj);
				if(val!=null){
					params.add(val);
					buffer.append(clmn.getName());
					buffer.append("=?");
					buffer.append("AND ");
				}
			}
			for(int i = 0;i<conditions.length;i++){
				if(conditions[i].getValue()!=null){
					params.add(conditions[i].getValue());
					buffer.append(conditions[i].getFieldName());
					buffer.append(conditions[i].getOperator());
					buffer.append("AND ");
				}
			}
			buffer.append("1=1");
			return new Result(buffer.toString(), params);
			
		}
	}

	@Override
	public Result forSelect(Class<?> clzz, SQLCondition... conditions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result forDelete(Class<?> clzz, SQLCondition... conditions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result forInsert(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
