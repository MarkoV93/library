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
import com.univ.vintoniuk.model.Book;
import com.univ.vintoniuk.requestWrapper.IRequestWrapper;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Marko
 */
public class FindingCommandTest {

    IRequestWrapper rw;
    FindingCommand fc;
    DaoFactory factory;
    BookDao books;

    @Before
    public void getParameters() throws DAOLibraryException {
        rw = mock(IRequestWrapper.class);
        fc = mock(FindingCommand.class);
        HttpSession session = mock(HttpSession.class);
        when(rw.getSession(true)).thenReturn(session);
        books = mock(BookDao.class);
        factory = mock(DaoFactory.class);
        when(fc.getFactory()).thenReturn(factory);
        when(factory.getBookDao()).thenReturn(books);
         when(fc.execute(rw)).thenCallRealMethod();
    }


    
     @Test
    public void FindByGenre() throws DAOLibraryException {

        when(rw.getParameter("Find by genre")).thenReturn("Horror");
            when(rw.getParameter("genre")).thenReturn("Horror");
        when(books.getByGenre("Horror")).thenReturn(null);
        String path = fc.execute(rw);
        Assert.assertEquals(path, "/BooksByGenre.jsp");
    }
    
      @Test
    public void FindByAuthor() throws DAOLibraryException {

        when(rw.getParameter("Find by author")).thenReturn("Ok");
            when(rw.getParameter("author")).thenReturn("Lubko Deresh");
        when(books.getByAuthor("Lubko Deresh")).thenReturn(null);
        String path = fc.execute(rw);
        Assert.assertEquals(path, "/BooksByAuthor.jsp");
    }
    
      @Test
    public void reloadPage() throws DAOLibraryException {

        when(books.getAuthorsOfBooks()).thenReturn(null);
      GenreDao genres=mock(GenreDao.class);
        when(factory.getGenreDao()).thenReturn(genres);
       when(genres.getAll()).thenReturn(null);
        String path = fc.execute(rw);
        Assert.assertEquals(path, "/Finding.jsp");
    }
}
