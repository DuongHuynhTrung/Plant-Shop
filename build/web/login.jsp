<%-- 
    Document   : login
    Created on : Jan 9, 2022, 11:53:24 AM
    Author     : Trung Duong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Index Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="mycss.css" type="text/css" />
    </head>
    <body>
        <header>
            <%@include file="header.jsp" %>
        </header>
        <input id="MESSAGE" type="hidden" value="${requestScope.MESSAGE}" />
        <script>
            if (document.getElementById("MESSAGE").value !== "") {
                alert(document.getElementById("MESSAGE").value);
            }
        </script>
        <div style="width: 100%;">
            <section style="width: 40%; float: right;">
                <form action="LoginServlet" method="POST">
                    <font style="color: red" ><%= request.getAttribute("WARNING") == null ? "" : request.getAttribute("WARNING")%></font>
                    <table border="0">
                        <thead>
                        <h1 style="color: blue">LOGIN</h1>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Email</td>
                                <td><input type="text" placeholder="Enter Your Email" name="txtEmail" value="" /></td>
                            </tr>
                            <tr>
                                <td>PassWork</td>
                                <td>
                                    <input type="text" placeholder="Enter Your PassWord" name="txtPassWork" value="" />
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <input style="padding: 2% 5%" type="submit" name="btAction" value="Login" />
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <input type="checkbox" name="savelogin" value="savelogin" />
                                    Stayed Signed in
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
                <h3>If you don't have account? <a href="registration.jsp">Register</a></h3> 
                <h2> OR <br/> </h2>
                <h4><a href="https://accounts.google.com/o/oauth2/auth?scope=profile email&redirect_uri=http://localhost:8084/FlowerManagementSystem/LoginGoogleHandler&response_type=code
                       &client_id=780080631666-i24cahc0jlot2d6t0g3vg8uanplr0cbp.apps.googleusercontent.com&approval_prompt=force">Login With Google</a></h4>
            </section>
            <section style="width: 40%; float: top; text-align: center">
                <img style="margin: 0 0; width: 90%" src="images/login.jpg"/>
            </section>
        </div>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
