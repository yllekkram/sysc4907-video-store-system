<%-- 
    Document   : users
    Created on : Mar 5, 2013, 11:54:25 PM
    Author     : Mark
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users</title>
    </head>
    <body>
        <h1>Users</h1>
        <c:choose>
            <c:when test="${empty userList}">
                No users found.
            </c:when>
            <c:otherwise>
                <table>
                    <tr>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Password Hash</th>
                        <th>Activated</th>
                    </tr>
                    <c:forEach var="user" items="${userList}">
                        <tr>
                            <td>${user.name}</td>
                            <td>${user.email}</td>
                            <td>${user.passwordHash}</td>
                            <td>${user.activated}</td>
                            <td><a href="user/${user.id}/show">View</a></td>
                            <td><a href="user/${user.id}/delete">delete</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
    </body>
</html>
