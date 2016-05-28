package com.shares.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.shares.base.Core;
import com.shares.security.UsersContainer;
import com.shares.utils.Utils;

public class Save {
	
	
	private static Logger logger = LogManager.getLogger("Save");
			
	
	public static void saveCore(Core core, String path) {
		
		if(core != null) {
			try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path + Utils.coreFilename))) {				
	            out.writeObject(core);
	            out.flush();
	            out.close();
	            
	            if(logger.isInfoEnabled())
	            	logger.info("Core file saved.");
	            
			} catch(FileNotFoundException fe) {							
				if(logger.isErrorEnabled())
					logger.error("Path doesn't exist. Will be created.", fe);
				
				createPath(path);
				saveCore(core, path);
	            
	        } catch(IOException e) {	        	
	        	e.printStackTrace();
	        	
	        	if(logger.isErrorEnabled())
	        		logger.error("Saving failed.", e);	        	
	        }			
		} else {
			System.err.println("Saving failed. Core is null.");
			
			if(logger.isErrorEnabled())
				logger.error("Saving failed. Core is null.");
		}
		
	}
	
	
	public static void saveUsers(UsersContainer uc, String path) {
		
		if(uc != null) {
			try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path + Utils.usersFilename))) {				
	            out.writeObject(uc);
	            out.flush();
	            out.close();
	            
	            if(logger.isInfoEnabled())
	            	logger.info("Users file saved.");
	            
			} catch(FileNotFoundException fe) {				
				if(logger.isErrorEnabled())
					logger.error("Path doesn't exist. Will be created.", fe);
				
				createPath(path);
				saveUsers(uc, path);
	            
	        } catch(IOException e) {	        	
	        	e.printStackTrace();
	        	
	        	if(logger.isErrorEnabled())
	        		logger.error("Saving failed.", e);
	        }
		} else {
			System.err.println("Saving failed. Users are null!");
			
			if(logger.isErrorEnabled())
				logger.error("Saving failed. Users are null.");
		}
		
	}
	
	
	public static void saveProperties(Properties properties, String path) {
		
		if(properties != null) {
			try(FileOutputStream out = new FileOutputStream(path + Utils.propsFilename)) {				
				properties.store(out, "SharesC properties");
				
				if(logger.isInfoEnabled())
					logger.info("Properties file saved.");
				
			} catch(FileNotFoundException fe) {
				if(logger.isErrorEnabled())
					logger.error("Path doesn't exist. Will be created.", fe);
				
				createPath(path);
				saveProperties(properties, path);
				
			} catch(IOException e) {				
				e.printStackTrace();
				
				if(logger.isErrorEnabled())
					logger.error("Saving failed.", e);
				
			}			
		} else {
			System.err.println("Saving failed. Properties are null!");
			
			if(logger.isErrorEnabled())
				logger.error("Saving failed. Properties are null.");
		}
	}
	
	
	private static boolean createPath(String path) {
		
		if(new File(path).mkdirs()) {
			if(logger.isDebugEnabled())
				logger.debug("Path created. " + path);
			
				return true;
				
		} else {
			if(logger.isErrorEnabled())
				logger.error("Failet to create path. " + path);
			return false;
		}
	}
}
