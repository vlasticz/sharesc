package com.shares.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.shares.base.Core;
import com.shares.utils.Utils;

public class Save {
			
	
	public static void saveCore(Core core, String path) {
		
		if(core != null) {
			try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path + Utils.coreFilename))) {
				
	            out.writeObject(core);
	            out.flush();
	            out.close();
	            System.out.println("Core saved (" + path + ")");	            
	        } catch(IOException e) {
	        	e.printStackTrace();	            
	        }
		} else System.err.println("NPE: Core is null !");
	}
}
