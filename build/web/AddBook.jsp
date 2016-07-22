<%-- 
    Document   : AddBook
    Created on : 18 лип. 2016, 19:35:47
    Author     : Marko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

        <head>
            <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>JSP Page</title>
        </head>

        <body>


            <h1>Add book</h1>
 <div id="right">
   
            <br> ${message}
            <br>
          
            <fieldset>
                <form action="addBook" method="post">
                    Title:<input type="text" name="title"/>
                    <br>
                    Author:<input type="text" name="author"/>
                    <br>
                    Qty:<input type="text" name="qty"/>
                    <br>
                    Genre:<select name="genre" size="1">
                        <option selected value="celect">Celect Genre of books
                            <c:forEach var="genre" items="${requestScope.genres}">
                            <option value=${genre.genre}>${genre.genre}
                            </c:forEach>
                    </select>
                    <br>
                    <input type="submit" name="add" value="add"/>
                     </form>

                    </div>
            <div id="left">
                <ul>
                    <table border="1">
                        <tr > <b>
                            <td>     title </td> 
                            <td>     author</td> 
                            <td>     genre </td> 
                            <td>     qty </td>
                        </b>
                        </tr>
                        <c:forEach var="book" items="${requestScope.books}">
                            <tr>
                                <td>     ${book.title} </td> 
                                <td>     ${book.author}</td> 
                                <td>     ${book.genre} </td> 
                                <td>     ${book.qty} </td>
                            </tr>
                        </c:forEach>
                     
                 </ul>
           <form action="login" method="post">
             <input type="submit" name="OK" value="To home page"/>
            </form>
           <form action="wievReserves" method="post">
             <input type="submit" name="OK" value="Wiev Reserves"/>
          </form>
              <form action="addBook" method="post">
             <input type="submit" name="goToAddBook" value="Add book to library"/>
          </form>
             <form action="wievOldReserves" method="post">
              <input type="submit" name="wievOldReserves" value="wiev old reserves"/>
              </form>
        <form action="returnBook" method="post">
              <input type="submit" name="Return book" value="Return book"/>
              </form>
 </div>

</body>

</html>
