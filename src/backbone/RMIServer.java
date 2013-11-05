/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package backbone;
import DBConn.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.Vector;

import javax.swing.JOptionPane;
/**
 *
 * @author the
 */
public class RMIServer {
    private dbConnection dCon;
    private Connection conn;
    private final String strServiceName="/DBServ";
    private String strServerStatus, strRMIAddress,strGUID,strName,strPassword;
    private String strIPAddress = "localhost";
    private ResultSet rs;
    private Statement stmt = null;
    private Vector vAll,vEach;
    private Registry reg;
    
    
    public RMIServer(){}
    
    public void InitializeService(int port){
        setStrRMIAddress("rmi://"+getStrIPAddress()+":"+port+strServiceName);
        try{
            RemoteInterface rIface = new RemoteImpl();
            /**
             * FIXME :
             * Using nameing will cause a problem, unexpected nested
             * Hint : Use Registry & LocateRegistry class from
             *        java.rmi.registry
             */
            
            Naming.rebind(getStrRMIAddress(),rIface);

        }
        catch (RemoteException e){
            JOptionPane.showMessageDialog(null,"Connection Error :" + e);
            System.exit(0);
        }catch (MalformedURLException e){
            JOptionPane.showMessageDialog(null,"Connection Error :" + e);
            System.exit(0);
        }
        setStrServerStatus("Connected");
    }
    
    public void DeinitializeService(){
        try {
            UnicastRemoteObject.unexportObject(reg, true);
        } catch (NoSuchObjectException ex) {
            JOptionPane.showMessageDialog(null,"Shutdown Service fail :" + ex);
        }
        setStrServerStatus("RMI Service OFF");
    }
    
    /**
     * Use vector in order to manipulate JTable content
     * @return
     * @throws RemoteException
     */
    public Vector getAllData() throws RemoteException{
        vAll = new Vector();
        try{
            this.dCon = new dbConnection();
            this.conn = (Connection)this.dCon.getConnetion();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("Select * FROM tDetails;");
            while (rs.next()){
                vEach = new Vector();
                vEach.addElement(rs.getString(1));
                vEach.addElement(rs.getString(2));
                vEach.addElement(rs.getString(3));
                vAll.add(vEach);
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Get All Data Error :" + ex);
        }
        return vAll;
    }

    /**
     * Insert into function
     * @return
     * @throws RemoteException
     */
    public int insertData() throws RemoteException{
        int x=0;
        /**
         * TODO:
         * SQL Syntax is : INSERT INTO tDetails values ('stringGUID', 'StringName', 'StringPassword')
         * use excuteUpdate() instead of executeQuery() it return 1 if the query successfully executed.
         */
        return x;
    }
    public int updateData() throws RemoteException{
        int x=0;
        /**
         * TODO:
         * SQL Syntax is : UPDATE tDetails SET NAME = 'StringName',PASSWD='StringPasswd' WHERE ID='StringGUID'
         * use excuteUpdate() instead of executeQuery() it return 1 if the query successfully executed.
         */
        return x;
    }
    public int deleteData() throws RemoteException{
        int x=0;
        /**
         * TODO:
         * SQL Syntax is : DELETE FROM tDetails WHERE ID='StringGUID'
         * use excuteUpdate() instead of executeQuery() it return 1 if the query successfully executed.
         */        
        return x;
    }
    /**
     * @return the strRMIAddress
     */
    public String getStrRMIAddress() {
        return strRMIAddress;
    }

    /**
     * @param strRMIAddress the strRMIAddress to set
     */
    public void setStrRMIAddress(String strRMIAddress) {
        this.strRMIAddress = strRMIAddress;
    }

    /**
     * @return the strIPAddress
     */
    public String getStrIPAddress() {
        return strIPAddress;
    }

    /**
     * @param strIPAddress the strIPAddress to set
     */
    public void setStrIPAddress(String strIPAddress) {
        this.strIPAddress = strIPAddress;
    }

    /**
     * @return the strGUID
     */
    public String getStrGUID() {
        return strGUID;
    }

    /**
     * @param strGUID the strGUID to set
     */
    public void setStrGUID(String strGUID) {
        this.strGUID = strGUID;
    }

    /**
     * @return the strName
     */
    public String getStrName() {
        return strName;
    }

    /**
     * @param strName the strName to set
     */
    public void setStrName(String strName) {
        this.strName = strName;
    }

    /**
     * @return the strPassword
     */
    public String getStrPassword() {
        return strPassword;
    }

    /**
     * @param strPassword the strPassword to set
     */
    public void setStrPassword(String strPassword) {
        this.strPassword = strPassword;
    }

    /**
     * @return the strServerStatus
     */
    public String getStrServerStatus() {
        return strServerStatus;
    }

    /**
     * @param strServerStatus the strServerStatus to set
     */
    public void setStrServerStatus(String strServerStatus) {
        this.strServerStatus = strServerStatus;
    }
}

