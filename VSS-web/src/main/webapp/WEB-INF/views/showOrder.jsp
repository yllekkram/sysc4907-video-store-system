<%-- 
    Document   : showOrder
    Created on : Mar 4, 2013, 11:09:32 PM
    Author     : Mark
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="../jspf/banner.jspf" %>
        <h1>Showing Order ${order.getOrdersPK().getId()}!</h1>
    </body>
</html>
