<%-- 
    Document   : MyOldReserves
    Created on : 20 лип. 2016, 14:00:13
    Author     : Marko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="myt" uri="WEB-INF/messageAboutReserve.tld"%>
<!DOCTYPE html>
<fmt:setLocale value="${sessionScope.locale}" scope="session" />
<fmt:bundle basename="com.univ.vintoniuk.properties.text">
    <!DOCTYPE html>
    <html>
        <head>
            <link rel="stylesheet" href="css/bootstrap.min.css" media="screen" type="text/css" />
            <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title><fmt:message key="myOldReserves"/></title>
        </head>
        <body>
            <!--button for change language-->
            <form action="changeLanguage" method="post">       
                <button type="submit" class="btn btn-primary"><fmt:message key="changeLanguage"/></button>
            </form>
            <!--tag for showing active user reserves-->
            <myt:MyRes login="${login}"/>
            <h1><fmt:message key="myOldReserves"/></h1>

            <ul>
                <!--table with old user reserves-->
                <div class="col-sm-11 col-md-11 col-lg-11">
                    <table border="2" class="table table-hover">
                        <tr> <b>

                            <td><fmt:message key="title"/> </td> 
                            <td><fmt:message key="answer"/></td>
                            <td><fmt:message key="date"/></td>
                            <td><fmt:message key="reserveAgein"/></td>
                        </b>
                        </tr>
                        <c:forEach var="reserve" items="${requestScope.reserves}">
                            <tr>

                                <td>${reserve.book.title} </td> 
                                <td>${reserve.answer.answer}</td>
                                <td>${reserve.date}</td>
                            <form action="reserve" method="post">
                                <td><button class="btn btn-primary" value=${reserve.book.title} name="title" type="submit" class="btn btn-default"><fmt:message key="reserveAgein"/></button></p></td>
                            </form>

                            </tr>
                        </c:forEach>


                        </ul>
                            <!--user menu buttons-->
                        <form action="login" method="post">
                            <button type="submit" class="btn btn-primary col-sm-2 col-md-2 col-lg-2"><fmt:message key="backToUserPage"/></button>
                        </form>
                        <form action="myOldReserves" method="post">
                            <button type="submit" class="btn btn-primary col-sm-2 col-md-2 col-lg-2"><fmt:message key="myOldReserves"/></button>
                        </form>
                        <form action="finding" method="post">
                            <button type="submit" class="btn btn-primary col-sm-2 col-md-2 col-lg-2"><fmt:message key="findAndReserve"/></button>
                        </form>
                        <form action="myReserves" method="post">
                            <button type="submit" class="btn btn-primary col-sm-2 col-md-2 col-lg-2"><fmt:message key="myRreserves"/></button>
                        </form>
                        <form action="logOut"  method="post">
                            <button type="submit" class="btn btn-primary col-sm-2 col-md-2 col-lg-2"><fmt:message key="logOut"/></button>
                        </form>
                </div>
        </body>
    </html>
</fmt:bundle>