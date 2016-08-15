package com.univ.vintoniuk.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import com.univ.vintoniuk.model.User;
import org.apache.log4j.LogManager;

public class UserDao extends AbstractDao<User> {

    private static final org.apache.log4j.Logger logger = LogManager.getLogger(BookDao.class);
    ResourceBundle labels;

    public UserDao(DataSource ds) {
        super(ds);
          Locale locale = Locale.getDefault();
        labels = ResourceBundle.getBundle("com.univ.vintoniuk.properties.text", locale);
    }
/**
 * method for inserting in PrepareSet with query to DB for inserting user parameters from User model object
 * @throws DAOLibraryException 
 */
    @Override
    public void prepareStatementForInsert(PreparedStatement st, User u) throws DAOLibraryException {
        try {

            st.setString(1, u.getFirstName());
            st.setString(2, u.getLastName());
            st.setString(3, u.getLogin());
            st.setString(4, u.getPassword());
            st.setBoolean(5, u.isIsAdmin());
        } catch (SQLException ex) {
             logger.error("Exception on delete in UserDao",ex);
            throw new DAOLibraryException("Exception on delete in UserDao.Cause " + ex.getMessage());
        }
    }
/**
 * 
 * @param login of user
 * @param password of user
 * @return is user in DB with this login-password pair
 * @throws DAOLibraryException 
 */
    public boolean verifyUser(String login, String password) throws DAOLibraryException {

        try {
            con = ds.getConnection();
            PreparedStatement st = con.prepareStatement("select * from user where login=? and password=?");//get sqlquery from properties file
            st.setString(1, login);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            logger.error("Exception on veryfyUser in UserDao",ex);
            throw new DAOLibraryException("Exception on veryfyUser in UserDao.Cause " + ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                logger.warn("connection is not closed", ex);//Logging warning if connection does not close 
            }
        }
    }
/**
 * 
 * @param ResultSet for parsing
 * @return list of users
 * @throws DAOLibraryException 
 */
    @Override
    public List<User> parseResultSet(ResultSet rs) throws DAOLibraryException {
        LinkedList<User> result = new LinkedList<User>();
        try {
            while (rs.next()) {
                User u = new User();
                u.setFirstName(rs.getString(2));
                u.setLastName(rs.getString(3));
                u.setLogin(rs.getString(4));
                u.setPassword(rs.getString(5));
                u.setIsAdmin(rs.getBoolean(6));
                result.add(u);//add user to list for returning
            }
        } catch (SQLException ex) {
             logger.error("Exception on parsing result set in UserDao ",ex);
            throw new DAOLibraryException("Exception on parsing result set in UserDao.Cause " + ex.getMessage());
        }
        return result;

    }

    @Override
    public String getCreateQuery() {
        return labels.getString("getCreateQueryUser");
    }

    @Override
    public String getSelectCreteria() {
        return labels.getString("getSelectCreteriaUser");
    }

    @Override
    public String getSelectQuery() {
        return labels.getString("getSelectQueryUser");
    }

    @Override
    public String getDeleteQuery() {
        return labels.getString("getDeleteQueryUser");
    }

    @Override
    protected String getUpdateQuery() {
        return labels.getString("getUpdateQueryUser");
    }

}
