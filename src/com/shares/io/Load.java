package com.shares.io;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.shares.utils.Utils;
import com.shares.io.DataContainer;


public class Load {
	
	
	private static Logger logger = LogManager.getLogger("Load");
	
	
	public static DataContainer load(String path) {
		 try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(path + Utils.dataFilename))) {
           DataContainer dc = (DataContainer)in.readObject();
           in.close();
           
           if(logger.isInfoEnabled())
           	logger.info("Data file loaded.");
           
           return dc;
       } catch(Exception e) {
       	System.err.println("Loading Failed");
       	e.printStackTrace();
       	
       	if(logger.isErrorEnabled())
       		logger.error("Loading failed.", e);
       	
       	return null;
       }
	}
}
