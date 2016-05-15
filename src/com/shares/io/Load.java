package com.shares.io;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import com.shares.base.Core;
import com.shares.base.Family;


public class Load {
	
	@Deprecated
	public static Family loadFamily(Family family, String path) {
		
		System.out.println("loading family starts");
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path + family.getFileName()))) {
            family = (Family)in.readObject();            
            in.close();
            System.out.println("loading family done");            
            return family;
        } catch (Exception e) {
        	System.err.printf("Loading Failed\n + %s", e);            
        }
        System.out.println("loading family failed");
        return family;
	}
	
	
	public static Core loadCore(String path) {
		
		System.out.println("core loading starts");
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) {
            Core core = (Core)in.readObject();            
            in.close();
            System.out.println("core loading done");            
            return core;
        } catch (Exception e) {
        	System.err.println("Loading Failed");
        	e.printStackTrace();
        	return null;
        }
	}
	
	
	@Deprecated
	public String loadStringContainer(String path) {
		
		System.out.println("loading console starts");
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path + "console.str"))) {
            StringContainer sc = (StringContainer)in.readObject();            
            in.close();
            System.out.println("loading console done");            
            return sc.getString();
        } catch (Exception e) {
        	System.err.printf("Loading Failed\n + %s", e);
        }
        System.out.println("loading console failed");
        return "";
	}
	
	
	@Deprecated
	public static String loadEncodedString(String path) {
		
		System.out.println("loading console starts");
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path + "console.str"))) {
            String string = (String)in.readObject();            
            in.close();
            System.out.println("loading console done");            
            return Base64Coder.decodeString(string);
        } catch (Exception e) {
        	System.err.printf("Loading Failed\n + %s", e);
        }
        System.out.println("loading console failed");
        return "";
	}
	
	
	public void loadSettings() {
		// TODO loadSettings()	
	}
}
