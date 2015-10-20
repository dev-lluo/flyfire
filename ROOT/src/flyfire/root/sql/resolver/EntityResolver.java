package flyfire.root.sql.resolver;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import flyfire.root.sql.ann.DateClmn;
import flyfire.root.sql.ann.DecimalClmn;
import flyfire.root.sql.ann.IntClmn;
import flyfire.root.sql.ann.StringClmn;
import flyfire.root.sql.ann.Table;
import flyfire.root.sql.exception.IsNotTableEntityException;
import flyfire.root.sql.exception.NotFoundEntityIdException;
import flyfire.root.sql.picture.ClmnPic;
import flyfire.root.sql.picture.DateClmnPic;
import flyfire.root.sql.picture.DecimalClmnPic;
import flyfire.root.sql.picture.IntClmnPic;
import flyfire.root.sql.picture.StringClmnPic;
import flyfire.root.sql.picture.TblPic;



public final class EntityResolver {
	protected static Map<Class<?>,TblPic> store = new HashMap<Class<?>,TblPic>();
	public static Map<Class<?>,TblPic> getStore(){
		return EntityResolver.store;
	}
	@SuppressWarnings("rawtypes")
	public static TblPic resolve(Class<?> clzz) {
		if(EntityResolver.store.containsKey(clzz)){
			return EntityResolver.store.get(clzz);
		}
		Table table = clzz.getAnnotation(Table.class);
		if(table!=null){
			Field[] filedArr = clzz.getDeclaredFields();
			Map<String,ClmnPic> clmns = new HashMap<String,ClmnPic>();
			for(int i = 0;i<filedArr.length;i++){
				Field fld = filedArr[i];
				StringClmn stringc = fld.getAnnotation(StringClmn.class);
				DecimalClmn decimalc = fld.getAnnotation(DecimalClmn.class);
				IntClmn intc = fld.getAnnotation(IntClmn.class);
				DateClmn datec = fld.getAnnotation(DateClmn.class);
				if(stringc!=null){
					String name = fld.getName();
					String name_ = name.substring(0, 1).toUpperCase()+name.substring(1);
					try{
						Method getter = clzz.getMethod(String.format("get%s",name_));
						Method setter = clzz.getMethod(String.format("set%s", name_), fld.getType());
						clmns.put(name,new StringClmnPic(stringc.name(), fld, stringc.isId(), stringc.isMd(), stringc.minVal(), stringc.maxVal(), stringc.defVal(), stringc.format(), stringc.isNl(),stringc.isUn(), getter, setter));
					}catch(Exception e){
						throw new RuntimeException(e);
					}
				}else if(intc!=null){
					String name = fld.getName();
					String name_ = name.substring(0, 1).toUpperCase()+name.substring(1);
					try{
						Method getter = clzz.getMethod(String.format("get%s",name_));
						Method setter = clzz.getMethod(String.format("set%s", name_), fld.getType());
						clmns.put(name,new IntClmnPic(intc.name(), fld, intc.isId(), intc.isMd(), intc.minVal(), intc.maxVal(), intc.defVal(), intc.isNl(), intc.isUn(),getter, setter));
					}catch(Exception e){
						throw new RuntimeException(e);
					}
				}else if(decimalc!=null){
					String name = fld.getName();
					String name_ = name.substring(0, 1).toUpperCase()+name.substring(1);
					try{
						Method getter = clzz.getMethod(String.format("get%s",name_));
						Method setter = clzz.getMethod(String.format("set%s", name_), fld.getType());
						clmns.put(name,new DecimalClmnPic(decimalc.name(), fld, decimalc.isId(), decimalc.isMd(), decimalc.minVal(), decimalc.maxVal(), decimalc.defVal(),decimalc.scale(),decimalc.precision(), decimalc.isNl(),decimalc.isUn(), getter, setter));
					}catch(Exception e){
						throw new RuntimeException(e);
					}
				}else if(datec!=null){
					String name = fld.getName();
					String name_ = name.substring(0, 1).toUpperCase()+name.substring(1);
					try{
						Method getter = clzz.getMethod(String.format("get%s",name_));
						Method setter = clzz.getMethod(String.format("set%s", name_), fld.getType());
						clmns.put(name,new DateClmnPic(datec.name(), fld, datec.isId(), datec.isMd(), datec.minVal(), datec.maxVal(),datec.defVal() , datec.isNl(),datec.isUn(), getter, setter));
					}catch(Exception e){
						throw new RuntimeException(e);
					}
				}
			}
			boolean hasId = false;
			for(Iterator<Entry<String, ClmnPic>> i = clmns.entrySet().iterator();i.hasNext();){
				Entry<String, ClmnPic> entry = i.next();
				ClmnPic clmn = entry.getValue();
				if(clmn.isId()){
					hasId = true;
					break;
				}
			}
			if(hasId==false){
				throw new NotFoundEntityIdException(clzz);
			}
			TblPic pic = new TblPic(table.name(), clzz, clmns);
			EntityResolver.store.put(clzz, pic);
			return pic;
		}else{
			throw new IsNotTableEntityException(clzz);
		}
	}
}
