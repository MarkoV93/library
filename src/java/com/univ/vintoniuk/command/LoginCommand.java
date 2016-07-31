/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univ.vintoniuk.command;

import com.univ.vintoniuk.requestWrapper.IRequestWrapper;
import com.univ.vintoniuk.dao.BookDao;
import com.univ.vintoniuk.dao.DAOLibraryException;
import com.univ.vintoniuk.dao.DaoFactory;
import com.univ.vintoniuk.dao.ReserveDao;
import com.univ.vintoniuk.dao.UserDao;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import com.univ.vintoniuk.model.Book;
import com.univ.vintoniuk.model.Reserve;
import com.univ.vintoniuk.model.User;

/**
 *
 * @author Marko Class for Loging
 * returnes:Login.gsp,AdminProFile.jsp,UserProFile.jsp,Registration.jsp
 */
public class LoginCommand extends Command {

    public String execute(IRequestWrapper request) throws DAOLibraryException {
        String loginUser = request.getParameter("login");
        String passwordUser = request.getParameter("password");
        HttpSession hs = request.getSession(true);
        Locale locale = (Locale) hs.getAttribute("locale");
        if (locale == null) {
            Locale.setDefault(new Locale("en", "US"));
            Locale newLocale = new Locale("en", "US");
            hs.setAttribute("locale", newLocale);
        }
        DaoFactory factory = this.getFactory();
        UserDao users = factory.getUserDao();
        DaoFactory factoryb = DaoFactory.getInstance();
        BookDao books = factory.getBookDao();
        List<Book> listBooks = books.getAll();
        if (hs.getAttribute("login") != null) {//if there is active session with field "login"
            if (users.getByCreteria((String) (hs.getAttribute("login"))).isIsAdmin()) {//if user whith this login is admin
                ReserveDao reserves = factory.getReserveDao();
                List<Reserve> listReserves = reserves.getAll();
                request.setAttribute("reserves", listReserves);
                return "/AdminProFile.jsp";
            } else {
                request.setAttribute("books", listBooks);////if user whith this login is not admin
                return "/UserProFile.jsp";
            }
        } else if (users.verifyUser(loginUser, passwordUser)) { //if there is not active session
            if (users.getByCreteria(loginUser).isIsAdmin()) {//if usser with this login is admin
                hs.setAttribute("login", loginUser);
                ReserveDao reserves = factory.getReserveDao();
                List<Reserve> listReserves = reserves.getAll();
                request.setAttribute("reserves", listReserves);
                return "/AdminProFile.jsp";
            } else {//if usser with this login is not admin
                request.setAttribute("books", listBooks);
                hs.setAttribute("login", loginUser);
                return "/UserProFile.jsp";
            }
        } else if (loginUser == null) {//reload page if user dous not write login
            return "/Login.jsp";
        } else {//if there is not user with login-password pair in DB or active session
            ResourceBundle labels = ResourceBundle.getBundle("com.univ.vintoniuk.properties.text", (Locale) hs.getAttribute("locale"));
            String message = labels.getString("loginWarning");
            request.setAttribute("message", message);
            return "/Login.jsp";
        }

    }
}
