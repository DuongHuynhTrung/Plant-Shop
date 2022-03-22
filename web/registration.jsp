<%-- 
    Document   : registration
    Created on : Mar 14, 2022, 12:20:24 AM
    Author     : Trung Duong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Page</title>
        <link rel="stylesheet" href="mycss.css" type="text/css" />
    </head>
    <body>
        <header>
            <%@ include file="header.jsp" %>
        </header>
        <div style="width: 100%">
            <section style="width: 40%; float: right">
                <form action="mainController" Method="post" class="formregister">
                    <h1 style="color: blue">Register</h1>
                    <table>
                        <tr>
                            <td>Email</td>
                            <td><input type="text" name="txtEmail" value="${requestScope.txtEmail}" required=""></td>
                            <td><h3 style="color: red">${requestScope.ERROR_EMAIL}</h3></td>
                        </tr>
                        <tr>
                            <td>Full Name</td>
                            <td><input type="text" name="txtFullName" value="${requestScope.txtFullName}" required=""></td>
                            <td><h3 style="color: red">${requestScope.ERROR_FULLNAME}</h3></td>
                        </tr>
                        <tr>
                            <td>Password</td>
                            <td><input type="password" name="txtPassWord" value="${requestScope.txtPassWord}" required=""></td>
                            <td><h3 style="color: red">${requestScope.ERROR_PASSWORD}</h3></td>
                        </tr>
                        <tr>
                            <td>Confirm PassWord</td>
                            <td><input type="password" name="txtPassWordConfirm" value="${requestScope.txtPassWordConfirm}" required=""></td>
                            <td><h3 style="color: red">${requestScope.ERROR_CONFIRM}</h3></td>
                        </tr>
                        <tr>
                            <td>Phone</td>
                            <td><input type="text" name="txtPhone" value="${requestScope.txtPhone}" required=""></td>
                            <td><h3 style="color: red">${requestScope.ERROR_PHONE}</h3></td>
                        </tr>
                        <tr>
                            <td colspan="2"><input type="submit" value="Register" name="btAction"></td>
                        </tr>
                    </table>
                </form>
                <h3 style="color: red">${requestScope.ERROR}</h3>
            </section>
            <section style="width: 40%; float: right">
                <img src="images/register.jpg" alt=""/>
            </section>
        </div>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
