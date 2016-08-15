package com.univ.vintoniuk.command;

import com.univ.vintoniuk.command.RegisterCommand;
import com.univ.vintoniuk.dao.DAOLibraryException;
import com.univ.vintoniuk.requestWrapper.IRequestWrapper;
import com.univ.vintoniuk.requestWrapper.RequestWrapper;
import com.univ.vintoniuk.dao.DaoFactory;
import com.univ.vintoniuk.dao.UserDao;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.univ.vintoniuk.model.User;

import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Matchers;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.legacy.PowerMockRunner;

/**
 *
 * @author Marko
 */
//@RunWith(PowerMockRunner.class)
//@PrepareForTest( DaoFactory.class )
public class RegisterCommandTest {

    IRequestWrapper rw;
    UserDao users;
    RegisterCommand rc;
    DaoFactory factory;

    @Before
    public void getParameters() throws DAOLibraryException {
        rw = mock(IRequestWrapper.class);
        when(rw.getParameter("firstName")).thenReturn("vasya");
        when(rw.getParameter("lastName")).thenReturn("vasya");
        when(rw.getParameter("login")).thenReturn("vasya");
        when(rw.getParameter("password")).thenReturn("vasya");
        rc = mock(RegisterCommand.class);
        users = mock(UserDao.class);
        factory = mock(DaoFactory.class);
        when(rc.getFactory()).thenReturn(factory);
        when(rc.execute(rw)).thenCallRealMethod();
        when(factory.getUserDao()).thenReturn(users);
    }

    @Test
    public void RegisterCompleted() throws DAOLibraryException {
        when(rw.getParameter("Registration")).thenReturn("ok");
        when(users.getByCreteria(anyString())).thenReturn(null);
        String path = rc.execute(rw);
        Assert.assertEquals(path, "/Login.jsp");
    }

    @Test
    public void RegisterFaild() throws Exception {
        when(users.getByCreteria(Matchers.anyString())).thenReturn(new User());
        String path = rc.execute(rw);
        Assert.assertEquals(path, "/RegistrationJSP.jsp");
    }
}
