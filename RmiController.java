/*RmiController.java
 * 
 * Version:
 *   $hkak$
 *   
 * Revision:
 *   $log$
 */

/**
 * This program implements the RMI programming concepts 
 * for the game called precision, implemented in previous
 * homework(hw12).
 * 
 * @author Harshal Khandare
 * @author Ajinkya Kale
 *
 */

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is a Rmi controller that
 * contains the actionlistener, which controls
 * the logic and implementation of the program
 * 
 */

public class RmiController {

	private RmiView view;  // class variable  
	private RmiModel model;
	int check=100;  
	boolean allow=true; // boolean variable for freezing buttons

	/**
	 * constructor for RmiController
	 * @param  view     initializing the object
	 * @param  model    initializing the object
	 * 
	 */
	
	RmiController(RmiView view,RmiModel model) throws RemoteException{
		this.view=view;
		this.model=model;
		for ( int index = -5; index <=5; index ++ )
			view.buttonListener(new ButtonListener(),index+5);
	}

	/**
	 * this method listens to event action of other player
	 * @param  no  number that is input from other player
	 * 
	 */
	
	public void emulateButtonListener(int no){
		model.action(no); //perform action on click 
		view.labelSet(model.getlabel());
		view.label_2Set(model.getlabel_2());
		view.statusSet(model.getstatus());
		view.disableButton(no+5);
		view.dialogvisible(false);
		allow=true;
	}

	/**
	 * This class implements ActionListner. it implements the 
	 * mechanisim to percive the input from the player.
	 * 
	 */
	
	class ButtonListener implements ActionListener{
		
		/**
		 * this method is executes when the action is performed
		 * from the user.
		 * @param  e  input as the action performed.
		 */
		
		public void actionPerformed(ActionEvent e) {
			if(allow){
				int no=Integer.parseInt(e.getActionCommand());
				check=no;
				model.action(no); //perform action on click 
				view.labelSet(model.getlabel()); // sets lable
				view.label_2Set(model.getlabel_2());
				view.statusSet(model.getstatus()); // sets status
				view.disableButton(no+5);
				//displays dialog box
				view.setDialog("Please wait for other player");
				view.dialogvisible(true);
				allow=false;
			}
		}
	}

	/**
	 * this method calls the randomnumset method
	 * @param   no   random number to be set
	 */
	
	public void random(String no){
		view.randomNumSet(no);
	}
	
	public void setCheck(int no){
		check=no;
	}

	/**
	 * this method updates the random num in model
	 * @param   no   random number to be set
	 */
	
	public void setServerRandom(String no){
		model.rand_num=Integer.parseInt(no);
		view.randomNumSet(no);
	}
}
