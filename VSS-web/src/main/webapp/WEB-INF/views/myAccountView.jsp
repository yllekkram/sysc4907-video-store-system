<%-- 
    Document   : myAccountView
    Created on : Feb 14, 2013, 12:44:45 PM
    Author     : Daywalker
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Account</title>
    </head>
    <body>
        <%@include file="../jspf/banner.jspf" %>
        <h1>Account Information</h1>
        <br>
        Name : <c:out value="${Name}" />
        <br>
        <h1>Orders</h1>
        <h2>Purchases</h2>
        <br>
        <table border="1">
            <tr>
                <th>Video</th>
                <th>Price</th>
            </tr>
            <c:forEach items="${Orders}" var="ordersItem">
                <c:forEach items="${ordersItem.getPurchaseCollection()}" var="purchase">
                    <tr>
                        <td><a href="viewVideo/${purchase.getOrders().getOrdersPK().getId()}/${purchase.getVideoInfo().getId()}.htm"><c:out value="${purchase.getVideoInfo().getTitle()}"/></a></td>
                        <td>${purchase.getVideoInfo().getPurchasePrice()}</td>
                    </tr>
                </c:forEach>
            </c:forEach>
        </table>
        <br>
        <h2>Rentals</h2>
        <br>
        <table border="1">
            <tr>
                <th>Video</th>
                <th>Price</th>
                <th>Expiry Date</th>
            </tr>
            <c:forEach items="${Orders}" var="ordersItem">
                <c:forEach items="${ordersItem.getRentalCollection()}" var="rental">
                    <tr>
                        <td><a href="viewVideo/${purchase.getOrders().getOrdersPK().getId()}/${rental.getVideoInfo().getId()}.htm"><c:out value="${rental.getVideoInfo().getTitle()}"/></a></td>
                        <td>${purchase.getVideoInfo().getRentalPrice()}</td>
                        <td>${rental.getRentalPK().getRentalExpiryDate().toString()}</td>
                    </tr>
                </c:forEach>
            </c:forEach>
        </table>
    </body>
</html>
