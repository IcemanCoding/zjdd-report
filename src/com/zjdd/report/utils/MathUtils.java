package com.zjdd.report.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MathUtils {

	public static BigDecimal divisionReDecimal( Integer dividend,
			Integer divisor ) {

		DecimalFormat df = new DecimalFormat( "######0.00" );
		double shopRate = dividend / divisor;
		String temp = df.format( shopRate );
		BigDecimal res = new BigDecimal( temp );

		return res;

	}

	public static String getPercent( int x, int total ) {
		
		String result = "";
		double x_double = x * 1.0;
		double tempresult = x_double / total * 100;
		DecimalFormat df1 = new DecimalFormat( "0.00" );
		result = df1.format( tempresult );
		return result;
		
	}

	public static void main( String[] args ) {

		String s = getPercent( 3, 32 );
		System.out.println( s );
		BigDecimal b = new BigDecimal( s );
		System.out.println( b );
		
	}

}
