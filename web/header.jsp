<%-- 
    Document   : header
    Created on : Jan 18, 2022, 10:17:25 PM
    Author     : Trung Duong
--%>

<%@page import="duonght.dto.account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <header>
            <nav>
                <ul>
                    <li><a href="mainController?btAction=search"><img src="images/logo.jpg"></a> </li>
                    <li><a href="mainController?btAction=search">Home</a></li>
                    <li>
                        <%
                            String name = (String) session.getAttribute("USER_NAME");
                            if (name != null) {
                                %>
                        <a href="mainController?btAction=SearchOrders" >Personal Page</a>
                                <%
                            } else {
                        %>
                        <a href="login.jsp" >Login</a>
                        
                        <%}%>
                    </li>
                    <li><a href="mainController?btAction=viewcart" >View Cart</a></li>
                    <li><form action="mainController" method="post" class="formsearch">
                            <input type="text" placeholder="Enter Name/Cate to search" name="txtsearch" value="<%= request.getParameter("txtsearch")==null?"":request.getParameter("txtsearch") %>">
                            <select name="searchby">
                                <option value="byname">by name</option><option value="bycate">by category</option>
                            </select>
                            <input type="submit" value="search" name="btAction" >
                        </form></li>
                </ul> 
            </nav>
        </header>
    </body>
</html>
