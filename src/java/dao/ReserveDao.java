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
import model.Reserve;
import model.User;


public class ReserveDao {
        Connection con;
    public ReserveDao(Connection con){
        this.con=con;
    }
         public List<Reserve> getList(){
        try {
            Statement st=con.createStatement();
            ResultSet rs= st.executeQuery("SELECT * From reserve ");
            List<Reserve> reserves=new ArrayList<>();
            while(rs.next()) {
                Reserve r=new Reserve();
                r.setUserId(rs.getInt(2));
                 r.setBookId(rs.getInt(3));
                r.setAnswer(rs.getInt(4));
                reserves.add(r);
                
            }
            return reserves;
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
         
         public void add(User user,int bookId){
        int userId=user.getId();
        int answer=0;
         try {
         
               PreparedStatement st=con.prepareStatement("insert into reserve(user_id,book_id, answer) values (?,?,?)");
               st.setInt(1, userId);
               st.setInt(2, bookId);
               st.setInt(3, answer);

       
               st.execute();
                
           
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
    }
         public void changeAnswe(Reserve reserve,int answer){
             int id=reserve.getId();
                try {
            PreparedStatement st = con.prepareStatement("UPDATE reserve SET answer =  ? WHERE id = '?';");
                st.setInt(1, answer);
               st.setInt(2,id);
           } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
         }
}
