package com.shares.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;

import com.shares.base.Core;
import com.shares.security.UsersContainer;
import com.shares.utils.Utils;


public class Load {
	
	
	public static Core loadCore(String path) {
		
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path + Utils.coreFilename))) {        	
            Core core = (Core)in.readObject();            
            in.close();        
            return core;
            
        } catch(FileNotFoundException fe) {        	
        	return new Core();
        	
        } catch(Exception e) {
        	
        	System.err.println("Loading Failed");
        	e.printStackTrace();
        	return null;
        }
	}
	
	
	public static UsersContainer loadUsers(String path) {
		 try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(path + Utils.usersFilename))) {
            UsersContainer uc = (UsersContainer)in.readObject();
            in.close();
            return uc;
        } catch(Exception e) {
        	System.err.println("Loading Failed");
        	e.printStackTrace();
        	return null;
        }
	}
}
