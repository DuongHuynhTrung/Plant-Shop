<%-- 
    Document   : ManageAccounts
    Created on : Feb 24, 2022, 10:57:59 AM
    Author     : Trung Duong
--%>

<%@page import="duonght.dao.OrderDao"%>
<%@page import="duonght.dto.Order"%>
<%@page import="duonght.dto.account"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="mycss.css" type="text/css" />
    </head>
    <body>
        <header>
            <%@include file="header_AdminIndex.jsp" %>
        </header>
        <section style="display: flex; justify-content: space-between; padding: 1% 0 ">
            <form action="mainController" method="POST">
                <input type="number" min="1" placeholder="Enter Account ID" name="txtsearch" value=""/>
                <input type="submit" value="SearchOrder" name="btAction"/>
            </form>
            <form action="mainController" method="POST">
                From <input type="date" name="from"/> To <input type="date" name="to" />
                <input type="hidden" value="admin" name="check"/>
                <input type="submit" value="SearchOrders" name="btAction" />
            </form>
        </section>
        <%
            ArrayList<Order> orders = ( ArrayList<Order>) request.getAttribute("result");
            String[] status = {"", "Processing", "Completed", "Canceled"}; 
            if (orders.isEmpty() || orders == null) {
        %>
            <h1 style="color: red">HAVE NO ORDERS IN THIS CASE</h1>
        <%
            } else {
            %>
                <h2 style="color: blue">ORDER'S COMPLETED</h2>
                <%
                    ArrayList<Order> completed = ( ArrayList<Order>) request.getAttribute("completed");
                    if (completed.isEmpty() || completed == null) {
                %>
                <h3 style="color: red">HAVE NO ORDER IN THIS CASE</h3>
                <%
                    } else {
                %>
                <table class="order" border="1" >
                    <tr>
                        <th style="width: 10%; text-align: center"> Order ID</th>
                        <th style="width: 20%; text-align: center"> Order Date</th>
                        <th style="width: 20%; text-align: center"> Ship Date</th>
                        <th style="width: 10%; text-align: center"> Account ID</th>
                        <th style="width: 20%; text-align: center"> Status</th>
                        <th style="width: 20%; text-align: center"> Action</th>
                    </tr>
                <%
                    for (Order order : orders) {
                        if (order.getStatus() == 2) {
                %>
                    <tr>
                        <td style="width: 10%; text-align: center"><%= order.getOrderID()%></td>
                        <td style="width: 20%; text-align: center"><%= order.getOrderDate()%></td>
                        <td style="width: 20%; text-align: center"><%= order.getShipDate()%></td>
                        <td style="width: 10%; text-align: center"><%= order.getAccID()%></td>
                        <td style="width: 20%; text-align: center"><%= status[order.getStatus()]%></td>
                        <td style="width: 20%; text-align: center"><a href="OrderDetail.jsp?orderid=<%= order.getOrderID() %>&AccID=<%= order.getAccID() %>" >Detail</a></td>
                    </tr>
                <%
                            }
                        }
                    }
                %>
                </table>
                
                <h2 style="color: blue">ORDER'S PROCESSING</h2>
                <%
                    ArrayList<Order> processing = ( ArrayList<Order>) request.getAttribute("processing");
                    if (processing.isEmpty() || processing == null) {
                %>
                <h3 style="color: red">HAVE NO ORDER IN THIS CASE</h3>
                <%
                    } else {
                %>
                <table class="order" border="1" >
                    <tr>
                        <th style="width: 10%; text-align: center"> Order ID</th>
                        <th style="width: 20%; text-align: center"> Order Date</th>
                        <th style="width: 20%; text-align: center"> Ship Date</th>
                        <th style="width: 10%; text-align: center"> Account ID</th>
                        <th style="width: 20%; text-align: center"> Status</th>
                        <th style="width: 20%; text-align: center"> Action</th>
                    </tr>
                <%
                    for (Order order : orders) {
                        if (order.getStatus() == 1) {
                %>
                    <tr>
                        <td style="width: 10%; text-align: center"><%= order.getOrderID()%></td>
                        <td style="width: 20%; text-align: center"><%= order.getOrderDate()%></td>
                        <td style="width: 20%; text-align: center"><%= order.getShipDate()%></td>
                        <td style="width: 10%; text-align: center"><%= order.getAccID()%></td>
                        <td style="width: 20%; text-align: center"><%= status[order.getStatus()]%></td>
                        <td style="width: 20%; text-align: center"><a href="OrderDetail.jsp?orderid=<%= order.getOrderID() %>&AccID=<%= order.getAccID() %>" >Detail</a></td>

                    </tr>
                <%
                            }
                        }
                    }
                %>
                </table>
                
                <h2 style="color: blue">ORDER'S CANCELED</h2>
                <%
                    ArrayList<Order> canceled = ( ArrayList<Order>) request.getAttribute("canceled");
                    if (canceled.isEmpty() || canceled == null) {
                %>
                <h3 style="color: red">HAVE NO ORDER IN THIS CASE</h3>
                <%
                    } else {
                %>
                <table class="order" border="1" >
                    <tr>
                        <th style="width: 10%; text-align: center"> Order ID</th>
                        <th style="width: 20%; text-align: center"> Order Date</th>
                        <th style="width: 20%; text-align: center"> Ship Date</th>
                        <th style="width: 10%; text-align: center"> Account ID</th>
                        <th style="width: 20%; text-align: center"> Status</th>
                        <th style="width: 20%; text-align: center"> Action</th>
                    </tr>
                <%
                    for (Order order : orders) {
                        if (order.getStatus() == 3) {
                %>
                    <tr>
                        <td style="width: 10%; text-align: center"><%= order.getOrderID()%></td>
                        <td style="width: 20%; text-align: center"><%= order.getOrderDate()%></td>
                        <td style="width: 20%; text-align: center"><%= order.getShipDate()%></td>
                        <td style="width: 10%; text-align: center"><%= order.getAccID()%></td>
                        <td style="width: 20%; text-align: center"><%= status[order.getStatus()]%></td>
                        <td style="width: 20%; text-align: center"><a href="OrderDetail.jsp?orderid=<%= order.getOrderID() %>&AccID=<%= order.getAccID() %>" >Detail</a></td>
                    </tr>
                <%
                            }
                        }
                    }
                %>
                </table>
            <%      
                }
            %>
    </body>
</html>
