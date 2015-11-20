package com.zjdd.report.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

	public static String addDay( String dateStr, int periodDay ) throws ParseException {

		Date date = new SimpleDateFormat( "yyyyMMdd" ).parse( dateStr );
		Calendar calendar = new GregorianCalendar();
		calendar.setTime( date );
		calendar.add( calendar.DATE, 1 );
		Date dateTemp = calendar.getTime();
		String resDate = new SimpleDateFormat( "yyyyMMdd" ).format( dateTemp );

		return resDate;

	}

}
