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
import java.util.List;
import javax.servlet.http.HttpSession;
import com.univ.vintoniuk.model.Genre;
import java.util.Map;
//Wrapper for request which owerride only methods which we used
public interface IRequestWrapper {
    String getParameter(String key);

    HttpSession getSession(Boolean flag);

    public void setAttribute(String genres, Object o);

    public Map<String, Object> getAttributesMap();

    public void setAttributesMap(Map<String,Object> attributes);
}