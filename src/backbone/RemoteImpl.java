/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package backbone;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

/**
 *
 * @author the
 */
public class RemoteImpl extends UnicastRemoteObject implements RemoteInterface {
    
    private final RMIServer rServer = new RMIServer();
    public RemoteImpl()throws RemoteException{}

    @Override
    public Vector getAllData() throws RemoteException {
        return rServer.getAllData();
    }

  

	@Override
	public int insertData() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateData() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteData() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setStrGUID(String strGUID) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setStrName(String strName) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setStrPassword(String strPassword) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

   
    
    
  
}
