

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<fmt:setLocale value="${sessionScope.locale}" scope="session" />
         <fmt:bundle basename="properties.text">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
         <form action="changeLanguage" method="post">       
      <button type="submit"><fmt:message key="changeLanguage"/></button>
            </form>
            <h1>${sessionScope.login}<fmt:message key="adminPage"/></h1>
        <ul>
                 <table border="1">
                     <tr> <b>
                <td><fmt:message key="userLogin"/></td> 
                <td><fmt:message key="bookTitle"/> </td> 
                <td><fmt:message key="answer"/></td>
                 <td><fmt:message key="date"/></td>
                     </b>
            </tr>
        <c:forEach var="reserve" items="${requestScope.reserves}">
            <tr>
                <td>${reserve.userLogin}</td> 
                <td>${reserve.bookTitle} </td> 
                <td>${reserve.answer}</td>
                <td>${reserve.date}</td>
            </tr>
        </c:forEach>
        </ul>

                       <form action="wievReserves" method="post">
                 <button type="submit"><fmt:message key="wievReserves"/></button>
            </form>
             <form action="addBook" method="post">
                 <button type="submit"><fmt:message key="addBook"/></button>
            </form>
              <form action="wievOldReserves" method="post">
             <button type="submit"><fmt:message key="wievOldReserves"/></button>
            </form>
             <form action="returnBook" method="post">
             <button type="submit"><fmt:message key="returnBook"/></button>
            </form>
        <form action="logOut"  method="post">
             <button type="submit"><fmt:message key="logOut"/></button>
            </form>
       
    </body>
</html>
         </fmt:bundle>