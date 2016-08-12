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
import com.univ.vintoniuk.model.Answer;
import com.univ.vintoniuk.model.Book;
import com.univ.vintoniuk.model.Reserve;
import com.univ.vintoniuk.requestWrapper.IRequestWrapper;
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
public class WievReservesCommandTest {

    IRequestWrapper rw;
    Command wrc;
    DaoFactory factory;
    BookDao books;
    ReserveDao reserves;
    Reserve reserve;
    Book book;

    @Before
    public void getParameters() throws DAOLibraryException {
        rw = mock(IRequestWrapper.class);
        wrc = mock(WievReservesCommand.class);
        factory = mock(DaoFactory.class);
        when(wrc.getFactory()).thenReturn(factory);
        books = mock(BookDao.class);
        when(factory.getBookDao()).thenReturn(books);
        when(wrc.execute(rw)).thenCallRealMethod();
        reserves = mock(ReserveDao.class);
        when(factory.getReserveDao()).thenReturn(reserves);
        when(reserves.getAllWievingRes()).thenReturn(null);
        reserve = mock(Reserve.class);
        book = mock(Book.class);
        when(reserves.getByCreteria(Matchers.anyString())).thenReturn(reserve);
        Book book=new Book();
        book.setTitle("kult");
        when(reserve.getBook()).thenReturn(book);
        when(books.getByCreteria(Matchers.anyString())).thenReturn(book);
        Answer answer=new Answer();
        answer.setAnswer("wiev for give to hend");
        when(reserve.getAnswer()).thenReturn(answer);
    }

    @Test
    public void cencelReserve() throws DAOLibraryException {
        when(rw.getParameter("censelId")).thenReturn("2");
        String path = wrc.execute(rw);
        Assert.assertEquals(path, "WievReserves.jsp");
    }

    @Test
    public void applyReserve() throws DAOLibraryException {
        when(rw.getParameter("applyId")).thenReturn("2");
         
        String path = wrc.execute(rw);
        Assert.assertEquals(path, "WievReserves.jsp");
    }
 @Test
    public void canNotApplyReserve() throws DAOLibraryException {
        when(rw.getParameter("applyId")).thenReturn("2");
        when(book.getQty()).thenReturn(0);
        String path = wrc.execute(rw);
        Assert.assertEquals(path, "WievReserves.jsp");
    }
}
