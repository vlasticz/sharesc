package com.shares.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.shares.base.Core;
import com.shares.security.UsersContainer;
import com.shares.utils.Utils;


public class Load {
	
	
	private static Logger logger = LogManager.getLogger("Load");

	
	public static Core loadCore(String path) {
		
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path + Utils.coreFilename))) {        	
            Core core = (Core)in.readObject();            
            in.close();     
            
            if(logger.isInfoEnabled())
            	logger.info("Core file loaded.");
            
            return core;
            
        } catch(FileNotFoundException fe) {
        	if(logger.isErrorEnabled())
        		logger.error("Core file not found. New core will be created.", fe);
        	
        	return new Core();
        	
        } catch(Exception e) {        	
        	System.err.println("Loading Failed.");
        	e.printStackTrace();
        	
        	if(logger.isErrorEnabled())
        		logger.error("Loading failed.", e);
        	
        	return null;
        }
	}
	
	
	public static UsersContainer loadUsers(String path) {
		 try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(path + Utils.usersFilename))) {
            UsersContainer uc = (UsersContainer)in.readObject();
            in.close();
            
            if(logger.isInfoEnabled())
            	logger.info("Users file loaded.");
            
            return uc;
        } catch(Exception e) {
        	System.err.println("Loading Failed");
        	e.printStackTrace();
        	
        	if(logger.isErrorEnabled())
        		logger.error("Loading failed.", e);
        	
        	return null;
        }
	}
}
