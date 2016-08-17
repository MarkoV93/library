/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univ.vintoniuk.filters;

import com.univ.vintoniuk.command.AdminCommand;
import com.univ.vintoniuk.command.Command;
import com.univ.vintoniuk.command.CommandFactory;
import com.univ.vintoniuk.command.UserCommand;
import com.univ.vintoniuk.dao.DAOLibraryException;
import com.univ.vintoniuk.dao.DaoFactory;
import com.univ.vintoniuk.dao.UserDao;
import com.univ.vintoniuk.model.User;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Marko
 */
public class AdminUserSecurityFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        User user = null;
        String path = ((HttpServletRequest) request).getServletPath();
        HttpSession hs = ((HttpServletRequest) request).getSession();
        DaoFactory factory = DaoFactory.getInstance();
        UserDao users = factory.getUserDao();
        if (hs.getAttribute("login") != null) {
            Command handler = CommandFactory.getInstance().getCommand((String) hs.getAttribute("req"));
            String userLogin = (String) hs.getAttribute("login");
            try {
                user = users.getByCreteria(userLogin);
            } catch (DAOLibraryException ex) {
                Logger.getLogger(AdminUserSecurityFilter.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (user.isIsAdmin()) {
                if (handler instanceof UserCommand) {
                    ResourceBundle labels = ResourceBundle.getBundle("com.univ.vintoniuk.properties.text", (Locale) hs.getAttribute("locale"));
                    request.setAttribute("errorMessage", labels.getString("youDoNotHavePermission"));
                    RequestDispatcher rd = ((HttpServletRequest) request).getRequestDispatcher("/error.jsp");
                    hs.setAttribute("req", "/login");
                    rd.forward(request, response);
                } else {
                    chain.doFilter(request, response);
                }
            } else if (handler instanceof AdminCommand) {
                ResourceBundle labels = ResourceBundle.getBundle("com.univ.vintoniuk.properties.text", (Locale) hs.getAttribute("locale"));
                request.setAttribute("errorMessage", labels.getString("youDoNotHavePermission"));
                RequestDispatcher rd = ((HttpServletRequest) request).getRequestDispatcher("/error.jsp");
                hs.setAttribute("req", "/login");
                rd.forward(request, response);
            } else {
                chain.doFilter(request, response);
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    public void destroy() {
    }

    public void init(FilterConfig filterConfig) {

    }

}
