<%-- 
    Document   : customerPage
    Created on : Jan 19, 2022, 10:34:32 PM
    Author     : Trung Duong
--%>

<%@page import="duonght.dao.accountDao"%>
<%@page import="duonght.dto.account"%>
<%@page import="duonght.dao.OrderDao"%>
<%@page import="duonght.dto.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/mytag_library.tld" prefix="mytag"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="mycss.css" type="text/css" />
        <title>Customer Page</title>
    </head>
    <body>
        <%
            String name = (String) session.getAttribute("USER_NAME");
            String email = (String) session.getAttribute("email");
            Cookie[] c = request.getCookies();
            boolean login = false;
            if (name == null) {
                String token = "";
                for (Cookie cookie : c) {
                        if (cookie.getName().equals("selector")) {
                            token = cookie.getValue();
                            account acc = accountDao.getAccount(token);
                            if (acc != null) {
                                name = acc.getFullname();
                                email = acc.getEmail();
                                login = true;
                            }
                        }
                    }
            } else login = true;
            if (login) {
        %>
        <header>
            <%@include file="header_customerPage.jsp" %>
        </header>
            <input type="hidden" value="${requestScope.WARNING}" id="warrning" />
            <input type="hidden" value="${requestScope.MESSAGE}" id="message" />
        <script>
            var warrning = document.getElementById("warrning").value;
            if (warrning !== "") {
                alert(warrning);
            }
            var message = document.getElementById("message").value;
            if (message !== "") {
                alert(message);
            }
        </script>
        <section>
            <h3><mytag:welcome name="${sessionScope.USER_NAME}"></mytag:welcome></h3>
            <h3><a href="mainController?btAction=logout">Logout</a></h3>
        </section>
        <section>
            <!--load all orders of the user at here-->
            <%
                ArrayList<Order> orders = (ArrayList<Order>) request.getAttribute("result");    
                String[] status = {"", "Processing", "Completed", "Canceled"};
                if (orders != null && !orders.isEmpty()) {
                    for (Order order : orders) { %>
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
                                    <br/>
                                        <% if (order.getStatus() == 1) { %>
                                            <a href="ReOrder?status=<%= order.getStatus() %>&orderID=<%= order.getOrderID() %>" >Cancel Order</a> 
                                        <%} else if (order.getStatus() == 3) {%>
                                                    <a href="ReOrder?status=<%= order.getStatus() %>&orderID=<%= order.getOrderID() %>" >Order Again</a>
                                                <%}%>
                                </td>
                                <td style="width: 20%; text-align: center"><a href="OrderDetail.jsp?orderid=<%= order.getOrderID() %>" >Detail</a></td>
                            </tr>
                        </tbody>
                    </table>
                <% }
                } else { 
            %>
                <p>You don't have any order</p>
            <%}%>
        </section>
        <a href="mainController?btAction=search">Go to Shopping</a>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
        <% } else {
        %> 
        <p> <font color="red" >You must login to view personal page<font/> </p>
        <a href="mainController?btAction=login" >Login</a>
        <%
            }
        %>
    </body>
</html>
