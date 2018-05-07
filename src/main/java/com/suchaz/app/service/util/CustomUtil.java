/**
 * 
 */
package com.suchaz.app.service.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author anandsingh
 *
 */
public final class CustomUtil {
	
	public static Date parseDate(String startDate) {
		DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy");
		try {
			return dateFormatter.parse(startDate);
		} catch (ParseException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public static boolean isNotNull(String input)
	{
		if(input!=null)
		{
			if(input.isEmpty())
			{
				return false;
			}
			else if(input.trim().isEmpty())
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		return false;
		
	}

}
