package flyfire.root.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtil {
	public final String NOW = "NOW";
	public static final DateUtil $ = new DateUtil();
	private SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	public Date parse(String date){
		if(NOW.equals(date))return new Date();
		try {
			return formater.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return new Date();
		}
	}
}
