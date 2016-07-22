<%-- 
    Document   : ReturnBook
    Created on : 20 лип. 2016, 10:23:19
    Author     : Marko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Reserves for return!</h1>
        ${message}
                <ul>
                     
                 <table border="1">
                     <tr> <b>
                <td>User login</td> 
                <td>Book title </td> 
                <td>Answer</td>
                 <td>Date</td>
                  <td>Return</td>
                     </b>
            </tr>
          
        <c:forEach var="reserve" items="${requestScope.reserves}">
            <tr>
                <td>${reserve.userLogin}</td> 
                <td>${reserve.bookTitle} </td> 
                <td>${reserve.answer}</td>
                <td>${reserve.date}</td>
                 <form action="returnBook" method="post">
                <td><button value=${reserve.id} name="returnId" type="submit">return</button></p></td>
                </form> 
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
                 <form action="logOut" method="post">
             <input type="submit" name="OK" value="Log Out"/>
            </form>
    </body>
     

</html>
