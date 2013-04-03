<%-- 
    Document   : newOrder
    Created on : Mar 4, 2013, 10:58:15 PM
    Author     : Mark
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order</title>
    </head>
    <body>
        <%@include file="../jspf/banner.jspf" %>
        <table>
            <tr>
                <td>Account:</td>
                <td>${account.getName()}</td>
            </tr>
            <tr>
                <td>Price:</td>
                <td>&#36;${totalPrice}</td>
            </tr>
        </table>

        <form method="post" action="create.htm">
            <table>
                <tr>
                    <td>Payment Method:</td>
                    <td><input type="radio" name="paymentMethod" value="creditCard" checked />Credit Card</td>
                </tr>
            </table>
            <table>
                <tr>
                    <th colspan="2">Credit Card Info</th>
                </tr>
                <tr>
                    <td>Cardholder Name:</td>
                    <td><input type="text" name="cardholderName" /></td>
                </tr>
                <tr>
                    <td>Card Number:</td>
                    <td><input type="text" name="creditCardNumber" /></td>
                </tr>
                <tr>
                    <td>Verification Number:</td>
                    <td><input type="text" name="creditCardVerification" /></td>
                </tr>
            </table>
            <input type="hidden" name="loginToken" value="${uuid}"/>
            <input type="submit" value="Confirm" />
        </form>
    </body>
</html>
