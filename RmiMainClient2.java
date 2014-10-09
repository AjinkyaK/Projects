/*RmiMainClient2.java
 * 
 * Version:
 *   $hkak$
 *   
 * Revision:
 *   $log$
 */

/**
 *This program contains main method for the client2 
 *
 * @author Harshal Khandare
 * @author Ajinkya Kale
 *
 */

import java.io.*;
import java.util.*;

/**
 * this class contain main program
 */

public class RmiMainClient2 {
	
     /**
      * this is main program
      * @param  args  commandline arguments
      */
	
     public static void main(String args[]) throws IOException{
	RmiModel model=new RmiModel(); // object of model
	RmiView view=new RmiView(model); // object of view
	RmiController controller=new RmiController(view,model);
	RmiInteractClient2 interact2=
			new RmiInteractClient2(controller,view,args);
	interact2.begin(args);  // starts the socket connection for client
	}
}
