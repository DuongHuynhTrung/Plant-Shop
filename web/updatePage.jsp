<%-- 
    Document   : updatePage
    Created on : Feb 14, 2022, 10:59:35 PM
    Author     : Trung Duong
--%>

<%@page import="duonght.dto.account"%>
<%@page import="duonght.dao.accountDao"%>
<%@page import="duonght.dto.Order"%>
<%@page import="duonght.dao.OrderDao"%>
<%@page import="duonght.dao.OrderDao"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="mycss.css" type="text/css" />
        <title>Update Customer Page</title>
    </head>
    <input type="hidden" id="message" value="<%= request.getAttribute("MESSAGE")%>"/>
    <script>
        var message = document.getElementById("message").value;
        if (message !== "null") {
            alert(message);
        }
    </script>
    <header>
        <%@include file="header_customerPage.jsp" %>
    </header>
    <body>

        <form action="UpdateServlet" method="POST">
            <%
                String email = (String) session.getAttribute("email");
                String passwork = (String) session.getAttribute("passwork");
                account acc = accountDao.getAccount(email, passwork);
                if (acc != null) {
            %>
            <table >
                <tbody>
                <h1>UPDATE YOUR PROFILE</h1>
                <tr>
                    <td style="width: 20%">FullName Before</td>
                    <td style="width: 20%">
                        <input style="width: 100%" value="<%= acc.getFullname()%>" name="FullName" readonly="">
                    </td>
                    <td style="width: 20%">Phone Before</td>
                    <td style="width: 20%">
                        <input style="width: 100%" value="<%= acc.getPhone()%>" name="Phone" readonly="">
                    </td>
                    <td><h3 style="color: red; width: 100%">${requestScope.ERROR_NAME}</h3></td>
                </tr>
                <tr>
                    <td style="width: 20%">FullName After</td>
                    <td style="width: 20%">
                        <input style="width: 100%" type="text" value="" name="txtFullNameAfter">
                    </td>
                    <td style="width: 20%">Phone After</td>
                    <td style="width: 20%">
                        <input style="width: 100%" type="number" value="" name="txtPhoneAfter">
                    </td>
                    <td><h3 style="color: red; width: 100%">${requestScope.ERROR_PHONE}</h3></td>
                </tr>
                </tbody>
            </table>
            <%}%>
            <input style="background-color: red; color: white" value="Save" type="submit" name="btAction">
        </form>
    </body>
    <footer>
        <%@include file="footer.jsp" %>
    </footer>
</html>
