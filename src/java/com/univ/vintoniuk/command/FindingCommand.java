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
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.univ.vintoniuk.model.Book;
import com.univ.vintoniuk.model.Genre;

public class FindingCommand extends Command {

    /**
     *
     * @author Marko Command for finding books by genre or by author or by title
     * return puth on
     * Finding.jsp,BooksByAuthor.jsp,BooksByGenre.jsp
     */
    @Override
    public String execute(IRequestWrapper request) throws DAOLibraryException {
        String titleBook = request.getParameter("title");
        String genreBook = request.getParameter("genre");
        String authorBook = request.getParameter("author");
        DaoFactory factory = this.getFactory();
        BookDao books = factory.getBookDao();
        HttpSession hs = request.getSession(true);
        if (request.getParameter("Find by genre") != null) {//if user press button "Find by genre"
            List<Book> listBooks = books.getByGenre(genreBook);
            request.setAttribute("books", listBooks);
            request.setAttribute("genre", genreBook);
            return "/BooksByGenre.jsp";
        } else if (request.getParameter("Find by author") != null) {//if user press button "Find by author"
            List<Book> listBooks = books.getByAuthor(authorBook);
            request.setAttribute("books", listBooks);
            request.setAttribute("author", authorBook);
            return "/BooksByAuthor.jsp";
        } else {
            List<String> listAuthors = books.getAuthorsOfBooks();//if user press button  "find and reserve"
            request.setAttribute("authors", listAuthors);
            GenreDao genres = factory.getGenreDao();
            List<Genre> listGenres = genres.getAll();
            request.setAttribute("genres", listGenres);
            return "/Finding.jsp";
        }
    }

}
