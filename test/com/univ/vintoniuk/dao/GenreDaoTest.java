/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univ.vintoniuk.dao;

import com.univ.vintoniuk.model.Genre;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.sql.DataSource;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Marko
 */
public class GenreDaoTest {

    Connection con;

    @Before
    public void getConnection() throws ClassNotFoundException, SQLException {
        ResourceBundle labels = ResourceBundle.getBundle("com.univ.vintoniuk.properties.text", Locale.getDefault());
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1:3306/library";
        con = DriverManager.getConnection(url, "root", labels.getString("passwordToDB"));
    }

    @After
    public void closeConnection() throws SQLException {
        con.close();
    }

    @Test
    public void getAll() throws DAOLibraryException, ClassNotFoundException, SQLException {

        DataSource ds = mock(DataSource.class);
        GenreDao genres = new GenreDao(ds);
        when(ds.getConnection()).thenReturn(con);
        List<Genre> genreList = genres.getAll();
        Assert.assertNotNull(genreList);
    }
}
