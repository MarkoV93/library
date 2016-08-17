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
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.univ.vintoniuk.model.Book;
import com.univ.vintoniuk.model.Reserve;

/**
 * 
 * @author Marko
 * class for returning books by admin
 * @return "/ReturnBook.jsp"
 */
public class ReturnBookCommand extends Command implements AdminCommand{
    @Override
    public String execute(IRequestWrapper request) throws DAOLibraryException {
        DaoFactory factory = this.getFactory();
        ReserveDao reserves = factory.getReserveDao();
        BookDao books = factory.getBookDao();
        if (request.getParameter("returnId") != null) {//if admin press button "return"
            ResourceBundle labels = ResourceBundle.getBundle("com.univ.vintoniuk.properties.text", Locale.getDefault());
            String id = request.getParameter("returnId");//get id of reserve
            Reserve reserve = reserves.getByCreteria(id);
            reserves.updateByCreteria("returned", id);//change reserve status on "returned"
            request.setAttribute("message", labels.getString("bookWasReturned"));
            Book book = reserve.getBook();
            books.updateByCreteria(Integer.toString(book.getQty() + 1), book.getTitle());//add to 1 to quantity of this in DB
        }
        List<Reserve> listReserves = reserves.getAllReservsForReturn();
        request.setAttribute("reserves", listReserves);
        return "/ReturnBook.jsp";
    }

}
