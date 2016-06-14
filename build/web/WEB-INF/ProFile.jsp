

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
          
        <ul>
            <li>${u}
        <c:forEach var="user" items="${requestScope.users}">
            <li>${user.firstName}    
        </c:forEach>
        </ul>
    </body>
</html>
