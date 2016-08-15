/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univ.vintoniuk.dao;

import com.univ.vintoniuk.model.Book;
import com.univ.vintoniuk.model.User;
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
public class UserDaoTest {
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
   UserDao users=new UserDao(ds);
     when(ds.getConnection()).thenReturn(con);
    List<User> userList= users.getAll();
     Assert.assertNotNull(userList);
    }
   @Test
  public void verifyUser() throws SQLException{
        DataSource ds = mock( DataSource.class);
   UserDao users=new UserDao(ds);
     when(ds.getConnection()).thenReturn(con);
     Assert.assertTrue(users.verifyUser("admin", "admin"));
  }
}
