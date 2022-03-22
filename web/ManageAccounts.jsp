<%-- 
    Document   : ManageAccounts
    Created on : Feb 24, 2022, 10:57:59 AM
    Author     : Trung Duong
--%>

<%@page import="duonght.dao.accountDao"%>
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
        <input type="hidden"  value="<%= request.getAttribute("OTP") %>" id="OTP"/>
        <input type="hidden"  value="<%= request.getAttribute("MESSAGE") %>" id="message"/>
        <script>
            var message = document.getElementById("OTP").value;
            if (message !== "" && message !== "null") {
                alert("An email has already send to email: " + message);
            } 
            var message = document.getElementById("message").value;
            if (message !== "" && message !== "null") {
                alert(message);
            } 
        </script>
        <header>
            <%@include file="header_AdminIndex.jsp" %>
        </header>
        <form action="mainController" method="POST">
            <input type="text" placeholder="Enter Full Name to search" name="txtsearch" value="<%= request.getAttribute("txtsearch") == null ? "" : request.getAttribute("txtsearch") %>"/>
            <input type="hidden" value="<%= session.getAttribute("email") %>" name="email" />
            <input type="submit" value="SearchAccount" name="btAction" />
        </form>
        <%
                ArrayList<account> accounts = ( ArrayList<account>) request.getAttribute("accounts");
                if (accounts.isEmpty() || accounts == null) {
        %>
        <h1 style="color: red">ACCOUNT LIST IS EMPTY</h1>
            <%
                } else {
                    ArrayList<account> admins = ( ArrayList<account>) request.getAttribute("admins");
                    if (admins.isEmpty() || admins == null) {
            %>
                <h1 style="color: red">ADMIN'S ACCOUNT LIST IS EMPTY</h1>
            <%
                } else {
            %> 
            <h2>ADMIN'S ACCOUNT</h2>
            <table class="order" border="1" >
                <tr>
                    <th style="width: 10%; text-align: center"> ID</th>
                    <th style="width: 20%; text-align: center"> Email</th>
                    <th style="width: 20%; text-align: center"> Full Name</th>
                    <th style="width: 10%; text-align: center"> Phone</th>
                    <th style="width: 10%; text-align: center"> Role</th>
                    <th style="width: 10%; text-align: center"> Status</th>
                    <th style="width: 20%; text-align: center"> Action</th>
                </tr>
            <%
                    for (account acc : accounts) {
                        if (acc.getRole() == 1) {       
            %>
                        <tr>
                            <td style="width: 10%; text-align: center"><%= acc.getAacId() %></td>
                            <td style="width: 20%; text-align: center"><%= acc.getEmail() %></td>
                            <td style="width: 20%; text-align: center"><%= acc.getFullname() %></td>
                            <td style="width: 10%; text-align: center"><%= acc.getPhone() %></td>
                            <td style="width: 10%; text-align: center"><%= acc.getRole() == 1 ? "Admin" : "User" %></td>
                            <td style="width: 10%; text-align: center"><%= acc.getStatus() == 1 ? "Active" : "InActive" %></td>
                            <td style="width: 20%; text-align: center">
                                <p style="font-weight: bold">READ ONLY ALLOW</p>
                            </td>
                        </tr>
                <%  
                        }
                    }
                }
                %>
            </table>
                   <%
                       ArrayList<account> users = ( ArrayList<account>) request.getAttribute("users");
                       if (users.isEmpty() || users == null) {
                   %>
            <h1 style="color: red">ADMIN'S ACCOUNT LIST IS EMPTY</h1>
            <%
                    } else {
            %> 
            <h2>USER'S ACCOUNT</h2>
            <table class="order" border="1" >
                <tr>
                    <th style="width: 10%; text-align: center"> ID</th>
                    <th style="width: 20%; text-align: center"> Email</th>
                    <th style="width: 20%; text-align: center"> Full Name</th>
                    <th style="width: 10%; text-align: center"> Phone</th>
                    <th style="width: 10%; text-align: center"> Role</th>
                    <th style="width: 10%; text-align: center"> Status</th>
                    <th style="width: 20%; text-align: center"> Action</th>
                </tr>
            <%
                    for (account acc : accounts) {
                        if (acc.getRole() == 0 && acc.getStatus() == 1) {
            %>
                        <tr>
                            <td style="width: 10%; text-align: center"><%= acc.getAacId() %></td>
                            <td style="width: 20%; text-align: center"><%= acc.getEmail() %></td>
                            <td style="width: 20%; text-align: center"><%= acc.getFullname() %></td>
                            <td style="width: 10%; text-align: center"><%= acc.getPhone() %></td>
                            <td style="width: 10%; text-align: center"><%= acc.getRole() == 1 ? "Admin" : "User" %></td>
                            <td style="width: 10%; text-align: center"><%= acc.getStatus() == 1 ? "Active" : "InActive" %></td>
                            <td style="width: 20%; text-align: center">
                                <a href="mainController?btAction=updateStatusAccount&email=<%= acc.getEmail() %>&status=<%= acc.getStatus() %>" >Block</a>
                            </td>
                        </tr>
                <%
                        }
                    }
                    }
                %>
                <%
                    for (account acc : accounts) {
                        if (acc.getRole() == 0 && acc.getStatus() == 0) {
                %>
                        <tr>
                            <td style="width: 10%; text-align: center"><%= acc.getAacId() %></td>
                            <td style="width: 20%; text-align: center"><%= acc.getEmail() %></td>
                            <td style="width: 20%; text-align: center"><%= acc.getFullname() %></td>
                            <td style="width: 10%; text-align: center"><%= acc.getPhone() %></td>
                            <td style="width: 10%; text-align: center"><%= acc.getRole() == 1 ? "Admin" : "User" %></td>
                            <td style="width: 10%; text-align: center"><%= acc.getStatus() == 1 ? "Active" : "InActive" %></td>
                            <td style="width: 20%; text-align: center">
                                <a href="mainController?btAction=updateStatusAccount&email=<%= acc.getEmail() %>&status=<%= acc.getStatus() %>" >UnBlock</a>
                                <a href="mainController?btAction=deleteAccount&AccID=<%= acc.getAacId() %>" onclick="return confirmDelete()" > / Delete</a>
                            </td>
                        </tr>
                <%
                        }
                    }
                %>
                </table>
            <%          
                }
            %>
            <script>
            function confirmDelete() {
                if (confirm("Are you sure to Delete?")) {
                    return true;
                }
                return false;
            }
        </script>
    </body>
</html>
