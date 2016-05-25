package com.shares.utils;

import java.time.LocalDateTime;
import java.util.Properties;

public class Utils {
	
	public static final String VERSION = "dev-0.2.0";
	
	public static final String appPath = System.getProperty("user.home") + "/ShareS";
	public static final String coreFilename = "/core.dat";
	public static final String usersFilename = "/uc.dat";
	public static final String propsFilename = "/shares.properties";
	
	
	/**
	 * Returns default properties otherwise loaded from a file. 
	 * @return Properties props
	 */
	public static Properties getDefaultProps() {
		
		Properties defaultProps = new Properties();
		
		defaultProps.setProperty("load.on.init", "true");
		defaultProps.setProperty("load.users", "true");
		
		return defaultProps;
	}
	
	
	/**
	 * Returns a inner format of date and time.
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
