<%-- 
    Document   : customerPage
    Created on : Jan 19, 2022, 10:34:32 PM
    Author     : Trung Duong
--%>

<%@page import="duonght.dao.OrderDao"%>
<%@page import="duonght.dto.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="mycss.css" type="text/css" />
        <title>Processing Orders Page</title>
    </head>
    <body>
        <%
            String name = (String) session.getAttribute("USER_NAME");
            if (name == null) {

        %>
        <p> <font color="red" >You must login to view personal page<font/> </p>
        <%        } else {
        %>
        <header>
            <%@include file="header_customerPage.jsp" %>
        </header>
        <section>
            <h3>Welcome <%= name%> come back</h3>
            <h3><a href="mainController?btAction=Logout">Logout</a></h3>
        </section>
        <section>
            <!--load all orders of the user at here-->
            <%
                ArrayList<Order> orders = (ArrayList<Order>) request.getAttribute("result");
                String[] status = {"", "Processing", "Completed", "Canceled"};
                if (orders != null && !orders.isEmpty()) {
                    for (Order order : orders) {
            %>
                    <table style="width: 100%">
                        <thead>
                            <tr>
                                <th style="width: 20%; text-align: center">Order ID</th>
                                <th style="width: 20%; text-align: center">Order Date</th>
                                <th style="width: 20%; text-align: center">Ship Date</th>
                                <th style="width: 20%; text-align: center">Order's Status</th>
                                <th style="width: 20%; text-align: center">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td style="width: 20%; text-align: center"><%= order.getOrderID() %></td>
                                <td style="width: 20%; text-align: center"><%= order.getOrderDate() %></td>
                                <td style="width: 20%; text-align: center"><%= order.getShipDate() %></td>
                                <td style="width: 20%; text-align: center"><%= status[order.getStatus()] %>
                                    <br/><% if (order.getStatus() == 1) %><a href="ReOrder?status=<%= order.getStatus() %>&orderID=<%= order.getOrderID() %>" >Cancel Order</a>
                                </td>
                                <td style="width: 20%; text-align: center"><a href="OrderDetail.jsp?orderid=<%= order.getOrderID() %>" >Detail</a></td>
                            </tr>
                        </tbody>
                    </table>
                <%      
                    }
                } else { 
            %>
                <p>You don't have any Processing Order</p>
            <%}%>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
        <% }
        %>
    </body>
</html>
