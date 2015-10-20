package flyfire.root.sql.ann;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface StringClmn {
	String name();
	boolean isId() default false;
	boolean isMd() default false;
	boolean isNl() default true;
	boolean isUn() default false;
	int minVal() default 0;
	int maxVal() default 500;
	String defVal() default "";
	String format() default "";
}
