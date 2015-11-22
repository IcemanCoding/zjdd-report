package com.zjdd.report.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

	public static String addDay( String dateStr, int periodDay )
			throws ParseException {

		Date date = new SimpleDateFormat( "yyyyMMdd" ).parse( dateStr );
		Calendar calendar = new GregorianCalendar();
		calendar.setTime( date );
		calendar.add( calendar.DATE, 1 );
		Date dateTemp = calendar.getTime();
		String resDate = new SimpleDateFormat( "yyyyMMdd" ).format( dateTemp );

		return resDate;

	}

	public static String getSysDate( String pattern ) {

		Date curDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat( pattern );

		return sdf.format( curDate );

	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween( Date smdate, Date bdate )
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		smdate = sdf.parse( sdf.format( smdate ) );
		bdate = sdf.parse( sdf.format( bdate ) );
		Calendar cal = Calendar.getInstance();
		cal.setTime( smdate );
		long time1 = cal.getTimeInMillis();
		cal.setTime( bdate );
		long time2 = cal.getTimeInMillis();
		long between_days = ( time2 - time1 ) / ( 1000 * 3600 * 24 );

		return Integer.parseInt( String.valueOf( between_days ) );
	}

	/**
	 * 字符串的日期格式的计算
	 */
	public static int daysBetween( String smdate, String bdate ) {

		long between_days = 0L;

		try {
			SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMdd" );
			Calendar cal = Calendar.getInstance();
			cal.setTime( sdf.parse( smdate ) );
			long time1 = cal.getTimeInMillis();
			cal.setTime( sdf.parse( bdate ) );
			long time2 = cal.getTimeInMillis();
			between_days = ( time2 - time1 ) / ( 1000 * 3600 * 24 );
		} catch ( ParseException e ) {
			e.printStackTrace();
		}

		return Integer.parseInt( String.valueOf( between_days ) );

	}

	public static void main( String[] args ) {

		String date = "20150826";
		String date2 = "20151122";
		Integer s = 0;
		s = daysBetween( date, date2 );
		System.out.println( s );

	}

}
