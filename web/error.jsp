<%-- 
    Document   : error
    Created on : 17 серп. 2016, 17:43:37
    Author     : Marko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<fmt:setLocale value="${sessionScope.locale}" scope="session" />
<fmt:bundle basename="com.univ.vintoniuk.properties.text">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
        <title>Error</title>
    </head>
    <body>
         <h1><fmt:message key="error"/></h1>
       ${requestScope.errorMessage}
    </body>
</html>
</fmt:bundle>
