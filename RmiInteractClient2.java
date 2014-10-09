/*RmiInteractClient2.java
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
import java.util.*;

/**
 * this class provide methods for interaction between model
 * view and controller
 */

public class RmiInteractClient2 {
	int clientmove;
	int servermessage;
	String sendrandomnum;
	RmiController controller2;
	RmiView view;
	String hostName;
	int portNumber;
	int counter=0;

	/**
	 * this is contructor 
	 * @param  controller object of Rmicontroller
	 * @param  view       object of Rmiview
	 */

	RmiInteractClient2(RmiController controller,RmiView view
			, String args[]){
		controller2=controller;
		this.view=view;
		sendrandomnum=view.getRandomNum();
	}

	/**
	 * this method begins to interact with the MVC.
	 * @param  args   commandline argument
	 */

	public void begin(String args[]) throws IOException {
           try{
        	String s;
   			if(args.length==0)
   				s="";
   			else
   				s="//localhost:"+args[0]+"/";
		// Rmi registry
		RmiInterface obj2_ran=(RmiInterface)Naming.lookup(s+"object2");
		obj2_ran.emulate(Integer.parseInt(sendrandomnum));

		while (true) {
			int check=100;
			while(check>10){  //waiting for other players move
				Thread.sleep(100);
				check=controller2.check;
				clientmove=check;
			}
			controller2.check=100;
			// rmi registry
			RmiInterface obj2=
				(RmiInterface)Naming.lookup(s+"object2");
			RmiInterface obj1=
				(RmiInterface)Naming.lookup(s+"object1");
			obj2.emulate(clientmove);
			// sends the action to other client on invocation
			servermessage=obj1.give();
			controller2.emulateButtonListener(servermessage);
			counter++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
