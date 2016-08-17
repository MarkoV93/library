package com.univ.vintoniuk.controlers;

import com.univ.vintoniuk.requestWrapper.RequestWrapper;
import com.univ.vintoniuk.requestWrapper.IRequestWrapper;
import com.univ.vintoniuk.command.ChangeLanguageCommand;
import com.univ.vintoniuk.command.Command;
import com.univ.vintoniuk.command.CommandFactory;
import com.univ.vintoniuk.dao.AbstractDao;
import com.univ.vintoniuk.dao.DAOLibraryException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
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
 * Servlet which creates relevant command which return path in doPost method and
 * after that moves on this path in doGet method
 *
 * @author Marko
 */
public class Handler extends HttpServlet {

    private static final org.apache.log4j.Logger logger = LogManager.getLogger(AbstractDao.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
//method for returning to user page and overloading pages 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession hs = request.getSession(true);
        if (!request.getServletPath().equals((String) hs.getAttribute("req"))) {//if user wrote into address field address of another action
            doPost(request, response);
        } else {
            String path = (String) hs.getAttribute("path");//got path from session after excecuting of command in doPost method
            Map<String, Object> atributes = (Map<String, Object>) hs.getAttribute("atrributes");//got all atributes of reqest in doPost method
            IRequestWrapper wrapper = new RequestWrapper(request);
            wrapper.setAttributesMap(atributes);//overwrite all attributes in request of doGet method
            RequestDispatcher rd = request.getRequestDispatcher(path);
            rd.forward(request, response);
        }
    }
//for processing requests of the user ,seting atributes of request into  
//HttpSession and  redirection on the  doGet method

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession hs = request.getSession(true);
        IRequestWrapper wrapper = new RequestWrapper(request);//Wrapper for request which owerride only methods which we used
        Command handler = CommandFactory.getInstance().getCommand(request.getServletPath());//crate relevant command
        String path = null;
        try {
            path = handler.execute(wrapper);//execute revelant command
        } catch (DAOLibraryException ex) {
            logger.error("something wrong with exucute method", ex);
            path = "/error.jsp";
        }
        if (!request.getServletPath().equals("/changeLanguage") && !request.getServletPath().equals("/logOut")) {
            hs.setAttribute("req", request.getServletPath());//remember in HttpSession last request for change languag command
        }
        hs.setAttribute("path", path);
        hs.setAttribute("atrributes", wrapper.getAttributesMap());
        response.sendRedirect("/Library" + hs.getAttribute("req"));//redirect in this servlet doGet method

    }

}
