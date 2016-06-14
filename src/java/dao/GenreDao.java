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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Book;
import model.Genre;


public class GenreDao {
     Connection con;

       public GenreDao(Connection con){
        this.con=con;
}
        public void add(Genre g){
         String genre=g.getGenre();
           try {
            PreparedStatement st = con.prepareStatement("select * from Genre where genre=?");
            st.setString(1, genre);
            ResultSet rs=st.executeQuery();
            if(rs.next()){
            return;
            
                
            }else {
               st=con.prepareStatement("insert into genre(genre) values (?)");
               st.setString(1, genre);
               st.execute();         
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
   
     }
        public void remove(String genre){
          try {
            PreparedStatement st = con.prepareStatement("DELETE FROM genre WHERE genre = '?';");
                st.setString(1, genre);
           } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        }
}
