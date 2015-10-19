package flyfire.root.sql.picture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import flyfire.root.sql.exception.NotFoundClmnException;



public class TblPic {
	
	protected String name;
	
	protected Class<?> jPic;
	
	@SuppressWarnings("rawtypes")
	protected Map<String,ClmnPic> clmns = new HashMap<String,ClmnPic>();

	public String getName() {
		return name;
	}

	public Class<?> getjPic() {
		return jPic;
	}

	@SuppressWarnings("rawtypes")
	public Map<String,ClmnPic> getClmns() {
		return clmns;
	}

	@SuppressWarnings("rawtypes")
	public ClmnPic findClmn(String name){
		if(this.clmns.containsKey(name)){
			return this.clmns.get(name);
		}else{
			throw new NotFoundClmnException(this.jPic, name);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public List<ClmnPic> findIds(){
		List<ClmnPic> list = new ArrayList<ClmnPic>();
		for(Iterator<Entry<String,ClmnPic>> iterator = this.clmns.entrySet().iterator();iterator.hasNext();){
			Entry<String,ClmnPic> entry = iterator.next();
			ClmnPic clmn = entry.getValue();
			if(clmn.isId()){
				list.add(clmn);
			}
		}
		return list;
	}
	
	@SuppressWarnings("rawtypes")
	public List<ClmnPic> findClmnIgnoredIds(){
		List<ClmnPic> list = new ArrayList<ClmnPic>();
		for(Iterator<Entry<String,ClmnPic>> iterator = this.clmns.entrySet().iterator();iterator.hasNext();){
			Entry<String,ClmnPic> entry = iterator.next();
			ClmnPic clmn = entry.getValue();
			if(!clmn.isId()){
				list.add(clmn);
			}
		}
		return list;
	}
	
	public TblPic(String name, Class<?> jPic, @SuppressWarnings("rawtypes") Map<String,ClmnPic> clmns) {
		super();
		this.name = name;
		this.jPic = jPic;
		this.clmns = clmns;
	}
	
	

}
