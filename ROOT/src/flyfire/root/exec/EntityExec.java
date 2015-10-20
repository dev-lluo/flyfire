package flyfire.root.exec;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import flyfire.root.context.Exec;
import flyfire.root.context.Store;
import flyfire.root.sql.picture.TblPic;
import flyfire.root.sql.resolver.EntityResolver;
import flyfire.root.sql.resolver.MySqlResolver;
import flyfire.root.sql.resolver.SQLResolver.Result;
import flyfire.root.store.EntityStore;
import flyfire.root.util.JsonC;

@Exec(url="entity.mgr")
public class EntityExec {
	@Exec(url="tables")
	public void tableArr(Store store){
		store.append(tables);
		store.responseJson();
	}
	
	@Exec(url="table.details")
	public void tableDetails(EntityStore store){
		store.append(EntityResolver.resolve(store.getClzz()));
		store.responseJson();
	}
	
	@Exec(url="table.create")
	public void tableCreate(EntityStore store){
		Result result = MySqlResolver.$.forCreate(store.getClzz());
		store.append(result.getSql());
		store.responseText();
	}
	
	private final String tables;
	public EntityExec(){
		Map<String,String> map = new HashMap<String,String>();
		Set<Entry<Class<?>, TblPic>> set = EntityResolver.getStore().entrySet();
		for(Iterator<Entry<Class<?>, TblPic>> i = set.iterator();i.hasNext();){
			Entry<Class<?>, TblPic> entry = i.next();
			map.put(entry.getValue().getName(),entry.getKey().getName());
		}
		tables = JsonC.convert(map);
	}
}
