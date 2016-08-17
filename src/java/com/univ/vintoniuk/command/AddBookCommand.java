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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.univ.vintoniuk.model.Book;
import com.univ.vintoniuk.model.Genre;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Marko Command for adding books and returning puth on AddBook.jsp
 */
public class AddBookCommand extends Command implements AdminCommand{

    @Override
    public String execute(IRequestWrapper request) throws DAOLibraryException {
        DaoFactory factory =this.getFactory();
        BookDao books = factory.getBookDao();
        HttpSession hs = request.getSession(true);
        ResourceBundle labels = ResourceBundle.getBundle("com.univ.vintoniuk.properties.text", (Locale) hs.getAttribute("locale"));
        if (request.getParameter("add") != null) {//if user press on button "add" on AddBook.jsp
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            String qty = request.getParameter("qty");
            String genre = request.getParameter("genre");
            if (!qty.matches("[0-9]*")||qty.equals("")) {//if user write in field "qty" not number on AddBook.jsp
                request.setAttribute("message", labels.getString("putCurrentIntoQty"));//set message about wrong number and reload page
            } else if (genre.equals("select")) {////if user not chouse genre in field genre on AddBook.jsp
                request.setAttribute("message", labels.getString("selectGenreArea"));
            } else if(title.equals("")){
                 request.setAttribute("message", labels.getString("putCurrentIntotitle"));
            } else if(author.equals("")){
                 request.setAttribute("message", labels.getString("putCurrentIntoauthor"));
            }
             else{
                if (books.getByCreteria(title) != null) {//if DB has row with the same title of  book ,add to qty
                    int newQty = books.getByCreteria(title).getQty() + Integer.parseInt(qty);//add  to qty of book in DB qty from the form
                    books.updateByCreteria(Integer.toString(newQty), title);//Updates values in a database
                } else {//create book and insert in DB
                    Book book = new Book();
                    book.setTitle(title);
                    book.setAuthor(author);
                    book.setQty(Integer.parseInt(qty));
                    book.setGenre(genre);

                    books.create(book);
                }
                request.setAttribute("message", labels.getString("bookWasAdded"));//posting messages about adding book
            }
        }
        GenreDao genres = factory.getGenreDao();
        List<Genre> listGenre = genres.getAll();
        request.setAttribute("genres", listGenre);
        List<Book> listBooks = books.getAll();
        request.setAttribute("books", listBooks);
        return "AddBook.jsp";
    }

}
