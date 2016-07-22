package dao;

import java.sql.Connection;
import java.sql.Date;
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
import model.User;

public class UserDao extends AbstractDao<User> {


    public UserDao(DataSource ds) {
        super(ds);
    }

    @Override
    public void prepareStatementForInsert(PreparedStatement st, User u) throws Exception {
        try {

            st.setString(1, u.getFirstName());
            st.setString(2, u.getLastName());
            st.setString(3, u.getLogin());
            st.setString(4, u.getPassword());
            st.setBoolean(5, u.isIsAdmin());
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public boolean verifyUser(User u) throws SQLException {
        String login = u.getLogin();
        String password = u.getPassword();
          con=ds.getConnection();
        try {
            PreparedStatement st = con.prepareStatement("select * from user where login=? and password=?");
            st.setString(1, login);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            con.close();
        }
        return false;
    }

    @Override
    public List<User> parseResultSet(ResultSet rs) throws Exception {
        LinkedList<User> result = new LinkedList<User>();
        try {
            while (rs.next()) {
                User u = new User();
                u.setFirstName(rs.getString(2));
                u.setLastName(rs.getString(3));
                u.setLogin(rs.getString(4));
                u.setPassword(rs.getString(5));
                u.setIsAdmin(rs.getBoolean(6));
                result.add(u);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
        return result;

    }
    @Override
    public String getCreateQuery() {
        return "insert into user(first_name,last_name, login, password,isAdmin) values (?,?,?,?,?)";
    }
    @Override
    public String getSelectCreteria() {
        return "select * from user where login=? ";
    }
    @Override
    public String getSelectQuery() {
        return "select * from user ";
    }
    @Override
    public String getDeleteQuery() {
       return "DELETE FROM user WHERE login= ?;";
    }
    @Override
    protected String getUpdateQuery() {
     return "UPDATE user  SET password = ? WHERE login = ?;";
    }

}
