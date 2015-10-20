package flyfire.root.sql.resolver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import flyfire.root.sql.picture.ClmnPic;
import flyfire.root.sql.picture.TblPic;

public class MySqlResolver extends SQLResolver {
	
	public static final SQLResolver $ = new MySqlResolver();

	@SuppressWarnings("rawtypes")
	@Override
	public Result forCreate(Class<?> clzz) {
		// TODO Auto-generated method stub
		TblPic tblPic = EntityResolver.resolve(clzz);
		StringBuffer buffer = new StringBuffer("create table "+tblPic.getName() +"(\r\n");
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

}
