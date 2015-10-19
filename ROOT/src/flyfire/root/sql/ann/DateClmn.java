package flyfire.root.sql.ann;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface DateClmn {
	String name();
	boolean isId() default false;
	boolean isMd() default false;
	boolean isNl() default true;
	boolean isUn() default false;
	String minVal() default "1900-01-01";
	String maxVal() default "2900-01-01";
	String defVal() default "NOW";
}
