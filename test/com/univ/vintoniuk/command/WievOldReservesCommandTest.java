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
import com.univ.vintoniuk.requestWrapper.IRequestWrapper;
import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Marko
 */
public class WievOldReservesCommandTest {
    @Test
    public void wievOldReserves() throws DAOLibraryException{
          IRequestWrapper rw = mock(IRequestWrapper.class);
             Command worc = mock(WievOldReservesCommand.class);
         ReserveDao reserves = mock(ReserveDao.class);
        DaoFactory factory = mock(DaoFactory.class);
        when(worc.getFactory()).thenReturn(factory);
        when(factory.getReserveDao()).thenReturn(reserves);
        when(reserves.getAllWievedRes()).thenReturn(null);
          when(worc.execute(rw)).thenCallRealMethod();
           String path = worc.execute(rw);
        Assert.assertEquals(path, "/WievOldReserves.jsp");
    }
}
