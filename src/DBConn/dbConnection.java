/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DBConn;

import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author the
 */
public class dbConnection {
    private Connection conn=null;
    
    public dbConnection(){
        try{
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:/Users/the/NetBeansProjects/greenteam331-labpresentation/lab.db");
            //conn.setAutoCommit(false);
        }
        catch(ClassNotFoundException | SQLException ex){
            JOptionPane.showMessageDialog(null,"Connection Error :" + ex);
        }
    }
    
    public Connection getConnetion()
    {
        return this.conn;
    }

}
