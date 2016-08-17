package com.univ.vintoniuk.dao;

import com.univ.vintoniuk.command.RegisterCommand;
import com.univ.vintoniuk.model.Book;
import com.univ.vintoniuk.model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.sql.DataSource;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Marko
 */
public class BookDaoTest {

    Connection con;
    
    @Before    
    public void getConnection() throws ClassNotFoundException, SQLException {
        ResourceBundle labels = ResourceBundle.getBundle("com.univ.vintoniuk.properties.text", Locale.getDefault());
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1:3306/library";
        con = DriverManager.getConnection(url, "root", labels.getString("passwordToDB"));        
    }
    
    @After
    public void closeConnection() throws SQLException {
        con.close();
    }
    
    @Test
    public void getAll() throws DAOLibraryException, ClassNotFoundException, SQLException {
        
        DataSource ds = mock(DataSource.class);
        BookDao books = new BookDao(ds);
        when(ds.getConnection()).thenReturn(con);
        List<Book> bookList = books.getAll();
        Assert.assertNotNull(bookList);
    }
    
    @Test
    public void getByGenre() throws DAOLibraryException, ClassNotFoundException, SQLException {
        
        DataSource ds = mock(DataSource.class);
        BookDao books = new BookDao(ds);
        when(ds.getConnection()).thenReturn(con);
        List<Book> bookList = books.getByGenre("Fable");
        Assert.assertNotNull(bookList);
    }
    
    @Test
    public void getByAuthor() throws DAOLibraryException, ClassNotFoundException, SQLException {
        
        DataSource ds = mock(DataSource.class);
        BookDao books = new BookDao(ds);
        when(ds.getConnection()).thenReturn(con);
        List<Book> bookList = books.getByAuthor("Lubko Deresh");
        Assert.assertNotNull(bookList);
    }
    
    @Test
    public void getAuthorsOfBooks() throws DAOLibraryException, ClassNotFoundException, SQLException {
        
        DataSource ds = mock(DataSource.class);
        BookDao books = new BookDao(ds);
        when(ds.getConnection()).thenReturn(con);
        List<String> bookList = books.getAuthorsOfBooks();
        Assert.assertNotNull(bookList);
    }
}
