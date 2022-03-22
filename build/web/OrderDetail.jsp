<%-- 
    Document   : customerPage
    Created on : Jan 19, 2022, 10:34:32 PM
    Author     : Trung Duong
--%>

<%@page import="duonght.dao.accountDao"%>
<%@page import="duonght.dto.account"%>
<%@page import="duonght.dto.OrderDetails"%>
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
        <title>Customer Page</title>
    </head>
    <body>
        <%
            String nameUser = (String) session.getAttribute("USER_NAME");
            String nameAdmin = (String) session.getAttribute("ADMIN_NAME");
            if (nameUser == null && nameAdmin == null) {
        %>
        <p> <font color="red" >You must login to view detail<font/> </p>
        <%  } else{
        %>
        <%
            if (nameUser != null) {
        %>
        <header>
            <%@include file="header_customerPage.jsp" %>
        </header>
        <section>
            <h3>Welcome <%= nameUser%> come back</h3>
            <h3><a href="mainController?btAction=logout">Logout</a></h3>
            <a href="mainController?btAction=SearchOrders&check=customer" >View All Orders</a>
        </section>
        <%
            } else {
        %>
        <header>
            <%@include file="header_AdminIndex.jsp" %>
        </header>
        <section>
            <h3>Welcome <%= nameAdmin %> come back</h3>
            <h3><a href="mainController?btAction=logout">Logout</a></h3>
            <a href="mainController?btAction=SearchOrders&check=admin" >View All Orders</a>
        </section>
        <%
            }
            String AccID = request.getParameter("AccID");
            if (AccID != null) {
            account acc = accountDao.getAccount(Integer.parseInt(AccID));
        %>
        <h2 style="color: tomato">Detail Order Of: <%= acc == null ? "" : acc.getFullname() %></h2>
        <%
            }
        %>
        <section>
            <!--load all orders of the user at here-->
            <%
                String orderID = request.getParameter("orderid");
                if (orderID != null) {
                    int OrderID = Integer.parseInt(orderID.trim());
                    ArrayList<OrderDetails> details = (ArrayList<OrderDetails>)OrderDao.getOrderDetail(OrderID);
                    if (!details.isEmpty() && details != null) {
                        int money = 0;
                    for (OrderDetails detail : details) { %>
                    <table style="width: 100%" class="order">
                        <thead>
                            <tr>
                                <th style="width: 10%; text-align: center">Order ID</th>
                                <th style="width: 10%; text-align: center">Plant ID</th>
                                <th style="width: 20%; text-align: center">Plant Name</th>
                                <th style="width: 10%; text-align: center">Price</th>
                                <th style="width: 40%; text-align: center">Image</th>
                                <th style="width: 10%; text-align: center">Quanlity</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td style="width: 10%; text-align: center"><%= detail.getOrderID() %></td>
                                <td style="width: 10%; text-align: center"><%= detail.getPlantID() %></td>
                                <td style="width: 20%; text-align: center"><%= detail.getPlantName() %></td>
                                <td style="width: 10%; text-align: center"><%= detail.getPrice() %></td>
                                <td style="width: 40%; text-align: center"><img style="width: 30%; height: 200px; margin: 20px auto;" src="<%= detail.getImgPath() %>" class="planting"/> </td>
                                <td style="width: 10%; text-align: center"><%= detail.getQuanlity() %></td>
                                <% money = money + (detail.getPrice() * detail.getQuanlity()); %>
                            </tr>
                        </tbody>
                    </table>
                <%  }//end For  %>
                <h1>    Total money: <%= money %></h1>
                <% } //end if
                 else { 
            %>
                <p>You don't have any order</p>
            <% }
            }
            %>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
        <% }
        %>
    </body>
</html>
