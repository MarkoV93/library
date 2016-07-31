/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univ.vintoniuk.command;

import com.univ.vintoniuk.dao.BookDao;
import com.univ.vintoniuk.dao.DAOLibraryException;
import com.univ.vintoniuk.dao.DaoFactory;
import com.univ.vintoniuk.dao.GenreDao;
import com.univ.vintoniuk.dao.ReserveDao;
import com.univ.vintoniuk.dao.UserDao;
import com.univ.vintoniuk.model.Book;
import com.univ.vintoniuk.requestWrapper.IRequestWrapper;
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
public class ReserveCommandTest {

    IRequestWrapper rw;
    HttpSession session;
    Command rc;
    DaoFactory factory;
    BookDao books;

    @Before
    public void getParameters() throws DAOLibraryException {
        rw = mock(IRequestWrapper.class);
        session = mock(HttpSession.class);
        when(rw.getSession(true)).thenReturn(session);
        rc = mock(ReserveCommand.class);
        factory = mock(DaoFactory.class);
        when(rc.getFactory()).thenReturn(factory);
        books = mock(BookDao.class);
        when(factory.getBookDao()).thenReturn(books);
        when(rc.execute(rw)).thenCallRealMethod();
    }

    @Test
    public void findWrongTitleBook() throws DAOLibraryException {
        GenreDao genres = mock(GenreDao.class);
        when(factory.getGenreDao()).thenReturn(genres);
        when(genres.getAll()).thenReturn(null);
        when(books.getAuthorsOfBooks()).thenReturn(null);
        String path = rc.execute(rw);
        Assert.assertEquals(path, "/Finding.jsp");
    }

    @Test
    public void goToReserveBook() throws DAOLibraryException {
        when(rw.getParameter("title")).thenReturn("kult");
        when(books.getByCreteria(anyString())).thenReturn(new Book());
        String path = rc.execute(rw);
        Assert.assertEquals(path, "/CreateReserve.jsp");

    }

    @Test
    public void wrongReserve() throws DAOLibraryException {
        when(session.getAttribute("titleBookforReserve")).thenReturn("kult");
        when(rw.getParameter("act")).thenReturn(null);
        when(books.getByCreteria(anyString())).thenReturn(new Book());
        String path = rc.execute(rw);
        Assert.assertEquals(path, "/CreateReserve.jsp");

    }

    @Test
    public void reserve() throws DAOLibraryException {
        when(session.getAttribute("titleBookforReserve")).thenReturn("kult");
        when(rw.getParameter("act")).thenReturn("act");
        ReserveDao reserves = mock(ReserveDao.class);
        when(factory.getReserveDao()).thenReturn(reserves);
        when(books.getAll()).thenReturn(null);
        String path = rc.execute(rw);
        Assert.assertEquals(path, "/UserProFile.jsp");

    }
}
