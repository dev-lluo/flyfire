package flyfire.root.sql.picture;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class ClmnPic<D,M>  {
	protected final String name;
	protected final Field jPic;
	protected final String jName;
	protected final boolean isId;
	protected final boolean isMd;
	protected final boolean isNl;
	protected final boolean isUn;
	protected final M minVal;
	protected final M maxVal;
	protected final D defVal;
	protected final Method getter;
	protected final Method setter;
	
	public final void set(Object obj,Object val){
		try {
			this.setter.invoke(obj, val);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public final <T> T get(Object obj){
		try {
			return (T) this.getter.invoke(obj);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	public final String getjName() {
		return jName;
	}

	public final String getName() {
		return name;
	}
	public final Field getjPic() {
		return jPic;
	}
	public final boolean isId() {
		return isId;
	}
	public final boolean isMd(){
		return isMd;
	}
	public final M getMinVal() {
		return minVal;
	}

	public final M getMaxVal() {
		return maxVal;
	}

	public final D getDefVal() {
		return defVal;
	}
	
	public final boolean isUn(){
		return isUn;
	}
	protected ClmnPic(String name, Field jPic, boolean isId, boolean isMd, M minVal, M maxVal, D defVal,boolean isNl,boolean isUn,Method getter,Method setter) {
		super();
		this.name = name;
		this.jPic = jPic;
		this.jName = jPic.getName();
		this.isId = isId;
		this.isMd = isMd;
		this.minVal = minVal;
		this.maxVal = maxVal;
		this.defVal = defVal;
		this.isNl = isNl;
		this.isUn = isUn;
		this.getter = getter;
		this.setter = setter;
	}
	public final boolean isNl() {
		return isNl;
	}
	
	
	
	
	
	

		
}
