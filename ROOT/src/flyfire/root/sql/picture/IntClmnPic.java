package flyfire.root.sql.picture;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class IntClmnPic extends ClmnPic<Long,Long> {

	protected int len;
	
	public IntClmnPic(String name, Field jPic, boolean isId, boolean isMd, Long minVal, Long maxVal, Long defVal,boolean isNl,boolean isUn,Method getter,Method setter) {
		super(name, jPic, isId, isMd, minVal, maxVal, defVal,isNl,isUn,getter,setter);
		// TODO Auto-generated constructor stub
		len = maxVal.toString().length();
	}

	

}
