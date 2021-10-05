package springboot.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConvert {
	public final static long SECOND_MILLIS = 1000;
    public final static long MINUTE_MILLIS = SECOND_MILLIS*60;
    public final static long HOUR_MILLIS = MINUTE_MILLIS*60;
    public final static long DAY_MILLIS = HOUR_MILLIS*24;
    public final static long YEAR_MILLIS = DAY_MILLIS*365;
	
	public static String dateToString(Date date, String formatString) {
		DateFormat dateFormat = new SimpleDateFormat(formatString);// "yyyy/MM/dd HH:mm:ss");
		return dateFormat.format(date);
	}

	public static Date stringToDate(String str) throws ParseException {
		System.out.println(str);
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
		Date date = dateFormat.parse(str);
		return date;
	}

	public static Date stringToDate(String str, String format)
			throws ParseException {		
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Date date = dateFormat.parse(str);
		return date;
	}

	public static int daysDiff(Date earlierDate, Date laterDate) {
		if (earlierDate == null || laterDate == null)
			return 0;

		return (int) ((laterDate.getTime() / DAY_MILLIS) - (earlierDate
				.getTime() / DAY_MILLIS));
	}

	public static void main(String[] args) {
		String data = "Hello [1]";
		
		try {
			data.replace("[1]", null);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		String daate = DateConvert.dateToString(new Date(), "dd/MM/yyyy HH:mm:ss");
		String strDate = "01/01/1970";
		Date newDate = new Date();
		Long date = new Long(0);
		try {
			date = Long.parseLong(newDate + "");
		} catch (Exception e) {
			date = Long.parseLong("0");
		}
		System.out.println(date);
		// try {
		// System.out.println(DateConvert.stringToDate(strDate,"dd/mm/yyyy").getTime());
		//
		// } catch (Exception e) {
		// // TODO: handle exception
		// }

	}
	// public static XMLGregorianCalendar getWsDate(
	// Date date) {
	// try {
	// GregorianCalendar gcal = new GregorianCalendar();
	// gcal.setTime(date);
	// XMLGregorianCalendar timestamp =
	// DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
	// return timestamp;
	// }
	// catch (DatatypeConfigurationException ex) {
	// throw ex;
	// }
	// }

}
