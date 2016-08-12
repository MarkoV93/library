<%-- 
    Document   : WievReserves
    Created on : 17.07.2016, 20:20:29
    Author     : arsen
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
            <title><fmt:message key="newReserves"/></title>

        </head>


        <body>
            <form action="changeLanguage" method="post">       
                <button type="submit" class="btn btn-primary"><fmt:message key="changeLanguage"/></button>
            </form>

            <h1><fmt:message key="newReserves"/></h1>
            ${message}
            <ul>
                <div class="col-sm-11 col-md-11 col-lg-11">
                    <table border="2"  class="table  table-hover  ">
                        <tr> <b>
                            <td><fmt:message key="number"/></td> 
                            <td><fmt:message key="userLogin"/></td> 
                            <td><fmt:message key="bookTitle"/></td> 
                            <td><fmt:message key="answer"/></td>
                             <td><fmt:message key="date"/></td>
                        </b>
                        </tr>
                        <c:forEach var="reserve" items="${requestScope.reserves}">
                            <tr>
                                <td>${reserve.id}</td> 
                                <td>${reserve.user.login}</td> 
                                <td>${reserve.book.title} </td> 
                                <td>${reserve.answer.answer}</td>
                                <td>${reserve.date}</td>
                            <form action="wievReserves" method="post">
                                <td><button value=${reserve.id} name="applylId" type="submit" class="btn btn-primary"><fmt:message key="apply"/></button></p></td>
                                <td><button value=${reserve.id} name="censelId" type="submit" class="btn btn-primary"><fmt:message key="cencel"/></button></p></td>
                            </form>
                            </tr>
                            </tr>
                        </c:forEach>
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
                        </ul> 



                        </body>
                        </html>
                    </fmt:bundle>