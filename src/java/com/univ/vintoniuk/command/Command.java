/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univ.vintoniuk.command;

import com.univ.vintoniuk.requestWrapper.IRequestWrapper;
import com.univ.vintoniuk.dao.DAOLibraryException;
import com.univ.vintoniuk.dao.DaoFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Marko
 */

public abstract class Command {
DaoFactory factory;
public DaoFactory getFactory(){
    if(factory==null){
    factory=DaoFactory.getInstance();
    }
    return factory;
} 

    public abstract String execute(IRequestWrapper request) throws DAOLibraryException;

}
