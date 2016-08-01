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
        String path = ((HttpServletRequest) request).getRequestURI();
        HttpSession hs = ((HttpServletRequest) request).getSession();
        String login = (String) hs.getAttribute("login");
        if (path.equals("/Autorithation/RegistrationJSP.jsp")
                || path.equals("/Autorithation/Login.jsp")
                || path.equals("/Autorithation/css/style.css")
                || path.equals("/Autorithation/css/bootstrap.min.css")
                || path.equals("/Autorithation/index.html")
                || path.equals("/Autorithation/")
                || path.equals("/Autorithation/login")
                || path.equals("/Autorithation/changeLanguage")
                || path.equals("/Autorithation/registration") || hs.getAttribute("login") != null) {
            chain.doFilter(request, response);
        } else {
            RequestDispatcher rd = ((HttpServletRequest) request).getRequestDispatcher("/error.html");
            rd.forward(request, response);
        }

    }

    public void destroy() {
    }

    public void init(FilterConfig filterConfig) {

    }

}
