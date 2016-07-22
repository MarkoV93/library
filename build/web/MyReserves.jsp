<%-- 
    Document   : MyReserves
    Created on : 19 лип. 2016, 18:31:51
    Author     : Marko
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
        <title><fmt:message key="myReserves"/></title>
    </head>
    <body>
         <form action="changeLanguage" method="post">       
      <button type="submit"><fmt:message key="changeLanguage"/></button>
            </form>
        <h1><fmt:message key="myReserves"/></h1>
        ${message}
        
        
           <ul>
               <form action="myReserves" method="post">
                 <table border="1">
                     <tr> <b>
       
                <td><fmt:message key="title"/> </td> 
                <td><fmt:message key="answer"/></td>
                 <td><fmt:message key="date"/></td>
                  <td>   </td>
                     </b>
            </tr>
        <c:forEach var="reserve" items="${requestScope.reserves}">
            <tr>

 
             <td>${reserve.bookTitle} </td> 
                <td>${reserve.answer}</td>
                <td>${reserve.date}</td>
   <td><button value=${reserve.id} name="censelId" type="submit"><fmt:message key="censel"/></button></p></td>
            </tr>
        </c:forEach>
               </form>

        </ul>
          <form action="login" method="post">
                 <button type="submit"><fmt:message key="backToUserPage"/></button>
            </form>
             <form action="finding" method="post">
                 <button type="submit"><fmt:message key="findAndReserve"/></button>
            </form>
       <form action="myOldReserves" method="post">
            <button type="submit"><fmt:message key="myOldReserves"/></button>
            </form>
        <form action="logOut"  method="post">
             <button type="submit"><fmt:message key="logOut"/></button>
            </form>
    </body>
</html>
         </fmt:bundle>