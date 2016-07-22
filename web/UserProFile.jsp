

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<fmt:setLocale value="${sessionScope.locale}" scope="session" />
         <fmt:bundle basename="properties.text">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="userPage"/></title>
    </head>
    <body>
        <form action="changeLanguage" method="post">       
      <button type="submit"><fmt:message key="changeLanguage"/></button>
            </form>
          <h1> ${login} <fmt:message key="userPage"/></h1>
        
        <ul>

<fmt:message key="booksInLibrary"/>
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
             </ul>
             <form action="myOldReserves" method="post">
                 <button type="submit"><fmt:message key="myOldReserves"/></button>
            </form>
             <form action="finding" method="post">
                 <button type="submit"><fmt:message key="findAndReserve"/></button>
            </form>
              <form action="myReserves" method="post">
             <button type="submit"><fmt:message key="myRreserves"/></button>
            </form>
        <form action="logOut"  method="post">
             <button type="submit"><fmt:message key="logOut"/></button>
            </form>

    </body>
</html>
         </fmt:bundle>