/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univ.vintoniuk.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import com.univ.vintoniuk.model.Reserve;

public class DaoFactory {

    private static DaoFactory factory = new DaoFactory();
    DataSource ds = null;

    private DaoFactory() {
        try {
            InitialContext contex = new InitialContext();
            ds = (DataSource) contex.lookup("java:comp/env/jdbc/dbconnect");
        } catch (NamingException ex) {
            Logger.getLogger(DaoFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public UserDao getUserDao() {
        UserDao dao = null;
        dao = new UserDao(ds);
        return dao;
    }

    public GenreDao getGenreDao() {
        GenreDao dao = null;
        dao = new GenreDao(ds);

        return dao;
    }

    public BookDao getBookDao() {
        BookDao dao = null;

        dao = new BookDao(ds);

        return dao;
    }

    public ReserveDao getReserveDao() {
        ReserveDao dao = null;
        dao = new ReserveDao(ds);
        return dao;
    }

    public DaoFactory getFactory() {
        return factory;
    }

    public static DaoFactory getInstance() {
        return factory;
    }

}
