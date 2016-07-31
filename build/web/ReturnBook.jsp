<%-- 
    Document   : ReturnBook
    Created on : 20 лип. 2016, 10:23:19
    Author     : Marko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<fmt:setLocale value="${sessionScope.locale}" scope="session" />
<fmt:bundle basename="com.univ.vintoniuk.properties.text">
    <html>
        <head>
            <link rel="stylesheet" href="css/bootstrap.min.css" media="screen" type="text/css" />
            <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>JSP Page</title>
        </head>
        <body>
            <form action="changeLanguage" method="post">       
                <button type="submit" class="btn btn-primary"><fmt:message key="changeLanguage"/></button>
            </form>
            <h1><fmt:message key="activeReserves"/></h1>
            ${message}
            <ul>
                <div class="col-sm-11 col-md-11 col-lg-11">
                    <table border="2"  class="table  table-hover  ">
                        <tr> <b>
                            <td><fmt:message key="userLogin"/></td> 
                            <td><fmt:message key="bookTitle"/></td> 
                            <td><fmt:message key="answer"/></td>
                            <td><fmt:message key="date"/></td> 
                            <td></td>
                        </b>
                        </tr>

                        <c:forEach var="reserve" items="${requestScope.reserves}">
                            <tr>
                                <td>${reserve.userLogin}</td> 
                                <td>${reserve.bookTitle} </td> 
                                <td>${reserve.answer}</td>
                                <td>${reserve.date}</td>
                            <form action="returnBook" method="post">
                                <td><button value=${reserve.id} name="returnId" type="submit" class="btn btn-primary"><fmt:message key="return"/></button></p></td>
                            </form> 
                            </tr>
                        </c:forEach>


                        </ul>
                        <form action="login" method="post">
                            <button type="submit" class="btn btn-primary col-sm-2 col-md-2 col-lg-2"><fmt:message key="backToUserPage"/></button>
                        </form>
                        <form action="wievReserves" method="post">
                            <button type="submit" class="btn btn-primary col-sm-2 col-md-2 col-lg-2"><fmt:message key="wievReserves"/></button>
                        </form>
                        <form action="addBook" method="post">
                            <button type="submit" class="btn btn-primary col-sm-2 col-md-2 col-lg-2"><fmt:message key="addBook"/></button>
                        </form>
                        <form action="wievOldReserves" method="post">
                            <button type="submit" class="btn btn-primary col-sm-2 col-md-2 col-lg-2"><fmt:message key="wievOldReserves"/></button>
                        </form>
                        <form action="returnBook" method="post">
                            <button type="submit" class="btn btn-primary col-sm-2 col-md-2 col-lg-2"><fmt:message key="returnBook"/></button>
                        </form>
                        <form action="logOut"  method="post">
                            <button type="submit" class="btn btn-primary col-sm-2 col-md-2 col-lg-2"><fmt:message key="logOut"/></button>
                        </form>
                        </body>
                        </html>
                    </fmt:bundle>