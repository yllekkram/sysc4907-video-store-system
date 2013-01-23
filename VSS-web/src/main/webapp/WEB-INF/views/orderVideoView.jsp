<%-- 
    Document   : orderVideoView
    Created on : 21-Jan-2013, 5:46:53 PM
    Author     : Samual
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head> <title>Online Video Store System</title> </head>
    <body>
        <!--Used to capture the exceptions -->
        <c:if test="${exception ne null}">
            <div class="error">
                ${exception.message}
            </div>
        </c:if>
        
        <form action="<c:url value="/orderVideoView.htm"/>" method="post">
            <h3>Please confirm the payment option for your video orders</h3> 
            <fieldset>
                <legend>Video Orders</legend>
                <table>
                    <!--Need a way to display the users video orders-->
                    <c:forEach items="${videoOrders.rows}" var="row">
                        <tr>
                            <td><c:out value="${row.id}" /></td>
                            <td><c:out value="${row.accountId}" /></td>
                            <td><c:out value="${row.price}" /></td>
                        </tr>
                    </c:forEach>
                </table>
            </fieldset>
            <fieldset>
                <legend>Payment Options</legend>
                <!--Display payment options and payment validation input-->
                <table>
                    <p><input type="radio" name="PaymentOptions" value="Visa"> Visa</input></p>
                    <input type="text" id="visaConfirmNum" name="visaNum"
                                   placeholder="Please enter your last 4 visa digits"/></td>
                    <p><input type="radio" name="PaymentOptions" value="American Express"> American Express</input></p>
                    <input type="text" id="amexConfirmNum" name="amexNum"
                                   placeholder="Please enter your american express validation numbers"/></td>
                    <p><input type="radio" name="PaymentOptions" value="Paypal"> Paypal</input></p>
                    <input type="text" id="paypal" name="paypal"
                                   placeholder="Please enter your paypal id number"/></td>
                    <input type="submit" name="Submit" value="Confirm Video Orders">
                </table>
            </fieldset>
        </form>
    </body>
