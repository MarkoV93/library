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
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import com.univ.vintoniuk.model.Book;
import com.univ.vintoniuk.model.Genre;
import org.apache.log4j.LogManager;

public class GenreDao extends AbstractDao<Genre> {

    ResourceBundle labels;
    private static final org.apache.log4j.Logger logger = LogManager.getLogger(BookDao.class);

    public GenreDao(DataSource ds) {
        super(ds);
        Locale locale = Locale.getDefault();
        labels = ResourceBundle.getBundle("com.univ.vintoniuk.properties.text", locale);
    }

    /**
     *
     * @param ResultSet for parsing
     * @return
     * @throws DAOLibraryException
     */
    @Override
    protected List<Genre> parseResultSet(ResultSet rs) throws DAOLibraryException {
        List<Genre> result = new LinkedList<Genre>();
        try {
            while (rs.next()) {
                Genre b = new Genre();
                b.setGenre(rs.getString(2));
                result.add(b);
            }
        } catch (SQLException ex) {
            logger.error("Exception on parsing result set in GenreDao.Cause ", ex);
            throw new DAOLibraryException("Exception on parsing result set in GenreDao.Cause " + ex.getMessage());
        }
        return result;
    }

    @Override
    protected String getSelectQuery() {
        return labels.getString("getSelectQueryGenre");
    }

    @Override
    protected String getUpdateQuery() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDeleteQuery() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void prepareStatementForInsert(PreparedStatement statement, Genre object) throws DAOLibraryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCreateQuery() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String getSelectCreteria() {
        return labels.getString("getSelectCreteriaGenre");
    }
}
