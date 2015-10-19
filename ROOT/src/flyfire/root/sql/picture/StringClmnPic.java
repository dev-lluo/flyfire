package flyfire.root.sql.picture;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class StringClmnPic extends ClmnPic<String,Integer> {

	private String format;

	public final String getFormat() {
		return format;
	}

	public StringClmnPic(String name, Field jPic, boolean isId, boolean isMd, Integer minVal, Integer maxVal,
			String defVal, String format,boolean isNl,boolean isUn,Method getter,Method setter) {
		super(name, jPic, isId, isMd, minVal, maxVal, defVal,isNl,isUn,getter,setter);
		this.format = format;
	}


}
