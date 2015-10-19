package flyfire.root.sql.picture;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

import flyfire.root.util.DateUtil;


public class DateClmnPic extends ClmnPic<Date,Date> {

	public DateClmnPic(String name, Field jPic, boolean isId, boolean isMd, String minVal, String maxVal, String defVal,boolean isNl,boolean isUn,Method getter,Method setter) {
		super(name, jPic, isId, isMd, DateUtil.$.parse(minVal), DateUtil.$.parse(maxVal), DateUtil.$.parse(defVal),isNl,isUn,getter,setter);
		// TODO Auto-generated constructor stub
	}


}
