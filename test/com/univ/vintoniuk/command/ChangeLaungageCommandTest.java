/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univ.vintoniuk.command;

import com.univ.vintoniuk.dao.DAOLibraryException;
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
public class ChangeLaungageCommandTest {
    @Test
    public void exucute() throws DAOLibraryException{
       IRequestWrapper   rw = mock(IRequestWrapper.class);
       HttpSession  session = mock(HttpSession.class);
        when(rw.getSession(true)).thenReturn(session);
        when(session.getAttribute("req")).thenReturn("/logOut");
        ChangeLanguageCommand clc=new ChangeLanguageCommand();

        String path=clc.execute(rw);
          Assert.assertEquals(path, "/Login.jsp");
         
    }
}
