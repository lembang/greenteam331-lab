/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package backbone;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

/**
 * 
 * @author the
 */
public class RMIClient {
	private RemoteInterface rIface;
	private final String strServiceName = "/DBServ";
	private String strIPAddress, strRMIAddress;
	private Registry Reg;

	public RMIClient() {
	}

	public void InitializeService(int port) {
		setStrRMIAddress("rmi://" + getStrIPAddress() + ":" + port
				+ strServiceName);
		try {
			/**
			 * FIXME : Using naming will cause a problem, unexpected nested Hint
			 * : Use Registry & LocateRegistry class from java.rmi.registry
			 */
			rIface = (RemoteInterface) Naming.lookup(getStrRMIAddress());
		} catch (NotBoundException e) {
		JOptionPane.showMessageDialog(null, "Connection Error :" + e);
			System.exit(0);
		} catch (RemoteException e) {

			JOptionPane.showMessageDialog(null, "Connection Error :" + e);
			System.exit(0);
		} catch (MalformedURLException e) {
			JOptionPane.showMessageDialog(null, "Connection Error :" + e);
			System.exit(0);
		}
		System.out.println("Client Connected");
	}

	public void DeinitializeService() {
		try {
			UnicastRemoteObject.unexportObject(Reg, true);
		} catch (NoSuchObjectException ex) {
			JOptionPane.showMessageDialog(null, "Shutdown Service fail :" + ex);
		}
	}

	public Vector getAllDataToClient() {
		Vector AllData = new Vector();
		try {
			AllData = rIface.getAllData();
		} catch (RemoteException ex) {
			JOptionPane.showMessageDialog(null, "GetAllDataToClient Error :"
					+ ex);
		}
		return AllData;
	}

	public void insertDataToServer(String strGUID, String strName,
			String strPassword) {
		try {
			rIface.setStrGUID(strGUID);
			rIface.setStrName(strName);
			rIface.setStrPassword(strPassword);
			rIface.insertData();
		} catch (RemoteException ex) {
			JOptionPane.showMessageDialog(null, "InsertDataToServer Error :"
					+ ex);
		}
	}

	public void updateDataToServer(String strGUID, String strName,
			String strPassword) {
		try {
			rIface.setStrGUID(strGUID);
			rIface.setStrName(strName);
			rIface.setStrPassword(strPassword);
			rIface.updateData();
		} catch (RemoteException ex) {
			JOptionPane.showMessageDialog(null, "UpdateDataToServer Error :"
					+ ex);
		}
	}

	public void DeleteDataOnServer(String strGUID) {
		try {
			rIface.setStrGUID(strGUID);
			rIface.deleteData();
		} catch (RemoteException ex) {
			JOptionPane.showMessageDialog(null,
					"Delete data on the server failed :" + ex);
		}
	}

	/**
	 * @return the strIPAddress
	 */
	public String getStrIPAddress() {
		return strIPAddress;
	}

	/**
	 * @param strIPAddress
	 *            the strIPAddress to set
	 */
	public void setStrIPAddress(String strIPAddress) {
		this.strIPAddress = strIPAddress;
	}

	/**
	 * @return the strRMIAddress
	 */
	public String getStrRMIAddress() {
		return strRMIAddress;
	}

	/**
	 * @param strRMIAddress
	 *            the strRMIAddress to set
	 */
	public void setStrRMIAddress(String strRMIAddress) {
		this.strRMIAddress = strRMIAddress;
	}
}
