
/*RmiInteractClient1.java
 * 
 * Version:
 *   $hkak$
 *   
 * Revision:
 *   $log$
 */

/**
 *This program interacts with the controller, model and view
 * @author Harshal Khandare
 * @author Ajinkya Kale
 *
 */

import java.io.*;
import java.net.*;
import java.rmi.Naming;
import java.util.Scanner;

/**
 * this class provides methods for interaction between model
 * view and controller
 */

public class RmiInteractClient1 extends Thread {
	int portNumber;
	int counter=0;
	int servermove;
	int clientmessage,randomnum;
	Scanner sc=new Scanner(System.in);
	RmiController controller;
	RmiView view;

	/**
	 * this is contructor 
	 * @param  controller object of socketcontroller
	 * @param  view       object of socketview
	 */

	public RmiInteractClient1(RmiController controller,RmiView view
			,String args[]){
		this.controller=controller;
		this.view=view;
		view.setDialog("Let player 1 join");
		view.dialogvisible(true);
		controller.allow=false;
	}

	/**
	 * this method begins to interact with the MVC.
	 * @param  args   commandline argument
	 */

	public void begin(String args[]) throws IOException{
           try{
        	String s;
   			if(args.length==0)
   				s="";
   			else
   				s="//localhost:"+args[0]+"/";
	      // checking the registry
              RmiInterface obj2_ran=(RmiInterface)Naming.lookup(s+"object2");
	      clientmessage=obj2_ran.give();
	      controller.setServerRandom(new Integer(clientmessage).toString());

		while (true) {
			int check=100;
			RmiInterface obj2=
				(RmiInterface)Naming.lookup(s+"object2");
			clientmessage=obj2.give();
			controller.emulateButtonListener(clientmessage);

			while(check>10){  //waiting for other players move
				Thread.sleep(100);
				check=controller.check;
				servermove=check;
			}
			controller.check=100;
                        // checking for C1
			RmiInterface obj1=
				(RmiInterface)Naming.lookup(s+"object1");
			obj1.emulate(servermove);
			counter++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
