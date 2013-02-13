<%-- 
    Document   : login.jsp
    Created on : 31-Dec-2012, 1:43:22 PM
    Author     : Samual Martelli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <%@include file="../jspf/banner.jspf" %>
        <c:if test="${exception ne null}">
            <div class="error">
                ${exception.message}
            </div>
        </c:if>
        <form action="<c:url value="/login.htm"/>" method="post">
            <fieldset>
                <legend>Account Login</legend>
                <table>
                    <tr>
                        <td>Username</td>
                        <td>
                            <input type="text" id="username" name="username"
                                   placeholder="Username"/></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td>
                            <input type="password" id="password" name="password"
                                   placeholder="Password"/></td>
                    </tr>
                    <input type="submit" name="Submit" value="Login">
                </table>
            </fieldset>
        </form>
    </body>
</html>
