/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univ.vintoniuk.command;

import com.univ.vintoniuk.requestWrapper.IRequestWrapper;
import com.univ.vintoniuk.dao.BookDao;
import com.univ.vintoniuk.dao.DAOLibraryException;
import com.univ.vintoniuk.dao.DaoFactory;
import com.univ.vintoniuk.dao.ReserveDao;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.univ.vintoniuk.model.Book;
import com.univ.vintoniuk.model.Reserve;

/**
 *
 * Class for wieving old reserves of user 
 */
public class MyOldReservesCommand extends Command implements UserCommand{

    @Override
    public String execute(IRequestWrapper request) throws DAOLibraryException {
        DaoFactory factory = this.getFactory();
        ReserveDao reserves = factory.getReserveDao();
        HttpSession hs = request.getSession(true);
        List<Reserve> listReserves = reserves.getAllOldResByLogin((String) hs.getAttribute("login"));//getting all reserves of user with login-"login" which returned or refused
        request.setAttribute("reserves", listReserves);
        return "/MyOldReserves.jsp";
    }
}
