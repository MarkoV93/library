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
import com.univ.vintoniuk.dao.GenreDao;
import com.univ.vintoniuk.dao.ReserveDao;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.univ.vintoniuk.model.Book;
import com.univ.vintoniuk.model.Genre;
import com.univ.vintoniuk.model.Reserve;

/**
 *
 * @author Marko Class for wieving reserves with status "wiev for give to hend"
 * and "wiev for give to reading room" and change them by admin
 * @return ""WievReserves.jsp""
 */
public class WievReservesCommand extends Command {

    @Override
    public String execute(IRequestWrapper request) throws DAOLibraryException {
        String applyId = request.getParameter("applylId");
        HttpSession hs = request.getSession(true);
        DaoFactory factory = this.getFactory();
        BookDao books = factory.getBookDao();
        ReserveDao reserves = factory.getReserveDao();
        ResourceBundle labels = ResourceBundle.getBundle("com.univ.vintoniuk.properties.text", Locale.getDefault());
        if (request.getParameter("censelId") != null) {//if admin press button "cencel"
            String id = request.getParameter("censelId");
            request.setAttribute("message", labels.getString("reserveCenseled"));
            reserves.updateByCreteria("refused", id);
        } else if (applyId != null) { //if admin press button "apply"
             applyId = request.getParameter("applylId");
            Reserve reserve = reserves.getByCreteria(applyId);
            Book book = books.getByCreteria(reserve.getBookTitle());
            if (book.getQty() < 1) {//if quantity of this book is 0, reserve get status "refused"
                reserves.updateByCreteria("refused", applyId);
                request.setAttribute("message", labels.getString("reserveCenseled"));
            } else {////if quantity of this book is not 0
                if (reserve.getAnswer().equals("wiev for give to hend")) {//if status of reserve "wiev for give to hend", then it change on "give to hend"
                    reserves.updateByCreteria("give to hend", applyId);
                    request.setAttribute("message", labels.getString("bookWillBeGivenInHend"));
                } else {//if status of reserve "wiev for give in reading room", then it change on "give in reading room"
                    reserves.updateByCreteria("give in reading room", applyId);
                    request.setAttribute("message", labels.getString("bookWillBeGivenInTheReadingRoom"));

                }
                books.updateByCreteria(Integer.toString(book.getQty() - 1), book.getTitle());//quantity decreases by 1
            }

        }
        List<Reserve> listReserves = reserves.getAllWievingRes();
        request.setAttribute("reserves", listReserves);
        return "WievReserves.jsp";
    }
}
