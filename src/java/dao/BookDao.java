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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Book;
import model.User;

/**
 *
 * @author Marko
 */
public class BookDao {
    Connection con;
    public BookDao(Connection con){
        this.con=con;
    }
     public List<Book> getList(){
        try {
            Statement st=con.createStatement();
            ResultSet rs= st.executeQuery("SELECT * From book inner JOIN genre On book.genre_id=genre.id;");
            List<Book> books=new ArrayList<>();
            while(rs.next()) {
                Book b=new Book();
                b.setTitle(rs.getString(2));
                 b.setAuthor(rs.getString(3));
                b.setQty(rs.getInt(4));
               b.setGenre(rs.getString(6));
                books.add(b);
                
            }
            return books;
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     public void add(Book b){
         String title=b.getTitle();
         String author=b.getAuthor();
         int qty=b.getQty();
         int genreId=b.getGenreId();
           try {
            PreparedStatement st = con.prepareStatement("select * from Book where title=? and author=? and  genre_id=?");
            st.setString(1, title);
             st.setString(2, author);
 
            st.setInt(3, genreId);
            ResultSet rs=st.executeQuery();
            if(rs.next()){
                addNumberOfBook(b,qty);
            
                
            }else {
               st=con.prepareStatement("insert into book(title,author, qty, genre_id) values (?,?,?,?)");
               st.setString(1, title);
               st.setString(2, author);
               st.setInt(3, qty);
               st.setInt(4, genreId);
       
               st.execute();
                
           
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
   
     }
     public void addNumberOfBook(Book b,int n){
          String title=b.getTitle();    
         try {
            PreparedStatement st = con.prepareStatement("UPDATE book SET qty = qty + ? WHERE title = '?';");
                st.setInt(1, n);
               st.setString(2, title);
           } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
     }
}
