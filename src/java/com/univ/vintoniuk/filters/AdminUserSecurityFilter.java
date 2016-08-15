/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univ.vintoniuk.filters;

import com.univ.vintoniuk.dao.DAOLibraryException;
import com.univ.vintoniuk.dao.DaoFactory;
import com.univ.vintoniuk.dao.UserDao;
import com.univ.vintoniuk.model.User;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
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
        String path = ((HttpServletRequest) request).getRequestURI();
        HttpSession hs = ((HttpServletRequest) request).getSession();
        String login = (String) hs.getAttribute("login");
        DaoFactory factory=DaoFactory.getInstance();
        UserDao users=factory.getUserDao();
        if(hs.getAttribute("login") != null){
            String userLogin=(String)hs.getAttribute("login");
            try {
                 user=users.getByCreteria(userLogin);
            } catch (DAOLibraryException ex) {
                Logger.getLogger(AdminUserSecurityFilter.class.getName()).log(Level.SEVERE, null, ex);
            }
       if(user.isIsAdmin()){  
        if ( path.equals("/Library/finding")
                || path.equals("/Library/reserve")
                || path.equals("/Library/myReserves")
                || path.equals("/Library/myOldReserves")
                // || path.equals("/Library/goToFind")
               ) {
             RequestDispatcher rd = ((HttpServletRequest) request).getRequestDispatcher("/error.html");
            rd.forward(request, response);            
        }else {
           chain.doFilter(request, response);
        }
        } else{
            if (path.equals("/Library/returnBook")
                || path.equals("/Library/wievOldReserves")
                || path.equals("/Library/wievReserves")
                || path.equals("/Library/addBook")) {
           RequestDispatcher rd = ((HttpServletRequest) request).getRequestDispatcher("/error.html");
            rd.forward(request, response);            
        }else {
           chain.doFilter(request, response);
        }
       }
    } else{
   chain.doFilter(request, response);
        }
  }

    public void destroy() {
    }

    public void init(FilterConfig filterConfig) {

    }

}
