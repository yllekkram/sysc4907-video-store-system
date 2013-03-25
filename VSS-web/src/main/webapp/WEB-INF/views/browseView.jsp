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
        <h1><c:out value="${info.title}"/></h1>
        <br>
        <h3>Description</h3>
        <br>
        <c:out value="${info.description}"/>
        <br>
        <h3>Release Date : <c:out value="${info.releaseDate}"/></h3>
        <h3>Running TIme : <c:out value="${runningHour}"/>:<c:out value="${runningMin}"/>H</h3>
        <h3>Genre : <c:out value="${info.genre.category}"/></h3>
        <h3>Screen Rating : <c:out value="${info.screenRating.ratingType}"/></h3>
        <h3>Purchase : <c:out value="${info.purchasePrice}"/></h3>
        <h3>Rental : <c:out value="${info.rentalPrice}"/></h3>
        <button type="button" value="/shoppingCartView.htm/${info.id}"></button>
    </body>
</html>
