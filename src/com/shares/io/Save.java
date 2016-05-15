package com.shares.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.shares.base.Core;
import com.shares.base.Family;

public class Save {
			
	
	@Deprecated
	public static void saveFamily(Family family, String path) {
		
		if(family != null) {
			try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path + family.getFileName()))) {
				
	            out.writeObject(family);
	            out.flush();
	            out.close();
	            System.out.println(family.getFileName() + " saved");	            
	        } catch(IOException e) {
	        	e.printStackTrace();	            
	        }
		} else System.err.println("NPE: Family is null !");
	}
	
	
	public static void saveCore(Core core, String path) {
		
		if(core != null) {
			try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))) {
				
	            out.writeObject(core);
	            out.flush();
	            out.close();
	            System.out.println("Core saved (" + path + ")");	            
	        } catch(IOException e) {
	        	e.printStackTrace();	            
	        }
		} else System.err.println("NPE: Core is null !");
	}
	
	
	@Deprecated
	public void saveStringContainer(String string, String path) {
		
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path + "console.str"))) {
						
            out.writeObject(new StringContainer(string));
            out.flush();
            out.close();
            System.out.println("console saved");            
        } catch(IOException e) {
        	e.printStackTrace();            
        }		
	}
	
	
	public static void saveEncodedString(String string, String path) {
		
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path + "console.str"))) {
						
            out.writeObject(Base64Coder.encodeString(string));
            out.flush();
            out.close();
            System.out.println("console saved");            
        } catch(IOException e) {
        	e.printStackTrace();            
        }		
	}

}
