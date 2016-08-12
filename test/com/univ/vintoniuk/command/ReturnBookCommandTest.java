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
import com.univ.vintoniuk.model.Book;
import com.univ.vintoniuk.model.Reserve;
import com.univ.vintoniuk.requestWrapper.IRequestWrapper;
import javax.servlet.http.HttpSession;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Marko
 */
public class ReturnBookCommandTest {
      IRequestWrapper rw;
    Command rbc;
    DaoFactory factory;
    BookDao books;
 ReserveDao reserves;
    @Before
    public void getParameters() throws DAOLibraryException {
        rw = mock(IRequestWrapper.class);
        rbc= mock(ReturnBookCommand.class);
        factory = mock(DaoFactory.class);
        when(rbc.getFactory()).thenReturn(factory);
        books = mock(BookDao.class);
        when(factory.getBookDao()).thenReturn(books);
        when(rbc.execute(rw)).thenCallRealMethod();
        reserves=mock(ReserveDao.class);
        when(factory.getReserveDao()).thenReturn(reserves);
        when(reserves.getAllReservsForReturn()).thenReturn(null);
    }
    @Test
    public void goToReturningBook() throws DAOLibraryException{
        String path = rbc.execute(rw);
        Assert.assertEquals(path, "/ReturnBook.jsp");
    }
    
     @Test
    public void returningBook() throws DAOLibraryException{
        when(rw.getParameter("returnId")).thenReturn("4");
        Reserve reserve=mock(Reserve.class);
        when(reserves.getByCreteria(Matchers.anyString())).thenReturn(reserve);
        Book book=new Book();
        book.setTitle("kult");
        when(reserve.getBook()).thenReturn(book);
        when(books.getByCreteria(Matchers.anyString())).thenReturn(new Book());
        String path = rbc.execute(rw);
        Assert.assertEquals(path, "/ReturnBook.jsp");
    }
}
