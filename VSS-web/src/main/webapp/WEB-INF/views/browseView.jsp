<%-- 
    Document   : browseView
    Created on : 23-Jan-2013, 11:16:25 AM
    Author     : Samual
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
        <h1><c:out value="${videoInfo.getTitle()}"/></h1>
        <br>
        <h3>Description</h3>
        <br>
        <c:out value="${videoInfo.getDescription()}"/>
        <br>
        <h3>Release Date : <c:out value="${videoInfo.getReleaseDate()}"/></h3>
        <h3>Running Time : <c:out value="${runningHour}"/>:<c:out value="${runningMin}"/>H</h3>
        <h3>Genre : <c:out value="${videoInfo.getGenreid().getCategory()}"/></h3>
        <h3>Screen Rating : <c:out value="${videoInfo.getScreenRatingid().getRatingType()}"/></h3>
        <h3>Purchase : <c:out value="${videoInfo.getPurchasePrice()}"/></h3>
        <button type="button" value="/shoppingCartView/purchase/${videoInfo.getId()}.htm">Buy</button>
        <h3>Rental : <c:out value="${videoInfo.getRentalPrice()}"/></h3>
        <button type="button" value="/shoppingCartView/rental/${videoInfo.getId()}.htm">Rent</button>
    </body>
</html>
