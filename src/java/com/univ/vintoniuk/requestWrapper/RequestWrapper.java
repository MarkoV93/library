/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univ.vintoniuk.requestWrapper;

/**
 *
 * @author Marko
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
//Wrapper for request which owerride only methods which we used
public class RequestWrapper implements IRequestWrapper {
    private HttpServletRequest request;

    public RequestWrapper(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String getParameter(String key) {
        return request.getParameter(key);
    }

    @Override
    public HttpSession getSession(Boolean flag) {
        return request.getSession(flag);
    }

    @Override
    public void setAttribute(String key, Object o) {
       request.setAttribute(key, o);
    }

}