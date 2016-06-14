/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class DaoFactory {
    private static DaoFactory factory=new DaoFactory();
    DataSource ds=null;
    private DaoFactory() {   
        try {
            InitialContext contex=new InitialContext();
            ds=(DataSource)contex.lookup("java:comp/env/jdbc/dbconnect");
        } catch (NamingException ex) {
            Logger.getLogger(DaoFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public UserDao getUserDao(){
        UserDao dao=null;
        try {
            Connection con=ds.getConnection();
            dao=new UserDao(con);
        } catch (SQLException ex) {
            Logger.getLogger(DaoFactory.class.getName()).log(Level.SEVERE, null, ex);
       
        }
        return dao;
    }
        public BookDao getBookDao(){
        BookDao dao=null;
        try {
            Connection con=ds.getConnection();
            dao=new BookDao(con);
        } catch (SQLException ex) {
            Logger.getLogger(DaoFactory.class.getName()).log(Level.SEVERE, null, ex);
       
        }
        return dao;
    }
    public static DaoFactory getInstance(){
        return factory;
    }
    
}
