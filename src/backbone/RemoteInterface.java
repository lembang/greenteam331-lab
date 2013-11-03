/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package backbone;
import java.rmi.*;
import java.util.*;
/**
 *
 * @author the
 */
public interface RemoteInterface extends Remote {
    public Vector getAllData() throws RemoteException;
    public int insertData() throws RemoteException;
    public int updateData() throws RemoteException;
    public int deleteData() throws RemoteException;
    public void setStrGUID(String strGUID) throws RemoteException;
    public void setStrName(String strName) throws RemoteException;
    public void setStrPassword(String strPassword) throws RemoteException;
}
