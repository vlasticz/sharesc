package com.shares.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Properties;

import com.shares.base.Core;
import com.shares.security.UsersContainer;
import com.shares.utils.Utils;

public class Save {
			
	
	public static void saveCore(Core core, String path) {
		
		if(core != null) {
			try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path + Utils.coreFilename))) {				
	            out.writeObject(core);
	            out.flush();
	            out.close();	  
	            
	        } catch(IOException e) {	        	
	        	e.printStackTrace();	        
	        	
	        }			
		} else System.err.println("NPE: Core is null !");
		
	}
	
	
	public static void saveUsers(UsersContainer uc, String path) {
		
		if(uc != null) {
			try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path + Utils.usersFilename))) {				
	            out.writeObject(uc);
	            out.flush();
	            out.close();	 
	            
	        } catch(IOException e) {	        	
	        	e.printStackTrace();	        	
	        }
		} else System.err.println("NPE: Users are null !");
		
	}
	
	
	public static void saveProperties(Properties properties, String path) {
		
		if(properties != null) {
			try(FileOutputStream out = new FileOutputStream(path + Utils.propsFilename)) {				
				properties.store(out, "SharesC properties");
				
			} catch(IOException e) {				
				e.printStackTrace();				
			}
		}
	}
}
