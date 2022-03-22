<%-- 
    Document   : header_customerPage
    Created on : Jan 19, 2022, 10:36:31 PM
    Author     : Trung Duong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <nav>
            <ul>
                <li><a href="mainController?btAction=SearchOrders"><img src="images/logo.jpg"></a> </li>
                <li><a href="mainController?btAction=SearchOrders">Home</a></li>
                <li><a href="updatePage.jsp">Change Profile</a></li>
                <li><a href="mainController?btAction=getCompleteOrders">Complete Orders</a></li>
                <li><a href="mainController?btAction=getCanceledOrders">Canceled Orders</a></li>
                <li><a href="mainController?btAction=getProcessingOrders">Processing Orders</a></li>
                <li>
                    <form action="mainController" method="POST">
                        From <input type="date" name="from"/> To <input type="date" name="to" />
                        <input type="hidden" value="customer" name="check"/>
                        <input type="submit" value="SearchOrders" name="btAction" />
                    </form>
                </li>
            </ul> 
        </nav>
    </body>
</html>
