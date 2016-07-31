/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univ.vintoniuk.filters;

import com.univ.vintoniuk.dao.DaoFactory;
import com.univ.vintoniuk.dao.UserDao;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Marko
 */
public class RequestWithOutLoginSessionFilter implements Filter {
    
  public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
      String path=((HttpServletRequest) request).getRequestURI();
 HttpSession hs = ((HttpServletRequest) request).getSession(true);
 DaoFactory factory = DaoFactory.getInstance();
      UserDao users=factory.getUserDao();
      try {
          if(path.equals("/Autorithation/Login.jsp")||path.equals("/Autorithation/UserProFile.jsp")||(hs.getAttribute("login")!=null)&&(hs.getAttribute("password")!=null&&users.verifyUser((String)hs.getAttribute("login"), (String)hs.getAttribute("password")))){
             chain.doFilter(request, response);
          }
                else {
            RequestDispatcher rd = request.getRequestDispatcher("/error.html");
        rd.forward(request, response);
        }
      } catch (SQLException ex) {
          Logger.getLogger(RequestWithOutLoginSessionFilter.class.getName()).log(Level.SEVERE, null, ex);
      }
    }


    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {        
   
    }

    
}
