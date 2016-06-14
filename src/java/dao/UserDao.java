
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
import model.User;


public class UserDao {
    Connection con;
    public UserDao(Connection con) {
        this.con=con;
    } 
    public boolean verifyAndInsert(User u) {
        String firstName=u.getFirstName();
        String lastName=u.getLastName();
        String login=u.getLogin();
        String password=u.getPassword();
        boolean isAdmin=u.isIsAdmin();
        try {
            PreparedStatement st = con.prepareStatement("select * from user where first_name=? and last_name=? and login=? and password=?");
            st.setString(1, firstName);
             st.setString(2, lastName);
            st.setString(3, login);
            st.setString(4, password);
            ResultSet rs=st.executeQuery();
            if(rs.next()){
                return true;
                
            }else {
               st=con.prepareStatement("insert into user(first_name,last_name, login, password,isAdmin) values (?,?,?,?,?)");
               st.setString(1, firstName);
               st.setString(2, lastName);
               st.setString(3, login);
               st.setString(4, password);
               st.setBoolean(5, isAdmin);
               st.execute();
                
               return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return false;
    }
    public boolean verifyUser(User u){
         String login=u.getLogin();
            String password=u.getPassword();
        try {     
            PreparedStatement st = con.prepareStatement("select * from user where login=? and password=?");
            st.setString(1, login);
            st.setString(2, password);
            ResultSet rs=st.executeQuery();
            return rs.next();       
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public List<User> getAll(){
        try {
            Statement st=con.createStatement();
            ResultSet rs= st.executeQuery("select* from user");
            List<User> users=new ArrayList<>();
            while(rs.next()) {
                User u=new User();
                u.setFirstName(rs.getString(2));
                 u.setLastName(rs.getString(3));
                u.setLogin(rs.getString(4));
                u.setPassword(rs.getString(5));
                u.setIsAdmin(rs.getBoolean(6));
                users.add(u);
                
            }
            return users;
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
