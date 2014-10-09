/*RmiMainClient1.java
 * 
 * Version:
 *   $hkak$
 *   
 * Revision:
 *   $log$
 */

/**
 *This program contains main method for the client1
 *
 * @author Harshal Khandare
 * @author Ajinkya Kale
 *
 */

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * this class contains main method
 */

public class RmiMainClient1 {
	
     /**
      * this is main program
      * @param  args  commandline arguments
      */
	
      public static void main(String args[]) throws IOException{
        RmiModel model=new RmiModel(); // object of model
        RmiView view=new RmiView(model); // object if view
        RmiController controller=new RmiController(view,model);
        RmiInteractClient1 interact=
			new RmiInteractClient1(controller,view,args);
        interact.begin(args);  // starts the client interaction
	}
}

