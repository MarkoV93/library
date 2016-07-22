/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import dao.BookDao;
import dao.DaoFactory;
import dao.GenreDao;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Book;
import model.Genre;

public class FindingCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String titleBook = request.getParameter("title");
        String genreBook = request.getParameter("genre");
        String authorBook = request.getParameter("author");


        DaoFactory factory = DaoFactory.getInstance();
        BookDao bookDao = factory.getBookDao();
        HttpSession hs = request.getSession(true);
       if(request.getParameter("Reserve")!=null) {
            if (bookDao.getByCreteria(titleBook) != null) {
                
                Book book = bookDao.getByCreteria(titleBook);
                hs.setAttribute("titleBookforReserve", titleBook);
                request.setAttribute("book", book);
                return "/CreateReserve.jsp";
            } else {
                 ResourceBundle labels = ResourceBundle.getBundle("properties.text", (Locale)hs.getAttribute("locale"));  
                   request.setAttribute("message", labels.getString("thereIsNoOneBook"));

                   GenreDao genres=factory.getGenreDao();
              List<Genre> listGenres=genres.getAll();
              request.setAttribute("genres", listGenres);
               List<String> listAuthors=bookDao.getAuthorsOfBooks();
              request.setAttribute("authors", listAuthors);
                return "/Finding.jsp";
            }
        } else  if(request.getParameter("Find by genre")!=null) {
            List<Book> listBooks = bookDao.getByGenre(genreBook);
            request.setAttribute("books", listBooks);
            request.setAttribute("genre", genreBook);
            return "/BooksByGenre.jsp";
        } else  if(request.getParameter("Find by author")!=null) {
            List<Book> listBooks = bookDao.getByAuthor(authorBook);
            request.setAttribute("books", listBooks);
            request.setAttribute("author", authorBook);
            return "/BooksByAuthor.jsp";
        }else {
            BookDao books=factory.getBookDao();
            List<String> listAuthors=books.getAuthorsOfBooks();
              request.setAttribute("authors", listAuthors);
              GenreDao genres=factory.getGenreDao();
              List<Genre> listGenres=genres.getAll();
              request.setAttribute("genres", listGenres);
              
              return "/Finding.jsp";
        }
    }

}
