package flyfire.root.sql.picture;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;

public class DecimalClmnPic extends ClmnPic<BigDecimal,BigDecimal> {

	protected int scale;
	protected int precision;


	public final int getScale() {
		return scale;
	}

	public final int getPrecision() {
		return precision;
	}

	public DecimalClmnPic(String name, Field jPic, boolean isId, boolean isMd, Long minVal, Long maxVal,
			Long defVal, int scale, int precision,boolean isNl,boolean isUn,Method getter,Method setter) {
		super(name, jPic, isId, isMd, BigDecimal.valueOf(minVal), BigDecimal.valueOf(maxVal), BigDecimal.valueOf(defVal),isNl,isUn,getter,setter);
		this.scale = scale;
		this.precision = precision;
	}



}
