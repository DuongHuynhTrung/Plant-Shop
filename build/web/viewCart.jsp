<%-- 
    Document   : viewCart
    Created on : Feb 15, 2022, 9:11:10 PM
    Author     : Trung Duong
--%>

<%@page import="java.sql.Date"%>
<%@page import="duonght.dao.PlantDao"%>
<%@page import="duonght.dto.Plant"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/mytag_library.tld" prefix="mytag"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="mycss.css" type="text/css" />
        <title>View Cart</title>
    </head>
    <header>
        <%@include file="header.jsp" %>
    </header>
    <input type="hidden" value="<%= request.getAttribute("WARNING") %>" id="message" />
        <script>
            var message = document.getElementById("message").value;
            if (message !== "null") {
                alert(message);
            }
        </script>
    <body>
        <section>
        <%
            name = (String) session.getAttribute("USER_NAME");
            if (name != null) {
        %>
        <h3><mytag:welcome name="${sessionScope.USER_NAME}"></mytag:welcome></h3>
            <h3><a href="mainController?btAction=logout">Logout</a></h3>
        <% }
        %>
        
        <table style="width: 100%; text-align: center" border="1" class="shopping">
                <%
                    HashMap<String, Integer> cart = (HashMap<String, Integer>) session.getAttribute("cart");
                    int total = 0;
                    if (cart != null && !cart.isEmpty()) {
                %>
                <tr>
                    <th style="width: 10%" >Product ID</th>
                    <th style="width: 10%">Name</th>
                    <th style="width: 30%">Image</th>
                    <th style="width: 10%">Price</th>
                    <th style="width: 10%">Quantity</th>
                    <th style="width: 30%">Action</th>
                </tr>
                <%
                        Set<String> pids = cart.keySet();
                        for (String pid : pids) {
                            Plant p = PlantDao.getPlant(Integer.parseInt(pid.trim()));
                            int quantity = cart.get(pid);
                        %>
                        <form action="mainController" method="POST">
                                <tr>
                                    <td style="width: 10%">
                                        <input type="hidden" name="pid" value="<%= pid %>" />
                                        <a href="mainController?btAction=viewdetail&pid=<%= pid %>" ><%= pid %></a>
                                    </td>
                                    <td style="width: 10%"><%= p.getName() %></td>
                                    <td style="width: 30%"><img width="40%" height="200px" src="<%= p.getImgPath() %>" /></td>
                                    <td style="width: 10%"><%= p.getPrice() %></td>
                                    <td style="width: 10%; text-align: center">
                                        <input  type="number" min="1" max="10" required="" name="quantity" value="<%= quantity %>" />
                                    </td>
                                    <td style="width: 30%">
                                        <input style="width: 50%" type="submit" name="btAction" value="Update Cart" />
                                        <input style="width: 50%" type="submit" name="btAction" onclick="return confirmDelete()" value="Delete Cart" />
                                    </td>
                                </tr>
                            </form>
                        <%
                            total = total + (quantity * p.getPrice());
                        }
                        %>
                <tr><td style="font-size: 1.5em">Total money: <%= total %></td></tr>
                <tr>
                    <td style="font-size: 1.5em">Order Date:<br/> <%= (new Date(System.currentTimeMillis())).toString() %></td>
                    <td style="font-size: 1.5em">Ship Date: N/A </td>
                </tr>
                <%
                    } else {
                %>
                    <tr><td style="font-size: 1.5em; color: red">Your cart is Empty</td></tr>
                <%
                    }
                %>
                
            </table>
        </section>
            <section>
                <form action="mainController" method="POST">
                    <section class='<%= session.getAttribute("USER_NAME") == null ? "active" : "block" %>'>
                        <h2 style="color: blue" >Your Information:</h2>
                        <!--Nhap ten, email, sdt-->
                        <table border="0">
                            <tbody>
                                <tr>
                                    <td>FullName</td>
                                    <td><input type="text" placeholder="Enter Your Full Name" name="txtFullName" value="<%= request.getAttribute("txtFullName")==null ? "" : request.getAttribute("txtFullName")%>" /></td>
                                </tr>
                                <tr>
                                    <td>Phone</td>
                                    <td>
                                        <input type="number" min="0" max="9999999999" placeholder="Enter Your Phone" name="txtPhone" value="<%= request.getAttribute("txtPhone")==null ? "" : request.getAttribute("txtPhone")%>" /> <br/>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </section>
                    <input style="font-size: 1.5em" type="submit" value="Save Order" name="btAction" class="submitorder" /> 
                </form>
            </section>
    <script>
        function confirmDelete() {
            if (confirm("Are you sure to Delete?")) {
                return true;
            }
            return false;
        }
    </script>      
    </body>
    <footer>
        <%@include file="footer.jsp" %>
    </footer>
    
</html>
