package com.shares.base;

import java.util.Scanner;
import com.shares.io.Save;
import com.shares.io.Load;
import com.shares.utils.Utils;


public class SharesConsole {
	
	private static final String FAIL = "Unknown Command (commands are case sensitive)";
	private Core core = new Core();
	private final Boolean loadOnInit = true;
	private Boolean cancel = false;

	
	/*
	 * ENTRY POINT ... main()
	 */
	public static void main(String[] args) {
		
		new SharesConsole().console();
	}
	/*
	 * 
	 */
	
	
	private void console() {
		
		Boolean quit = false;
		Scanner sc = new Scanner(System.in);
		
		if(loadOnInit) {
			this.core = Load.loadCore(Utils.appPath);
		}
			
        do{
        	coutf(String.format("> SHARES %s : ", com.shares.utils.Utils.VERSION));
        	
        	// Initiate the comms[], input from console, separate each word by the space and put them into comms[].
        	String[] comms = sc.nextLine().split(" ");
        	
        	
        	/*
        	 *  
        	 *  Commands ARE now case SENSITIVE !!!  
        	 *  
        	 */
        	
        	
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
	        						failReaction();
	        					
	        					break;
	        					
	        				/***********************************/
	        				case "perk":
	        					if(comms.length > 3) {
	        						if(core.getFamily(comms[3]) != null) {
	        							
	        							core.getFamily(comms[3]).addPerk(comms[2]);
	        							
	        						} else
	        							failReaction(String.format("Family %s does not exist", comms[3]));
	        					} else
	        						failReaction("Specify a valid family name");
	        					
	        					break;
	        					
	        				/***********************************/
	        				default:
	        					failReaction();
	        					break;
	        			}
	        			break;
        			} else 
        				failReaction();
        			
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
        							failReaction("Specify a valid family name");
        						
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
								System.out.println("family found");
								
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
        			Save.saveCore(this.core, Utils.appPath);
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
        	Save.saveCore(this.core, Utils.appPath);
        }
        
        System.out.println("Quiting ...");
        
        sc.close();		
	}
	
	
	private void failReaction() {
		
		System.out.println(FAIL);
	}
	
	
	private void failReaction(String msg) {
		
		System.out.println(msg);
	}
	
	
	private void cout(String msg) {
		
		System.out.println(msg);
	}
	
	
	private void coutf(String msg) {
		
		System.out.printf(msg);
	}
}