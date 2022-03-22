<%-- 
    Document   : ManageCategory
    Created on : Mar 7, 2022, 12:03:59 AM
    Author     : Trung Duong
--%>

<%@page import="duonght.dto.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="mycss.css" type="text/css" />
        <title>Manage Category Page</title>
    </head>
    <body>
        <input type="hidden" value="<%= request.getAttribute("MESSAGE") %>" id="message" />
        <script>
            var message = document.getElementById("message").value;
            if (message !== "null") {
                alert(message);
            }
        </script>
        <header>
            <%@include file="header_AdminIndex.jsp" %>
        </header>
        <section style="padding-bottom: 2%; background: wheat;">
            <h1 style="color: blue">CREATE NEW CATEGORY</h1>
            <form  action="mainController" method="POST" style="display: flex; justify-content: space-around; width: 30% ">
                <p>
                    <h3>Category's Name: </h3>
                    <input type="text" required="" value="" name="txtName" />
                </p>
                <input type="submit" value="CreateCategory" name="btAction"/>
            </form>
        </section>
        <%
            ArrayList<Category> categories = (ArrayList<Category>) request.getAttribute("categories");
            if (categories.isEmpty() || categories == null) {
        %>
        <h1>CATEGORY LIST IS EMPTY</h1>
        <%
            } else {
        %>
                <table border="1" style="width: 100%; text-align: center" >
                    <thead>
                        <tr>
                            <th style="width: 10%">Cate ID</th>
                            <th style="width: 30%">Cate Name</th>
                            <th style="width: 40%">Update Name</th>
                            <th style="width: 20%">Delete Category</th>
                        </tr>
                    </thead>
                    <%
                        for (Category cate : categories) {
                    %>
                            <tbody>
                                <tr>
                                    <td style="width: 10%"><%= cate.getCateID() %></td>
                                    <td style="width: 30%"><%= cate.getCateName() %></td>
                                    <td style="width: 40%">
                                        <form action="mainController" method="POST">
                                            <input style="width: 60%; margin-right: 10%" type="text" required="" value="" name="textName" placeholder="Input new category's name"/>
                                            <input type="hidden" value="<%= cate.getCateID() %>" name="cateID" />
                                            <input type="submit" value="UpdateCategory" name="btAction" />
                                        </form>
                                    </td>
                                    <td style="width: 20%"><a href="mainController?btAction=DeleteCategory&cateID=<%= cate.getCateID() %>" onclick="return confirmDelete()">Delete</a></td>
                                </tr>
                            </tbody>
                    <%
                            }
                    %>
                    
                </table>
            <%
                }
            %>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
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
