package com.shares.utils;

import java.time.LocalDateTime;

public class Utils {
	
	public static final String VERSION = "0.0.0";
	
	public static final String PASS = "masterAdmin17.";
		
	private static final String dataPath = "//Elis-pc/shared/system/Tasker/data/";
	private static final String devDataPath = "C:/Users/eLiS/workspace/java/Tasker/data/";
	
	public static final String devSharesPathAndFilename = "C:/Data/shares_test/core.sav";
	
	
	/**
	 * Set true to get development data path returned.
	 * @param Boolean
	 * @return String path
	 */
	public static String getDevDataPath(Boolean useDev) {
		
		if(useDev) {
			return Utils.devDataPath;
		} else {
			return Utils.dataPath;
		}		
	}
	
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
