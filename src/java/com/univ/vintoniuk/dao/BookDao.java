/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univ.vintoniuk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.sql.DataSource;
import com.univ.vintoniuk.model.Book;
import com.univ.vintoniuk.model.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 * @author Marko
 */
public class BookDao extends AbstractDao<Book> {

    ResourceBundle labels;
  private static final Logger logger= LogManager.getLogger(BookDao.class);
    public BookDao(DataSource ds) {
        super(ds);
        Locale locale = Locale.getDefault();
        labels = ResourceBundle.getBundle("com.univ.vintoniuk.properties.text", locale);
    }
/**
 * 
 * @param genre
 * @return List of books with genre='genre'
 * @throws DAOLibraryException 
 */
    public List<Book> getByGenre(String genre) throws DAOLibraryException {
        try {
            List<Book> list;
            con = ds.getConnection();
            PreparedStatement statement = con.prepareStatement(labels.getString("getSelectBookByGenre"));//get sql query from file properies depending on locale
            statement.setString(1, genre);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
            return list;
        } catch (SQLException ex) {
               logger.error("exeption in getByGenre method",ex);
            throw new DAOLibraryException("Exception on getByGenre in BookDao and " + ex.getMessage());
        } finally{
               try {
                   con.close();
               } catch (SQLException ex) {
                  logger.warn("connection is not closed",ex);//Logging warning if connection does not close 
               }
        }
    }

    public List<Book> getByAuthor(String author) throws DAOLibraryException {
        List<Book> list = null;
        try {
            con = ds.getConnection();
            PreparedStatement statement = con.prepareStatement(labels.getString("getByAuthorBook"));
                statement.setString(1, author);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
                return list;
        } catch (SQLException ex) {
               logger.error("exeption in getByAuthor method",ex);
            throw new DAOLibraryException("Exception on getByAuthor in BookDao.Cause  " + ex.getMessage());
            //Log
       } finally{
               try {
                   con.close();
               } catch (SQLException ex) {
                 logger.warn("connection is not closed",ex);//Logging warning if connection does not close 
               }
        }
    
    }

    /**
     * 
     * @return list of authors of books
     * @throws DAOLibraryException 
     */
    public List<String> getAuthorsOfBooks() throws DAOLibraryException {
        try {
            List<String> list = new LinkedList<>();
            con = ds.getConnection();
            PreparedStatement statement = con.prepareStatement(labels.getString("getAuthorsOfBooks"));//get sql query from file properies depending on locale
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));//add to list only author of book
            }
            return list;
        } catch (SQLException ex) {
               logger.error("exeption in getAuthorsOfBooks method",ex);
            throw new DAOLibraryException("Exception on getAuthors of Books and " + ex.getMessage());
            //log
         } finally{
               try {
                   con.close();
               } catch (SQLException ex) {
                  logger.warn("connection is not closed",ex);//Logging warning if connection does not close 
               }
        }
    }
/**
 * 
 * @param ResultSet for parsing
 * @return List of books
 * @throws DAOLibraryException 
 */
    @Override
    protected List<Book> parseResultSet(ResultSet rs) throws DAOLibraryException {
        List<Book> result = new LinkedList<Book>();

        try {
            while (rs.next()) {
                Book b = new Book();
                b.setTitle(rs.getString(2));
                b.setAuthor(rs.getString(3));
                b.setQty(rs.getInt(4));
                b.setGenre(rs.getString(7));
                result.add(b);//Add Book to list for returnig after parsing
            }

        } catch (SQLException ex) {
               logger.error("exeption on parsing result set of books",ex);
            throw new DAOLibraryException("Exception on parsing result set in BookDAO" + ex.getMessage());
        }

        return result;

    }

    @Override
   protected void prepareStatementForInsert(PreparedStatement st, Book b) throws DAOLibraryException  {
     

        try {
            st.setString(1, b.getTitle());
            st.setString(2, b.getAuthor());
            st.setInt(3, b.getQty());
            st.setString(4, b.getGenre());
        } catch (SQLException ex) {
               logger.error("Exception onpreparing statement for insert in BookDAO",ex);
            throw new DAOLibraryException("Exception onpreparing statement for insert in BookDAO" + ex.getMessage());
        }


    }

    @Override
    protected String getSelectQuery() {
        return labels.getString("getSelectQueryBook");
    }

    @Override
    protected String getCreateQuery() {
        return labels.getString("getCreateQueryBook");
    }

    @Override
    protected String getSelectCreteria() {
        return labels.getString("getSelectCreteriaBook");
    }

    @Override
    protected String getDeleteQuery() {
        return labels.getString("getDeleteQueryBook");
    }

    @Override
    protected String getUpdateQuery() {
        return labels.getString("getUpdateQueryBook");
    }
}
