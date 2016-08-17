/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univ.vintoniuk.filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Marko
 */
public class BadRequestFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        String path;
        HttpSession hs = ((HttpServletRequest) request).getSession();
        path = ((HttpServletRequest) request).getRequestURI();
        String login = (String) hs.getAttribute("login");
        if (path.equals("/Library/RegistrationJSP.jsp")
                || path.equals("/Library/Login.jsp")
                || path.equals("/Library/css/style.css")
                || path.equals("/Library/css/bootstrap.min.css")
                || path.equals("/Library/index.html")
                || path.equals("/Library/")
                || path.equals("/Library/login")
                || path.equals("/Library/changeLanguage")
                || path.equals("/Library/logOut")
                || path.equals("/Library/registration") || hs.getAttribute("login") != null) {
            chain.doFilter(request, response);
        } else {
             ResourceBundle labels = ResourceBundle.getBundle("com.univ.vintoniuk.properties.text", (Locale) hs.getAttribute("locale"));
            RequestDispatcher rd = ((HttpServletRequest) request).getRequestDispatcher("/error.jsp");
            request.setAttribute("errorMessage", labels.getString("youCanNotGoTo"));
            rd.forward(request, response);
        }

    }

    public void destroy() {
    }

    public void init(FilterConfig filterConfig) {

    }

}
