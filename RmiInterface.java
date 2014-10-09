/*RmiInterface.java
 * 
 * Version:
 *   $hkak$
 *   
 * Revision:
 *   $log$
 */


/**
 * this class is a interface class which extends remote used by server objects
 * @author Harshal Khandare
 * @author Ajinkya Kale
 */

import java.rmi.*;

public interface RmiInterface extends Remote {
	public void emulate(int no) throws RemoteException;
	public int give() throws RemoteException;
}
