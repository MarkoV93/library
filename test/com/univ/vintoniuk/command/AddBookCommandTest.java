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
import com.univ.vintoniuk.dao.UserDao;
import com.univ.vintoniuk.requestWrapper.IRequestWrapper;
import java.util.Locale;
import javax.servlet.http.HttpSession;
import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Marko
 */
public class AddBookCommandTest {

   
  
 

    @Test
    public void execute() throws DAOLibraryException {
       IRequestWrapper rw = mock(IRequestWrapper.class);
       HttpSession session = mock(HttpSession.class);
        when(rw.getSession(true)).thenReturn(session);
        when(session.getAttribute("locale")).thenReturn(Locale.getDefault());
        when(rw.getParameter("add")).thenReturn("ok");
        when(rw.getParameter("title")).thenReturn("kult");
        when(rw.getParameter("author")).thenReturn("Lubko Deresh");
        when(rw.getParameter("qty")).thenReturn("5");
        when(rw.getParameter("genre")).thenReturn("Horror");
        AddBookCommand ab = mock(AddBookCommand.class);
         BookDao books = mock(BookDao.class);
        DaoFactory factory = mock(DaoFactory.class);
        when(ab.getFactory()).thenReturn(factory);
        when(factory.getBookDao()).thenReturn(books);
        when(books.getByCreteria("kult")).thenReturn(null);
        when(ab.execute(rw)).thenCallRealMethod();
        GenreDao genres = mock(GenreDao.class);
        when(factory.getGenreDao()).thenReturn(genres);
        when(genres.getAll()).thenReturn(null);
        when(books.getAll()).thenReturn(null);
        String path = ab.execute(rw);
        Assert.assertEquals(path, "AddBook.jsp");
    }
}
