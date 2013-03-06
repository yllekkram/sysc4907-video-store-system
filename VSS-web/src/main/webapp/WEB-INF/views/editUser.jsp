<%-- 
    Document   : editUser
    Created on : Mar 6, 2013, 8:30:02 AM
    Author     : Mark
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit User - ${user.name}</title>
    </head>
    <body>
        <h1>Editing ${user.name}</h1>
        <%@include file="/WEB-INF/jspf/userForm.jspf" %>
    </body>
</html>
