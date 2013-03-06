<%-- 
    Document   : showUser
    Created on : Mar 6, 2013, 10:32:43 AM
    Author     : Mark
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User - ${user.name}</title>
    </head>
    <body>
        <h1>${user.name}</h1>
        <table>
            <tr>
                <td>ID</td>
                <td>${user.id}</td>
            </tr>
            <tr>
                <td>Email</td>
                <td>${user.email}</td>
            </tr>
            <tr>
                <td>Password Hash</td>
                <td>${user.passwordHash}</td>
            </tr>
            <tr>
                <td>Activated</td>
                <td>${user.activated}</td>
            </tr>            
        </table>
    </body>
</html>
