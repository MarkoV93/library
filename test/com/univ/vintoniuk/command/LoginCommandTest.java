/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univ.vintoniuk.command;

import com.univ.vintoniuk.dao.BookDao;
import com.univ.vintoniuk.dao.DAOLibraryException;
import com.univ.vintoniuk.dao.DaoFactory;
import com.univ.vintoniuk.dao.ReserveDao;
import com.univ.vintoniuk.dao.UserDao;
import com.univ.vintoniuk.model.User;
import com.univ.vintoniuk.requestWrapper.IRequestWrapper;
import java.util.Locale;
import javax.servlet.http.HttpSession;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Marko
 */
public class LoginCommandTest {

    IRequestWrapper rw;
    HttpSession session;
    LoginCommand lic;
    UserDao users;
    ReserveDao reserves;
    BookDao books;
    DaoFactory factory;
    User user;

    @Before
    public void getParameters() throws DAOLibraryException {
        rw = mock(IRequestWrapper.class);
        session = mock(HttpSession.class);
        when(rw.getSession(true)).thenReturn(session);
        when(session.getAttribute("locale")).thenReturn(Locale.getDefault());
        lic = mock(LoginCommand.class);
        books = mock(BookDao.class);
        users = mock(UserDao.class);
        reserves = mock(ReserveDao.class);
        factory = mock(DaoFactory.class);
        when(lic.getFactory()).thenReturn(factory);
        when(factory.getBookDao()).thenReturn(books);
        when(factory.getUserDao()).thenReturn(users);
        when(factory.getReserveDao()).thenReturn(reserves);

        user = mock(User.class);
        when(users.getByCreteria(anyString())).thenReturn(user);
        when(lic.execute(rw)).thenCallRealMethod();
    }

    @Test
    public void logInActivAdmin() throws DAOLibraryException {
        when(session.getAttribute("login")).thenReturn("admin");
        when(user.isIsAdmin()).thenReturn(true);
        when(reserves.getAll()).thenReturn(null);
        String path = lic.execute(rw);
        Assert.assertEquals(path, "/AdminProFile.jsp");

    }

    @Test
    public void logInActivUser() throws DAOLibraryException {
        when(session.getAttribute("login")).thenReturn("user");
        when(user.isIsAdmin()).thenReturn(false);
        String path = lic.execute(rw);
        Assert.assertEquals(path, "/UserProFile.jsp");

    }

    @Test
    public void logInAdmin() throws DAOLibraryException {
        when(session.getAttribute("login")).thenReturn(null);
        when(users.verifyUser(anyString(), anyString())).thenReturn(true);
        when(user.isIsAdmin()).thenReturn(true);
        when(reserves.getAll()).thenReturn(null);
        String path = lic.execute(rw);
        Assert.assertEquals(path, "/AdminProFile.jsp");

    }
    
     @Test
    public void logInUser() throws DAOLibraryException {
        when(session.getAttribute("login")).thenReturn(null);
        when(users.verifyUser(anyString(), anyString())).thenReturn(true);
        when(user.isIsAdmin()).thenReturn(false);
        String path = lic.execute(rw);
        Assert.assertEquals(path, "/UserProFile.jsp");

    }
    
     @Test
    public void logInFailed() throws DAOLibraryException {
 
        String path = lic.execute(rw);
        Assert.assertEquals(path, "/Login.jsp");

    }
}
