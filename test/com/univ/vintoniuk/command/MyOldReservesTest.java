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
import com.univ.vintoniuk.model.Book;
import com.univ.vintoniuk.model.Reserve;
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
public class MyOldReservesTest {

    IRequestWrapper rw;
    HttpSession session;
    MyOldReservesCommand morc;
    ReserveDao reserves;
    DaoFactory factory;

    @Before
    public void getParameters() throws DAOLibraryException {
        rw = mock(IRequestWrapper.class);
        session = mock(HttpSession.class);
        when(rw.getSession(true)).thenReturn(session);
        when(session.getAttribute("locale")).thenReturn(Locale.getDefault());
        morc = mock(MyOldReservesCommand.class);
        when(morc.execute(rw)).thenCallRealMethod();
        reserves = mock(ReserveDao.class);
        factory = mock(DaoFactory.class);
        when(morc.getFactory()).thenReturn(factory);
        when(factory.getReserveDao()).thenReturn(reserves);
        when(reserves.getAll()).thenReturn(null);
    }

    @Test
    public void goToPage() throws DAOLibraryException {

        String path = morc.execute(rw);
        Assert.assertEquals(path, "/MyOldReserves.jsp");
    }
    
     @Test
    public void reserveAgain() throws DAOLibraryException {
       when(rw.getParameter("oldReserveId")).thenReturn("1");
       BookDao books=mock(BookDao.class);
       when(factory.getBookDao()).thenReturn(books);
     //  Reserve reserve=mock(Reserve.class);
       when(reserves.getByCreteria(anyString())).thenReturn(new Reserve());
       when(books.getByCreteria(anyString())).thenReturn(new Book());
        String path = morc.execute(rw);
        Assert.assertEquals(path, "/CreateReserve.jsp");
    }
}
