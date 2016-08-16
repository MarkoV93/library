 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univ.vintoniuk.dao;

import com.univ.vintoniuk.model.Book;
import com.univ.vintoniuk.model.Reserve;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Marko
 */
public class ReserveDaoTest {
     Connection con;
    
    @Before 
    public void getConnection() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
  String url="jdbc:mysql://127.0.0.1:3306/library";
 con=DriverManager.getConnection(url,"root","vfhrj270893"); 
    }
    
    @After
    public void closeConnection() throws SQLException {
        con.close();
    }
    
    @Test
  public  void getAll() throws DAOLibraryException, ClassNotFoundException, SQLException {
         
    DataSource ds = mock( DataSource.class);
    ReserveDao reserves=new ReserveDao(ds);
     when(ds.getConnection()).thenReturn(con);
    List<Reserve> reserveList= reserves.getAll();
     Assert.assertTrue(reserveList.size()>0);
    } 
  
    @Test
   public  void getAllWievingRes() throws DAOLibraryException, ClassNotFoundException, SQLException {
         
    DataSource ds = mock( DataSource.class);
    ReserveDao reserves=new ReserveDao(ds);
     when(ds.getConnection()).thenReturn(con);
    List<Reserve> reserveList= reserves.getAllWievingRes();
     Assert.assertTrue(reserveList.size()>0);
    } 
   
    @Test
   public  void getAllReservsForReturn() throws DAOLibraryException, ClassNotFoundException, SQLException {
         
    DataSource ds = mock( DataSource.class);
    ReserveDao reserves=new ReserveDao(ds);
     when(ds.getConnection()).thenReturn(con);
    List<Reserve> reserveList= reserves.getAllReservsForReturn();
     Assert.assertTrue(reserveList.size()>0);
    } 
  
             @Test
   public  void  getAllWievedRes() throws DAOLibraryException, ClassNotFoundException, SQLException {    
    DataSource ds = mock( DataSource.class);
    ReserveDao reserves=new ReserveDao(ds);
     when(ds.getConnection()).thenReturn(con);
    List<Reserve> reserveList= reserves. getAllWievedRes();
     Assert.assertTrue(reserveList.size()>0);
}
   
               @Test
   public  void  getAllOldResByLogin() throws DAOLibraryException, ClassNotFoundException, SQLException {    
    DataSource ds = mock( DataSource.class);
    ReserveDao reserves=new ReserveDao(ds);
     when(ds.getConnection()).thenReturn(con);
    List<Reserve> reserveList= reserves. getAllOldResByLogin("user");
     Assert.assertTrue(reserveList.size()>0);
}
   
   
       @Test
   public  void  getByCreteria() throws DAOLibraryException, ClassNotFoundException, SQLException {    
    DataSource ds = mock( DataSource.class);
    ReserveDao reserves=new ReserveDao(ds);
     when(ds.getConnection()).thenReturn(con);
   Reserve reserve= reserves. getByCreteria("109");
     Assert.assertNotNull(reserve);
}
   
}
