<%-- 
    Document   : heaeder_AdminIndex
    Created on : Feb 24, 2022, 9:12:05 AM
    Author     : Trung Duong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/mytag_library.tld" prefix="mytag"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <nav>
            <ul>
                <li><a href="AdminIndex.jsp"><img src="images/logo.jpg"></a> </li>
                <li><a href="mainController?btAction=manageAccounts&email=<%= session.getAttribute("email") %>" >Manage Account</a></li>
                <li><a href="mainController?btAction=manageOrders&check=admin">Manage Orders</a></li>
                <li><a href="mainController?btAction=managePlants&check=plant" >Manage Plants</a></li>
                <li><a href="mainController?btAction=manageCategory&check=category" >Manage Categories</a></li>
                <li><h2><mytag:welcome name="${sessionScope.ADMIN_NAME}"></mytag:welcome> |  <a href="mainController?btAction=logout" >Logout</a></h2></li>
            </ul>
        </nav>
    </body>
</html>
