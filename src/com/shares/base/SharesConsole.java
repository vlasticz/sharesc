package com.shares.base;

import com.shares.base.Core;
import com.shares.base.Family;
import com.shares.base.Perk;
import com.shares.io.Load;
import com.shares.io.Save;
import com.shares.security.Security;
import com.shares.security.User;
import com.shares.security.UsersContainer;
import com.shares.utils.Utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SharesConsole {
	private Scanner sc = new Scanner(System.in);
	private static final String FAIL = "Unknown Command (commands are case sensitive)";
	private static Security secure = new Security();
	private Core core = new Core();
	private String username;
	private static Properties props = new Properties();
	private static final Logger logger = LogManager.getLogger("shares");


	/*
	 * ENTRY POINT ... main()
	 */
	public static void main(String[] args) {
		
		
		// Load properties from a file
		InputStream in = null;
		
		try {			
			in = new FileInputStream(Utils.appPath + "/shares.properties");
			props.load(in);
			
		} catch(FileNotFoundException fe) {			
			props = Utils.getDefaultProps();
			
		} catch(IOException e) {			
			e.printStackTrace();
		}
		
		
		// Console begins
		cout(" ***-_ SHARES " + Utils.VERSION + " _-***");
		File s = new File(Utils.appPath + Utils.usersFilename);
		
		if (s.exists() && Boolean.valueOf(props.getProperty("load.users"))) {
			ArrayList<User> users = Load.loadUsers(Utils.appPath).getUsers();			
			secure.setUsers(users);
			
		} else {
			cout("No users were found. Default one was registered.");
		}
		

		User user = secure.login();
		
		if (user != null) {
			cout("Login successful!\n");
			logger.info("User successfully logged");
			(new SharesConsole()).console(user);
		} else {
			cout("Login failed!");
		}
	}
	/*
	 *
	 */


	private void console(User user) {

		Boolean quit = false;
		Boolean cancel = false;
		
		this.username = user.getName();
		if (Boolean.valueOf(props.getProperty("load.on.init"))) {											// The very first value from properties file	
			
			this.core = Load.loadCore(Utils.appPath);
		}

		do {
			coutf(String.format("> SHARES %s@%s: ", Utils.VERSION, this.username));

			// Initiate the comms[], input from console, separate each word by the space and put them into comms[].
			String[] comms = this.sc.nextLine().split(" ");

			// Quitting sequence.
			if(comms[0].equals("quit") || comms[0].equals("exit") || comms[0].equals("sd"))
				break;
			else if (comms[0].equals("cancel")) {
				cancel = true;
				break;
			}

        	// Switch tree for processing the commands.        	
        	switch(comms[0]) {
        	
        		/***********************************/										// just optical separation :)
        		case "new":
        			if(comms.length > 1) {
	        			switch(comms[1]) {
	       
	        				/***********************************/
	        				case "family":
	        					
	        					if(comms.length > 2) {
		        					core.addFamily(comms[2]);
	        					}
	        					
	        					else
	        						cout("Specify a valid family name.");
	        					
	        					break;
	        					
	        				/***********************************/
	        				case "perk":
	        					
	        					if(comms.length > 3) {
	        						if(core.getFamily(comms[3]) != null) {	        							
	        							core.getFamily(comms[3]).addPerk(comms[2]);
	        							
	        						} else
	        							cout(String.format("Family %s does not exist", comms[3]));
	        					} else
	        						cout("Specify a valid family name.");
	        					
	        					break;
	        					
	        				/***********************************/
	        				case "user":
	        					
	        					if(comms.length > 3) {	        						
	        						secure.addUser(comms[2], comms[3]);
									cout("User added.");
	        					} else {
	        						cout("Specify the user and the password.");
	        					}
	        					
	        					break;
	        					
	        				/***********************************/
	        				default:
	        					failReaction();
	        					break;
	        			}
	        			break;
        			} else 
        				cout("Specify a valid item to create.");
        			
        			break;
        		
        		/***********************************/
        		case "remove":
        			if(comms.length > 1) {
        				switch(comms[1]) {
        				
        					/***********************************/
        					case "family":
        						if(comms.length > 2) {
        							core.removeFamily(comms[2]);       							
        						}
        						
        						else
        							failReaction();
        						
        						break;
        						
        					/***********************************/
        					case "perk":
        						if(comms.length > 3) {
        							if(core.getFamily(comms[3]) != null) {
        							
        								core.getFamily(comms[3]).removePerk(comms[2]);
        								
        							} else
        								failReaction(String.format("Family %s does not exist", comms[3]));
        						} else
        							failReaction("Specify a valid family name.");
        						
        						break;
        						
        					/***********************************/
        					case "user":
        						if(comms.length > 2) {	        						
	        						secure.remUser(comms[2]);
									cout("User removed.");
	        					} else {
	        						cout("Specify the user.");
	        					}
	        					
	        					break;
        						
        					/***********************************/
        					default:
        						failReaction();
        						
        						break;
        				}
        			}
        			
        			else
        				failReaction();
        			
        			break;
        			
        		/***********************************/
        		case "add":																// working name !!! --should be changed
					if(comms.length > 2) {
						
						for(Family family : core.getFamilies()) {							
							if(comms[1].equals(family.getName())) {
								
								if(family.getPerk(comms[2]) == null) {
									failReaction("Perk \"" + comms[2] + "\" not found in family " + comms[1]);
								}else
									cout(family.getPerk(comms[2]).addValue());
							}
						}
        			}
        			break;
	        			
        		/***********************************/										// just optical separation :)        			
        		case "show":        			
        			for(Family family : core.getFamilies()) {
        				System.out.printf("\nFamily: %s\n", family.getName());
        				for(Perk perk : family.getPerks()) {
        					cout(" Perk: " + perk.getCaption() + ": " + perk.getValue());
        				}
        			}
        			break;
        			
        		/***********************************/
        		case "save":
        			saveAll();
        			break;
        			
        		/***********************************/
        		case "load":
        			this.core = Load.loadCore(Utils.appPath);
        			break;
        			
        		/***********************************/
        		/*case "help":
        			
        			System.out.println("This is ")
        			
        			break;*/
        		/***********************************/
        		default:
        			failReaction();
        			break;
        	}
        	        	        	
        	
        	
        } while(!quit);
        
        if(!cancel) {
        	saveAll();
        }
        
        System.out.println("Quitting ...");
        
        sc.close();		
	}
	
	
	private void saveAll() {
		Save.saveUsers(new UsersContainer(secure.getUsers()), Utils.appPath);
    	Save.saveCore(this.core, Utils.appPath);
    	Save.saveProperties(props, Utils.appPath);
	}
	
	
	private void failReaction() {
		
		System.out.println(FAIL);
	}
	
	
	private void failReaction(String msg) {
		
		System.out.println(msg);
	}
	
	
	private static void cout(String msg) {
		
		System.out.println(msg);
	}
	
	
	private static void coutf(String msg) {
		
		System.out.printf(msg);
	}
}