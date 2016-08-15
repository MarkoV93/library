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
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
//Wrapper for request which owerride only methods which we used
public class RequestWrapper implements IRequestWrapper {
    private HttpServletRequest request;
 Map<String, Object> attributes=new HashMap<String, Object>();

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
  //     request.setAttribute(key, o);
       attributes.put(key, o);
    }

    @Override
    public Map<String, Object> getAttributesMap() {
//        Map<String, Object> attributes=new HashMap<String, Object>();
//        Enumeration<String> att=request.getAttributeNames();
//        while(att.hasMoreElements()){
//            String attribute=att.nextElement();
//            attributes.put(attribute, request.getAttribute(attribute));
//        }
        return attributes;
    }

    @Override
    public void setAttributesMap(Map<String,Object> attributes) {
        if(attributes!=null){
       for(Map.Entry<String,Object> entry :attributes.entrySet()){
        request.setAttribute(entry.getKey(), entry.getValue());
    }
       this.attributes.putAll(attributes);
    }
    }
}