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
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * @author Marko
 */
public class CharacterEncoding implements Filter {
    

  



    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
  request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
         chain.doFilter(request, response);
    }


    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {        
   
    }

    
}
