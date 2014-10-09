/*RmiObj1.java
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


import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

/**
 * this class extens UnicastRemoterObject and implements
 * RmiInterface. it provides implementation by which
 * server and client interacts
 *
 */
public class RmiObj1 extends UnicastRemoteObject implements RmiInterface {
	public static int num=100;
	public RmiObj1() throws RemoteException{
	}

	/**
	 * this method sets the action no
         * @param 	no 	number to be set
	 */

	public void emulate(int no){
		num=no;
	}

	/**
	 * this method sends the action value
	 * @return  	check
	 */

	public int give(){
		int check=100;
		while(num>10){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			check=num;
		}
		num=100;
		return check;
	}
}
