package com.shares.base;

import com.shares.base.Core;
import com.shares.base.Family;
import com.shares.base.Perk;
import com.shares.io.DataContainer;
import com.shares.io.IO;
import com.shares.security.Security;
import com.shares.security.User;
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
	
	
	private static SharesConsole shares;
	private Scanner sc = new Scanner(System.in);
	private static final String FAIL = "Unknown Command (commands are case sensitive)";
	private static Security secure = new Security();
	private Core core = new Core();
	private String username;
	private static Properties props = new Properties();
	public static Logger logger;
	private boolean saveProperties = false;


	/*
	 * ENTRY POINT ... main()
	 */
	public static void main(String[] args) {
		
		shares = new SharesConsole();
		shares.init();
	}
	/*
	 *
	 */
	
	
	private void init() {
		
		logger = LogManager.getLogger("Core");
		
		if(logger.isInfoEnabled())
			logger.info("============== ShareS " + Utils.VERSION + " started ==============");
				
		// Load properties from a file
		InputStream in = null;
		
		try {			
			in = new FileInputStream(Utils.appPath + "/shares.properties");
			props.load(in);
			
			if(logger.isInfoEnabled())
				logger.info("Properties file loaded.");
			
		} catch(FileNotFoundException fe) {			
			props = Utils.getDefaultProps();
			saveProperties = true;
			
			if(logger.isWarnEnabled())
				logger.warn("Default properties loaded.");
			
		} catch(IOException e) {
			if(logger.isErrorEnabled())
				logger.error(" ", e);
			
			e.printStackTrace();
		}
		
		
		// Console begins
		cout(" ***-_ SHARES " + Utils.VERSION + " _-***");
		File s = new File(Utils.appPath + Utils.dataFilename);
		
		if (s.exists()) {
			ArrayList<User> users = IO.load(Utils.appPath).getUsers();
			secure.setUsers(users);
			
		} else {
			cout("No data file found. Default user made available.");
			
			if(logger.isWarnEnabled())
				logger.warn("No data file found. Default user made available.");
		}
		

		User user = secure.login();
		
		if (user != null) {
			cout("Login successful!\n");
									
			if(logger.isInfoEnabled())
				logger.info("User successfully logged in. User: " + user.getName() + ".");
			
			shares.console(user);
			
		} else {
			cout("Login failed!");
			
			if(logger.isWarnEnabled())
				logger.warn("User login failed.");
		}
	}


	private void console(User user) {

		Boolean quit = false;
		Boolean cancel = false;
		
		this.username = user.getName();
		if (Boolean.valueOf(props.getProperty("load.on.init"))) {											// The very first value from properties file	
			
			this.core = IO.load(Utils.appPath).getCore();
		}

		do {
			coutf(String.format("> SHARES %s@%s: ", Utils.VERSION, this.username));

			// Initiate the comms[], input from console, separate each word by the space and put them into comms[].
			String[] comms = this.sc.nextLine().split(" ");

			// Quitting sequence.
			if(comms[0].equals("quit") || comms[0].equals("exit") || comms[0].equals("sd"))
				break;
			else if (comms[0].equals("cancel") || comms[0].equals("abort")) {
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
	        					
	        					if(secure.getUser(username).getPermissionValue("new_family")) {
	        					
		        					if(comms.length > 2) {
		        						
			        					if(core.addFamily(comms[2])) {
			        						cout("Family created.");
			        						
			        						if(logger.isTraceEnabled())
			        							logger.trace("Family " + comms[2] + " created.");
			        						
			        						if(secure.getUser(username).addHomeFamily(comms[2])) {
			        							cout("Family added to home families to user " + username);
			        							
			        							if(logger.isTraceEnabled())
			        								logger.trace("Family " + comms[2] + " added to user " + username + " as home family.");
			        						} else {
			        							cout("Failed to add family to home families of user " + username);
			        							
			        							if(logger.isTraceEnabled())
			        								logger.trace("Failed to add family" + comms[2] + " to home families of user " + username);
			        						}
			        						
			        					} else {
			        						cout("Failed to create family. Name is invalid or the family already exists.");
			        						
			        						if(logger.isTraceEnabled())
			        							logger.trace("Failed to create family " + comms[2] + ". Name is invalid or the family already exists.");
			        					}
		        					} else {
		        						cout("Specify valid family name.");
		        					}
	        					} else {
	        						cout("Insufficient permissions.");
	        					}
	        					
	        					break;
	        					
	        				/***********************************/
	        				case "perk":
	        					
	        					if(comms.length > 2) {
	        						
		        					if(comms.length > 3) {
		        						
		        						if(core.getFamily(comms[3]) != null) {	
		        							
		        							if(core.getFamily(comms[3]).addPerk(comms[2])) {
		        								cout("Perk added");
		        								
		        								if(logger.isTraceEnabled())
		        									logger.trace("Perk " + comms[2] + " added to family " + comms[3] + ".");
		        								
		        							} else {
		        								cout("Failed to create perk. Name is invalid or the perk already exists.");
		        								
		        								if(logger.isTraceEnabled())
		        									logger.trace("Failed to create perk " + comms[2] + ". Name is invalid or the perk already exists.");
		        							}
		        							
		        						} else {
		        							cout("Family " + comms[3] + " doesn't exist.");
		        						}		        							
		        					} else {
		        						cout("Specify valid family name.");
		        					}		        						
	        					} else {
	        						cout("Specify valid perk name.");
	        					}
	        					
	        					break;
	        					
	        				/***********************************/
	        				case "user":
	        					
	        					if(secure.getUser(username).getPermissionValue("users_admin")) {
	        					
		        					if(comms.length > 2) {
		        						
			        					if(comms.length > 3) {
			        								        								        						
			        						if(secure.addUser(comms[2], comms[3])) {
			        							cout("User created.");
			        							
			        							if(logger.isTraceEnabled())
			        								logger.trace("User " + comms[2] + " created.");
			        							
			        						} else {
			        							cout("User already exists.");
			        							
			        							if(logger.isTraceEnabled())
			        								logger.trace("User " + comms[2] + " already exists.");
			        						}
			        					} else {
			        						cout("Specify password.");
			        					}
		        					} else {
		        						cout("Specify username.");
		        					}
	        					} else {
	        						cout("Insufficient permissions.");
	        					}
	        					
	        					break;
	        					
	        				/***********************************/
	        				default:
	        					cout(FAIL);
	        					break;
	        			}
        			} else 
        				cout("Specify valid item to create.");
        			
        			break;
        		
        		/***********************************/
        		case "remove":
        			
        			if(comms.length > 1) {
        				switch(comms[1]) {
        				
        					/***********************************/
        					case "family":
        						
        						if(comms.length > 2) {
        							
        							if(core.removeFamily(comms[2])) {
        								cout("Family removed.");
        								
        								if(logger.isTraceEnabled())
        									logger.trace("Family " + comms[2] + " removed.");
        								
        							} else {
        								cout("Family " + comms[2] + " doesn't exist.");
        								
        								if(logger.isTraceEnabled())
        									logger.trace("Family " + comms[2] + " doesn't exist.");
        							}
        						} else {
        							cout("Specify valid family name.");
        						}
        						
        						break;
        						
        					/***********************************/
        					case "perk":
        						
        						if(comms.length > 2) {
        						        						
	        						if(comms.length > 3) {
	        							
	        							if(core.isFamily(comms[3])) { 
	        								
	        								if(core.getFamily(comms[3]).removePerk(comms[2])) {
	        									cout("Perk removed.");
	        									
	        									if(logger.isTraceEnabled())
	        										logger.trace("Perk " + comms[2] + " removed from family " + comms[3] + ".");
	        									
	        								} else {
	        									cout("Perk " + comms[2] + " doesn't exist.");
	        									
	        									if(logger.isTraceEnabled())
	        										logger.trace("Perk " + comms[2] + " doesn't exist.");
	        								}
	        							} else {
	        								cout("Family " + comms[3] + " doesn't exist.");
	        							}
	        						} else {
	        							cout("Specify valid family name.");
	        						}
        						} else {
        							cout("Specify valid perk name.");
        						}
        						
        						break;
        						
        					/***********************************/
        					case "user":
        						
        						if(secure.getUser(username).getPermissionValue("users_admin")) {
        						
	        						if(comms.length > 2) {	
	        							
		        						if(secure.remUser(comms[2])) {
		        							cout("User removed.");
		        							
		        							if(logger.isTraceEnabled())
		        								logger.trace("User " + comms[2] + " removed.");
		        							
		        						} else {
		        							cout("User " + comms[2] + " doesn't exist.");
		        							
		        							if(logger.isTraceEnabled()) {
		        								logger.trace("User " + comms[2] + " doesn't exist.");
		        							}
		        						}
										
		        					} else {
		        						cout("Specify username.");
		        					}
        						} else {
        							cout("Insufficient permissions.");
        							
        							if(logger.isWarnEnabled())
        								logger.warn("Insufficient permissions. User: " + username + ". Action: remove user.");
        						}
	        					
	        					break;
        						
        					/***********************************/
        					default:
        						cout(FAIL);
        						
        						break;
        				}
        			} else
        				cout("Specify valid item to remove.");
        			
        			break;
        			
        		/***********************************/
        		case "add":	
        			
        			if(comms.length > 1) {
        				
						if(comms.length > 2) {
								
							if(core.isFamily(comms[1])) {
								Family family = core.getFamily(comms[1]);
								
								if(family.isPerk(comms[2])) {
									
									if(family.getPerk(comms[2]).addValue()) {
										cout("Value added.");
										
										if(logger.isTraceEnabled())
											logger.trace("Value added to perk " + comms[2] + " of family " + comms[1] + " by " + this.username + ".");
									}
									
								} else {
									cout("Perk " + comms[2] + " not found in family " + comms[1]);
								}								
							} else {
								cout("Family " + comms[1] + " doesn't exist.");
							}
	        			} else {
	        				cout("Specify valid perk name.");
	        			}
        			} else {
        				cout("Specify valid family name.");
        			}
					
        			break;
        			
        		/***********************************/
        		case "sub":
        			
        			if(comms.length > 1) {
        				
	        			if(comms.length > 2) {
	        				
	        				if(core.isFamily(comms[1])) {
	        					Family family = core.getFamily(comms[1]);
        						
        						if(family.isPerk(comms[2])) {	
        							
        							if(family.getPerk(comms[2]).subValue()) {
        								cout("Value substracted.");
        								
        								if(logger.isTraceEnabled())
        									logger.trace("Value substracted from perk " + comms[2] + " of family " + comms[1] + " by " + this.username + ".");
        								
        							} else {
        								cout("Value cannot be lower than zero.");
        								
        								if(logger.isTraceEnabled())
        									logger.trace("Value of perk " + comms[2] + " of family " + comms[1] + " is already zero.");
        							}        							
        							
        						} else {
        							cout("Perk " + comms[2] + " not found in family " + comms[1]);
        						}
        					} else {
        						cout("Family " + comms[1] + " doesn't exist.");
        					}
	        			} else {
	        				cout("Specify valid perk name.");
	        			}
        			} else {
        				cout("Specify valid family name.");
        			}
        			
        			break;
	        			
        		/***********************************/										// just optical separation :)        			
        		case "show":  
        			
        			if(secure.getUser(username).getPermissionValue("families_admin")) {
        				
        				for(Family family : core.getFamilies()) {
            				System.out.printf("\nFamily: %s\n", family.getName());
            				for(Perk perk : family.getPerks()) {
            					cout(" Perk: " + perk.getCaption() + ": " + perk.getValue());
            				}
            			}
        			} else {
        				
	        			for(Family family : core.getFamiliesByName(secure.getUser(username).getHomeFamilies())) {
	        				System.out.printf("\nFamily: %s\n", family.getName());
	        				for(Perk perk : family.getPerks()) {
	        					cout(" Perk: " + perk.getCaption() + ": " + perk.getValue());
	        				}
	        			}
        			}
        			
        			break;
        			
        		/***********************************/
        		case "save":
        			saveAll();
        			break;
        			
        		/***********************************/
        		case "load":
        			this.core = IO.load(Utils.appPath).getCore();
        			break;
        			
        		/***********************************/
        		/*case "help":
        			
        			System.out.println("This is ")
        			
        			break;*/
        		/***********************************/
        		default:
        			cout(FAIL);
        			break;
        	}
        	        	        	
        	
        	
        } while(!quit);
        
        if(!cancel) {
        	saveAll();
        }
                
        sc.close();
        System.out.println("Quitting ...");
        
        if(logger.isTraceEnabled())
        	logger.trace("Exit");
	}
	
	
	private void saveAll() {
		
		IO.save(new DataContainer(secure.getUsers(), this.core), Utils.appPath);
	    	    
	    if(saveProperties) {
	    	IO.saveProperties(props, Utils.appPath);
	    }
	}
	
	
	private static void cout(String msg) {
		
		System.out.println(msg);
	}
	
	
	private static void coutf(String msg) {
		
		System.out.printf(msg);
	}
}