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
            <h3>Please confirm the current video orders</h3> 
            <fieldset>
                <legend>Purchases</legend>
                <table>
                </table>
            </fieldset>
            <fieldset>
                <legend>Rentals</legend>
                <table>
                </table>
            </fieldset>
            <fieldset>
                <legend>Payment Options</legend>
                <table>
                    <input type="submit" name="Submit" value="Confirm Video Orders">
                </table>
            </fieldset>
        </form>
    </body>
