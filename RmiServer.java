/*RmiServer.java
 * 
 * Version:
 *   $hkak$
 *   
 * Revision:
 *   $log$
 */

/**
 *This program implements the mechanisim
 *by which server and client interacts
 *
 * @author Harshal Khandare
 * @author Ajinkya Kale
 *
 */

import java.rmi.Naming;
import java.rmi.RemoteException;

/**
 * this class is Rmi server
 * @author Ajinkya
 *
 */

public class RmiServer {
/**
 * this is a main program
 * 
 * @param     	args   		commandline arguments
 * @throws 	RemoteException
 */

	public static void main(String args[]) throws RemoteException{
		try{	
			RmiInterface object1=new RmiObj1(); // object for 
							    //RmiInterface
			RmiInterface object2=new RmiObj2();
			String s;
			if(args.length==0)
				s="";
			else
				s="//localhost:"+args[0]+"/";
			Naming.rebind(s+"object1", object1);
			Naming.rebind(s+"object2", object2);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
