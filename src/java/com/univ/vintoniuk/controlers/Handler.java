package com.univ.vintoniuk.controlers;

import com.univ.vintoniuk.requestWrapper.RequestWrapper;
import com.univ.vintoniuk.requestWrapper.IRequestWrapper;
import com.univ.vintoniuk.command.ChangeLanguageCommand;
import com.univ.vintoniuk.command.Command;
import com.univ.vintoniuk.command.CommandFactory;
import com.univ.vintoniuk.dao.AbstractDao;
import com.univ.vintoniuk.dao.DAOLibraryException;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import java.util.logging.Level;

import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.LogManager;

/**
 * Servlet which creates relevant command which return path and after this moves
 * on this path
 *
 * @author Marko
 */
public class Handler extends HttpServlet {

    private static final org.apache.log4j.Logger logger = LogManager.getLogger(AbstractDao.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession hs = request.getSession(true);
        IRequestWrapper wrapper = new RequestWrapper(request);//Wrapper for request which owerride only methods which we used
        Command handler = CommandFactory.getInstance().getCommand(request.getServletPath());//crate relevant command
        String path = null;
        try {
            path = handler.execute(wrapper);//execute revelant command
        } catch (DAOLibraryException ex) {
            logger.error("something wrong with exucute method", ex);
           ResourceBundle labels = ResourceBundle.getBundle("com.univ.vintoniuk.properties.text", (Locale) hs.getAttribute("locale"));
           request.setAttribute("errorMessage", ex);
            path = "/error.html";
        }
        if (!request.getServletPath().equals("/changeLanguage")&&!request.getServletPath().equals("/logOut")) {
            hs.setAttribute("req", request.getServletPath());//remember in HttpSession last request for reloading if user change laungage
        }
        RequestDispatcher rd = request.getRequestDispatcher(path);
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);

    }

}
