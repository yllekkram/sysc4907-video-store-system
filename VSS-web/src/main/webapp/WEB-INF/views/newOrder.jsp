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
        <form:form method="post" action="create.htm">
            <table>
                <tr>
                    <td>Account:</td>
                    <td>${account.getName()}</td>
                </tr>
                <tr>
                    <td>Price:</td>
                    <td>${totalPrice}</td>
                </tr>
                <tr>
                    <td>Payment Method:</td>
                    <td><form:radiobutton path="paymentMethod" value="creditCard" />Credit Card</td>
                </tr>
            </table>
                <h3>Credit Card Info</h3>
                <table>
                    <tr>
                        <td><form:label path="cardholderName">Cardholder Name:</form:label></td>
                        <td><form:input path="cardholderName" /></td>
                    </tr>
                    <tr>
                        <td><form:label path="creditCardNumber">Card Number:</form:label></td>
                        <td><form:input path="creditCardNumber" /></td>
                    </tr>
                    <tr>
                        <td><form:label path="creditCardVerification">Verification Number:</form:label></td>
                        <td><form:input path="creditCardVerification" /></td>
                    </tr>
                </table>
            <input type="submit" value="Confirm" />
        </form:form>
    </body>
</html>
