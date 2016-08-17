/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univ.vintoniuk.command;

import com.univ.vintoniuk.requestWrapper.IRequestWrapper;
import com.univ.vintoniuk.dao.DAOLibraryException;
import com.univ.vintoniuk.dao.DaoFactory;
import com.univ.vintoniuk.dao.ReserveDao;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.univ.vintoniuk.model.Reserve;

/**
 *
 * @author Marko
 * Class for wieving reserves with status "refused" and "returned"
 * @return "/WievOldReserves.jsp"
 */
public class WievOldReservesCommand extends Command implements AdminCommand{

    @Override
    public String execute(IRequestWrapper request) throws DAOLibraryException {
        DaoFactory factory = this.getFactory();
        ReserveDao reserves = factory.getReserveDao();
        List<Reserve> listReserves = reserves.getAllWievedRes();//get list of reserves with answer "returned" and "refused"
        request.setAttribute("reserves", listReserves);
        return "/WievOldReserves.jsp";
    }

}
