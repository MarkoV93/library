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
import com.univ.vintoniuk.dao.UserDao;
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
 * @author arsen
 * Class for creating new reserve by user
 */
public class ReserveCommand extends Command {

    @Override
    public String execute(IRequestWrapper request) throws DAOLibraryException {
        HttpSession hs = request.getSession(true);
        String titleBookForRes = (String) hs.getAttribute("titleBookforReserve");//title of book witch set in session ,if it was find in Finding.jsp 
        ResourceBundle labels = ResourceBundle.getBundle("com.univ.vintoniuk.properties.text", Locale.getDefault());
        DaoFactory factory = this.getFactory();
        BookDao books = factory.getBookDao();
        String userLogin = (String) hs.getAttribute("login");
        String action = request.getParameter("act");
        String titleBook = request.getParameter("title");
        if (books.getByCreteria(titleBook) != null) {//if there is book with the same title in DB
            Book book = books.getByCreteria(titleBook);
            hs.setAttribute("titleBookforReserve", titleBook);//set in session title of book for reserve
            request.setAttribute("book", book);
            return "/CreateReserve.jsp";
        } else if (books.getByCreteria(titleBook) == null && titleBookForRes == null) {//if in DB  is no one book with  title, witch wrote user in fild "title" in "Finding.jsp"
            request.setAttribute("message", labels.getString("thereIsNoOneBook"));//sent message to user and reload page"Finding.jsp"
            GenreDao genres = factory.getGenreDao();
            List<Genre> listGenres = genres.getAll();
            request.setAttribute("genres", listGenres);
            List<String> listAuthors = books.getAuthorsOfBooks();
            request.setAttribute("authors", listAuthors);
            return "/Finding.jsp";
        } else if (titleBookForRes != null && action == null) {//if user did not choose variant where he want to take book ,then print message about this and overload page
            Book book = books.getByCreteria(titleBookForRes);
            request.setAttribute("message", labels.getString("chouseAction"));
            request.setAttribute("book", book);
            return "/CreateReserve.jsp";
        } else {//if all right ,create reserve , print message and move to the "UserProFile.jsp"
            Reserve reserve = new Reserve();
            reserve.setBookTitle(titleBookForRes);
            reserve.setAnswer(action);
            reserve.setUserLogin(userLogin);
            ReserveDao reserves = factory.getReserveDao();
            reserves.create(reserve);
            List<Book> bookList = books.getAll();
            request.setAttribute("books", bookList);
            request.setAttribute("message", labels.getString("bookReserved"));
            return "/UserProFile.jsp";
        }
    }

}
