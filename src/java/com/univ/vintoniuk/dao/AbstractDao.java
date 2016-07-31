/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univ.vintoniuk.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;


import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;



/**
 * 
 * @author Marko
 * @param <T> 
 * In this class realized CRUD and getAll for other dao
 */
public abstract class AbstractDao<T> {

    /**
     * 
     */
    Connection con;
    DataSource ds;
   private static final Logger logger= LogManager.getLogger(AbstractDao.class);
    public AbstractDao(DataSource ds) {
        this.ds = ds;

    }
/**
 * 
 * @return list of jeneric values
 * @throws DAOLibraryException 
 */
    
  
    public List<T> getAll() throws DAOLibraryException {
        List<T> list = null;
        try {
            con = ds.getConnection();
            String sql = getSelectQuery();//invoke the override method from realization of AbstractDao
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);//inwoke method from realization of AbstruktDao for parsing ResultSet and returning list if generic
         
        } catch (SQLException ex) {
             logger.error("exeption in getAll method",ex);
            throw new DAOLibraryException("Exception on getAll in AbstractDao.Cause " + ex.getMessage());
          
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
              
   logger.warn("connection is not closed",ex);
            }
        }
        return list;
    }
/**
 * 
 * @param object of model
 * @return created model in DB or not
 * @throws DAOLibraryException 
 */
    public boolean create(T object) throws DAOLibraryException {
        try {
            con = ds.getConnection();
            T persistInstance;
            String sql = getCreateQuery();//get database query from override method
            PreparedStatement statement = con.prepareStatement(sql);
            prepareStatementForInsert(statement, object);//invoke the override method from realization of AbstractDao for inserting into String sql parameters of preparedStatement
            return statement.execute();
        } catch (SQLException ex) {
             logger.error("exeption in getAll method",ex);
            throw new DAOLibraryException("Exception on create in AbstractDao.Cause " + ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
             logger.warn("connection is not closed",ex);
            }
        }
    }
/**
 * 
 * @param its criteria of finding model from DB.It is login for user,title for book,id for reserve.In other model this method does not override
 * @return object of model
 * @throws DAOLibraryException 
 */
    public T getByCreteria(String criteria) throws DAOLibraryException {
        try {
            List<T> list;
            con = ds.getConnection();
            String sql = getSelectCreteria();//get database query from override method
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, criteria);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
            if (list == null || list.size() == 0) {
                return null;
            }
            if (list.size() > 1) {//method must return only one object of model
                 logger.error("There is more then 1 result un getByCriteria method");
                throw new DAOLibraryException("Received more than one record.");
            }
            return list.iterator().next();
        } catch (SQLException ex) {
            logger.error("exeption in getAll method",ex);
            throw new DAOLibraryException("Exception on create in AbstractDao.Cause " + ex.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                 logger.warn("connection is not closed",ex);
            }
        }

    }
/**
 * 
 * @param this parameter of model object is that we want to change.For reserve its answer,for book its qty,for user it is passsword
 * @param it is criteriaForFind object wich we wont to change.For reserve it is id,for user it is login ,for book it is title 
 * @return update object in DB or not
 * @throws DAOLibraryException 
 */
    public boolean updateByCreteria(String change, String criteriaForFind) throws DAOLibraryException {
        try {
            String sql = getUpdateQuery();//get database query from override method
            con = ds.getConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, change);//
            statement.setString(2, criteriaForFind);
            int count = statement.executeUpdate();
            if (count == 0) {
                return false;
            } else if (count != 1) {//method must update only one object of model
             logger.error("There is more then 1 result un updateByCriteria method");
                throw new DAOLibraryException("On update modify more then 1 record: " + count);

            }
            return true;
        } catch (SQLException ex) {
             logger.error("exeption in updateByCreteria method",ex);
            throw new DAOLibraryException("Exception on update in AbstractDao.Cause " + ex.getMessage());
                       //LOG
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                logger.warn("connection is not closed",ex);
            }
        }
    }
/**
 * 
 * @param it is criteria For Find object wich we wont to change.For reserve it is id,for user it is login ,for book it is title 
 * @return
 * @throws DAOLibraryException 
 */
    public boolean deleteByCreteria(String criteria) throws DAOLibraryException {
        try {
            String sql = getDeleteQuery();//get database query from override method
            con = ds.getConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, criteria);
            int count = statement.executeUpdate();
            if (count == 0) {
                return false;
            } else if (count != 1) {//method must delete only one object of model
                 logger.error("There is not one result un deleteByCriteria method");
                throw new DAOLibraryException("On delete modify more then 1 record: " + count);
                           //LOG
            }
            statement.close();
            return true;
        } catch (SQLException ex) {
          logger.error("exeption in deleteByCreteria method",ex);
            throw new DAOLibraryException("Exception on delete in AbstractDao.Cause " + ex.getMessage());
                       //LOG
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
               logger.warn("connection is not closed",ex);
            }
        }
    }

    protected abstract String getSelectQuery();

    protected abstract List<T> parseResultSet(ResultSet rs) throws DAOLibraryException;

    protected abstract String getUpdateQuery();

   protected abstract String getDeleteQuery();

    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws DAOLibraryException;

    protected abstract String getCreateQuery();

    protected abstract String getSelectCreteria();

}
