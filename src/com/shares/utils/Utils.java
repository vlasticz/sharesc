package com.shares.utils;

import java.time.LocalDateTime;

public class Utils {
	
	public static final String VERSION = "0.0.0";
	
	public static final String appPath = System.getProperty("user.home") + "/ShareS";
	public static final String coreFilename = "/core.dat";
	public static final String usersFilename = "/uc.dat";
	
	
	/**
	 * Returns a inner format of date and time
	 * @return String dateTimeStr
	 */
	public static String getTimestamp() {
		
		String dateTimeStr = "";
		char[] dateTimeChar = LocalDateTime.now().toString().toCharArray();
		boolean cutOffFlag = false;
		
		
		for(int i = 0; i < dateTimeChar.length; i++) {			
			
			switch(dateTimeChar[i]) {
				case 'T':					
					dateTimeChar[i] = ' ';
					break;
				case '.':
					cutOffFlag = true;
					break;
				case '-':
					dateTimeChar[i] = '/';
					break;
									
			}
			
			if(cutOffFlag)
				break;
						
			dateTimeStr = dateTimeStr.concat(Character.toString(dateTimeChar[i]));					
		}
		
		return dateTimeStr;
	}

}
