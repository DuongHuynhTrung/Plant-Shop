<%-- 
    Document   : AdminIndex
    Created on : Feb 24, 2022, 9:16:15 AM
    Author     : Trung Duong
--%>

<%@page import="duonght.dao.accountDao"%>
<%@page import="duonght.dto.account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="mycss.css" type="text/css" />
        <title>Admin Page</title>
    </head>
    <body>
        <%
            String name = (String) session.getAttribute("ADMIN_NAME");
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
            <%@include file="header_AdminIndex.jsp" %>
        </header>
        
        <section>
            <img width="100%" src="images/background.jpg" >
        </section>
        <%
            } else {
        %>
        <h1 style="color: red">You are not an Admin</h1>
        <% }
        %>
    </body>
</html>
