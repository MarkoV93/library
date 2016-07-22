<%-- 
    Document   : WievReserves
    Created on : 17.07.2016, 20:20:29
    Author     : arsen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reserves for wieving</title>

    </head>


    <body>

       
            <h1>Reserves for wieving</h1>
            ${message}
            <ul>
                <table border="1">
                    <tr> <b>
                        <td>Id</td> 
                        <td>User login</td> 
                        <td>Book title </td> 
                        <td>Answer</td>
                    </b>
                    </tr>
                    <c:forEach var="reserve" items="${requestScope.reserves}">
                        <tr>
                            <td>${reserve.id}</td> 
                            <td>${reserve.userLogin}</td> 
                            <td>${reserve.bookTitle} </td> 
                            <td>${reserve.answer}</td>
                        <form action="wievReserves" method="post">
                             <td><button value=${reserve.id} name="applylId" type="submit">apply</button></p></td>
            <td><button value=${reserve.id} name="censelId" type="submit">censel</button></p></td>
            </form>
            </tr>
                        </tr>
                    </c:forEach>
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
            </ul> 
      

<!--        <div id="right">
             <h1>Give answer</h1>
            <form action="wievReserves" method="post">
                <br>
                Reserve:<select name="idReserve" size="1">                    <   

                    <option selected value="celect">Celect Id of reser for wieving                       

                        <c:forEach var="reserve" items="${requestScope.reserves}">
                        <option value=${reserve.id}>${reserve.id}
                        </c:forEach>                       
                                          <br>      
                    <input type="radio" name="act" value="give in reading room" checked> give in reading room<br>
                    <input type="radio" name="act" value="give to hend"> give to hend<br>
                    <input type="radio" name="act" value="refused"> refused<br>
                    <br>
                    <input type="submit" name="Change reserve" value="Change reserve"/>
            </form>
        </div>-->
    </body>
</html>
