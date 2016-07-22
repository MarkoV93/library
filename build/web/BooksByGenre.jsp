<%-- 
    Document   : BooksByGenre
    Created on : 17.07.2016, 19:11:01
    Author     : arsen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<fmt:setLocale value="${sessionScope.locale}" scope="session" />
         <fmt:bundle basename="properties.text">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>${genre}  </h1>
           <form action="changeLanguage" method="post">       
      <button type="submit"><fmt:message key="changeLanguage"/></button>
            </form>
        <ul>


   <table border="1">
        <tr > <b>
            <td> <fmt:message key="title"/> </td> 
            <td> <fmt:message key="author"/></td> 
            <td> <fmt:message key="genre"/> </td> 
            <td>  <fmt:message key="qty"/> </td>
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
               <form action="finding" method="post">
              <button type="submit"><fmt:message key="reserve"/></button>
            </form>
              <form action="login" method="post">
             <button type="submit"><fmt:message key="backToUserPage"/></button>
            </form>
        </ul>
    </body>
</html>
         </fmt:bundle>