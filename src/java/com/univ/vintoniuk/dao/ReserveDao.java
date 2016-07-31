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
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import com.univ.vintoniuk.model.Book;
import com.univ.vintoniuk.model.Reserve;
import com.univ.vintoniuk.model.User;
import org.apache.log4j.LogManager;

public class ReserveDao extends AbstractDao<Reserve> {

    ResourceBundle labels;
    private static final org.apache.log4j.Logger logger = LogManager.getLogger(BookDao.class);

    public ReserveDao(DataSource ds) {
        super(ds);
        Locale locale = Locale.getDefault();
        labels = ResourceBundle.getBundle("com.univ.vintoniuk.properties.text", locale);
    }

    /**
     *
     * @return list of reservis with fild of answer:"wiev for give to hand,wiev
     * for give to reading room
     * @throws DAOLibraryException
     */
    public List<Reserve> getAllWievingRes() throws DAOLibraryException {
        List<Reserve> list;
        String sql = labels.getString("getAllWievingRes");//get sqlquery from properties file

        list = this.getAllForCommand(sql);//inwoke private method wich create PrepareStatement and pars ResultSet

        return list;
    }

    /**
     *
     * @param sql query in DB which return list of Reserves
     * @return return List of Reserves depending on @param sql
     * @throws DAOLibraryException
     */
    private List<Reserve> getAllForCommand(String sql) throws DAOLibraryException {
        try {
            List<Reserve> list;
            con = ds.getConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);//parsing ResultSet 

            return list;
        } catch (SQLException ex) {
            logger.error("Exception on delete in AbstractDao.Cause ", ex);
            throw new DAOLibraryException("Exception on delete in AbstractDao.Cause " + ex.getMessage());
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
     * @return list of reservis with fild of answer:"give to hand,give in
     * reading room
     * @throws DAOLibraryException
     */
    public List<Reserve> getAllReservsForReturn() throws DAOLibraryException {
        List<Reserve> list;
        String sql = labels.getString("getAllReservsForReturn");//get sqlquery from properties file
        list = this.getAllForCommand(sql);//inwoke private method wich create PrepareStatement and pars ResultSet
        return list;
    }

    /**
     * @return list of reservis with fild of answer:"returned ,refused
     * @throws DAOLibraryException
     */
    public List<Reserve> getAllWievedRes() throws DAOLibraryException {
        List<Reserve> list;
        String sql = labels.getString("getAllWievedRes");//get sqlquery from properties file
        list = this.getAllForCommand(sql);//inwoke private method wich create PrepareStatement and pars ResultSet
        return list;
    }

    /**
     * @return list of reservis with fild of answer:"returned" ,"refused" and
     * with login_id like id in user whis login @param login
     * @throws DAOLibraryException
     */
    public List<Reserve> getAllOldResByLogin(String login) throws DAOLibraryException {
        List<Reserve> list;
        String sql = labels.getString("getAllOldResByLogin");
        sql += login + "'";
        list = this.getAllForCommand(sql);
        return list;
    }

    /**
     * @return list of all reservis with login_id like id in user whis login
     * @param login
     * @throws DAOLibraryException
     */
    public List<Reserve> getReservesByLogin(String login) throws DAOLibraryException {
        List<Reserve> list;
        String sql = labels.getString("getReservesByLogin");//get sqlquery from properties file
        sql += login + "'";
        list = this.getAllForCommand(sql);//inwoke private method wich create PrepareStatement and pars ResultSet
        return list;
    }
//override method for getAll method

    @Override
    protected String getSelectQuery() {
        return labels.getString("getSelectQueryReserve");
    }

    /**
     *
     * @param ResultSet for parsing
     * @return list of reserves
     * @throws DAOLibraryException
     */
    @Override
    protected List<Reserve> parseResultSet(ResultSet rs) throws DAOLibraryException {
        List<Reserve> result = new LinkedList<>();
        try {
            while (rs.next()) {
                Reserve r = new Reserve();
                r.setId(rs.getInt(1));
                r.setDate(rs.getString(5));
                r.setUserLogin(rs.getString(9));
                r.setBookTitle(rs.getString(13));
                r.setAnswer(rs.getString(18));

                result.add(r);//Add reserve  to list for returnig after parsing
            }
        } catch (SQLException ex) {
            logger.error("Exception on parsing result set in ReservetDao.Cause ", ex);
            throw new DAOLibraryException("Exception on parsing result set in ReservetDao.Cause " + ex.getMessage());
        }
        return result;
    }

    /**
     *
     * @return database query por updating Reserve in DB,
     */
    @Override
    protected String getUpdateQuery() {
        Locale local = new Locale("ru", "RU");
        DateFormat df = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, local);
        Date currentDate = new Date();
        return "UPDATE reserve  SET answer =(select id from answer where answer=?), date='" + df.format(currentDate) + "' WHERE id = ?;";
    }

    /**
     *
     * @return query for delete reserve
     */
    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM reserve WHERE id= ?;";
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Reserve r) throws DAOLibraryException {
        try {

            statement.setString(1, r.getUserLogin());
            statement.setString(2, r.getBookTitle());
            statement.setString(3, r.getAnswer());

        } catch (SQLException ex) {
            throw new DAOLibraryException("Exception on delete in AbstractDao.Cause " + ex.getMessage());
        }
    }

    @Override
    protected String getCreateQuery() {
        Locale local = new Locale("ru", "RU");
        DateFormat df = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, local);
        Date currentDate = new Date();
        return "INSERT INTO reserve ( user_id,book_id,answer,date) VALUES((select id from user where login =?),(select id from book where title = ?),(select id from answer where answer=?),'" + df.format(currentDate) + "')";
    }

    @Override
    protected String getSelectCreteria() {
        return labels.getString("getSelectCreteriaReserve");

    }
}
