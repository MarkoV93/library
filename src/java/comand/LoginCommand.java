/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;


import dao.BookDao;
import dao.DaoFactory;
import dao.ReserveDao;
import dao.UserDao;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import model.Book;
import model.Reserve;
import model.User;

/**
 *
 * @author Marko
 */
public class LoginCommand implements Command {

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      
        String loginUser = request.getParameter("login");
        String passwordUser = request.getParameter("password");
        User u = new User();
        u.setLogin(loginUser);
        u.setPassword(passwordUser);
        HttpSession hs = request.getSession(true);
          Locale locale = (Locale) hs.getAttribute("locale");

        if (locale == null) {
           Locale newLocale = new Locale("en", "US");
                hs.setAttribute("locale", newLocale);
        }
        DaoFactory factory = DaoFactory.getInstance();
        UserDao users = factory.getUserDao();
        
        User user=users.getByCreteria(loginUser);
        DaoFactory factoryb = DaoFactory.getInstance();
        BookDao books = factory.getBookDao();
        List<Book> listBooks = books.getAll();
        if (hs.getAttribute("login") != null) {
            if (users.getByCreteria((String)(hs.getAttribute("login"))).isIsAdmin()) {

                ReserveDao reserves = factory.getReserveDao();
                List<Reserve> listReserves = reserves.getAll();
                  request.setAttribute("user", users.getByCreteria((String)hs.getAttribute("login")));
                request.setAttribute("reserves", listReserves);
                return "/AdminProFile.jsp";
            } else {
                request.setAttribute("books", listBooks);
                 request.setAttribute("user", users.getByCreteria((String)hs.getAttribute("login")));
                return "/UserProFile.jsp";
            }
        } else if (users.verifyUser(u)) {
            if (users.getByCreteria(loginUser).isIsAdmin()) {
                hs.setAttribute("login", loginUser);
                ReserveDao reserves = factory.getReserveDao();
                List<Reserve> listReserves = reserves.getAll();
                request.setAttribute("reserves", listReserves);
                request.setAttribute("login", loginUser);
                 hs.setAttribute("login", loginUser);
                return "/AdminProFile.jsp";
            } else {
                request.setAttribute("books", listBooks);
                 request.setAttribute("login", loginUser);
                  hs.setAttribute("login", loginUser);
                return "/UserProFile.jsp";
            }
        } else if(loginUser==null){
            return "/Login.jsp";
        }else
            {
            
            ResourceBundle labels = ResourceBundle.getBundle("properties.text", (Locale)hs.getAttribute("locale"));
String message=labels.getString("loginWarning");
            request.setAttribute("message", message);
  
            return "/Login.jsp";
        }

    }
}

