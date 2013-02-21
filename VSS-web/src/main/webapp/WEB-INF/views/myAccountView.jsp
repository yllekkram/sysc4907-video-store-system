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
        <br>
        <h2>Purchases</h2>
        <br>
        <table border="1">
            <tr>
                <th>
                    Video
                </th>
            </tr>
            <c:forEach items="${Orders}" var="ordersItem">
                <c:forEach items="${ordersItem.purchaseCollection}" var="purchase">
                    <tr>
                        <td><a href="viewVideo.htm/${purchase.videoInfo.id}"><c:out value="${purchase.videoInfo.title}"/></a></td>
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
            </tr>
            <c:forEach items="${Orders}" var="ordersItem">
                <c:forEach items="${ordersItem.rentalCollection}" var="rental">
                    <tr>
                        <td><a href="viewVideo.htm/${rental.videoInfo.id}"><c:out value="${rental.videoInfo.title}"/></a></td>
                    </tr>
                </c:forEach>
            </c:forEach>
        </table>
    </body>
</html>
