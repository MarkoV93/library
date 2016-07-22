/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import model.Book;
import model.User;

/**
 *
 * @author Marko
 */
public class BookDao extends AbstractDao<Book> {

    public BookDao(DataSource ds) {
        super(ds);
    }
    public List<Book> getByGenre(String genre) throws Exception {
        List<Book> list;
       con=ds.getConnection();
        try (PreparedStatement statement = con.prepareStatement("select* from book  inner join genre  on book.genre_id=genre.id where genre.genre= ?")) {
              statement.setString(1, genre);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
            
        } catch (Exception e) {
            throw new Exception(e);
        } finally{
            con.close();
        }
        return list;
    }
     public List<Book> getByAuthor(String author) throws Exception {
        List<Book> list;
       con=ds.getConnection();
       author="%"+author+"%";
        try (PreparedStatement statement = con.prepareStatement("select* from book  inner join genre  on book.genre_id=genre.id where book.author like ?")) {
              statement.setString(1, author);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
            
        } catch (Exception e) {
            throw new Exception(e);
        } finally{
            con.close();
        }
        return list;
    }
    
     public List<String> getAuthorsOfBooks() throws Exception {
            List<String> list=new LinkedList<>();
       con=ds.getConnection();
        try (PreparedStatement statement = con.prepareStatement("select author from book group by author order by author")) {
        ResultSet rs = statement.executeQuery();
        while(rs.next()){
            list.add(rs.getString(1));
        }  } catch (Exception e) {
            throw new Exception(e);
        } finally{
            con.close();
        }
        return list;
     }
    @Override
    public List<Book> parseResultSet(ResultSet rs) throws Exception {
       List<Book> result = new LinkedList<Book>();
        try {
            while (rs.next()) {
                Book b = new Book();
                b.setTitle(rs.getString(2));
                b.setAuthor(rs.getString(3));
                b.setQty(rs.getInt(4));
                b.setGenre(rs.getString(7));
                result.add(b);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
        return result;

    }

    @Override
    public void prepareStatementForInsert(PreparedStatement st, Book b) throws Exception {
        try {

            st.setString(1, b.getTitle());
            st.setString(2, b.getAuthor());
            st.setInt(3, b.getQty());
            st.setString(4, b.getGenre());

        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public String getSelectQuery() {
        return "select * from book inner join genre on book.genre_id=genre.id order by book.title";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO book ( title,author,qty,genre_id ) VALUES(?,?,?,(select id from genre where genre=?))";
    }

    @Override
    public String getSelectCreteria() {
        return "select* from book  inner join genre  on book.genre_id=genre.id where book.title= ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM book WHERE title= ?";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE book  SET qty = ? WHERE title = ?;";
    }
}
