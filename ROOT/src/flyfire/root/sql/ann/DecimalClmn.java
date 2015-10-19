package flyfire.root.sql.ann;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface DecimalClmn {
	String name();
	boolean isId() default false;
	boolean isMd() default false;
	boolean isNl() default true;
	boolean isUn() default false;
	long minVal() default -999999999999999999l;
	long maxVal() default 999999999999999999l;
	long defVal() default 0;
	int scale() default 8;
	int precision() default 2;
}
