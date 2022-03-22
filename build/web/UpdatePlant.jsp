<%-- 
    Document   : UpdatePlant
    Created on : Mar 6, 2022, 2:06:16 PM
    Author     : Trung Duong
--%>

<%@page import="duonght.dao.CategoryDao"%>
<%@page import="duonght.dto.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="duonght.dto.Plant"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="mycss.css" type="text/css" />
        <title>Update Plant Page</title>
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
        <form action="mainController" method="POST">
            <%
                Plant p = (Plant) request.getAttribute("Plant");
                if (p != null) {
            %>
            <input type="hidden" value="<%= p.getId() %>" name="pid" />
            <table style="width: 100%; text-align: center" border="1">
                
                <tbody>
                <h1 style="color: blue">UPDATE PLANT</h1>
                    <tr style="width: 100%">
                        <td style="width: 5%">Plant Name</td>
                        <td style="width: 20%">
                            <input value="<%= p.getName()%>" name="" readonly="">
                        </td>
                        <td style="width: 5%">Plant Price</td>
                        <td style="width: 20%">
                            <input value="<%= p.getPrice()%>" name="" readonly="">
                        </td>
                        <td style="width: 5%">Plant Image</td>
                        <td style="width: 20%">
                            <img style="width: 50%" src="<%= p.getImgPath() %>"/>
                        </td>
                        <td style="width: 5%">Plant Category</td>
                        <td style="width: 20%">
                            <input value="<%= p.getCateName()%>" name="" readonly="">
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 5%">Plant Name After</td>
                        <td style="width: 20%">
                            <input type="text" value="" required="" name="txtNameAfter">
                        </td>
                        <td style="width: 5%">Plant Price After</td>
                        <td style="width: 20%">
                            <input type="number" min="1" value="" required="" name="txtPriceAfter">
                        </td>
                        <td style="width: 5%"></td>
                        <td style="width: 20%">
                        </td>
                        <td style="width: 5%">Plant Category After</td>
                        <td style="width: 20%">
                            <% 
                                ArrayList<Category> categories = CategoryDao.getCategories();
                            %>
                            <select name="txtCategoryAfter">
                                <%
                                    for (Category cate : categories) {
                                %>
                                <option value="<%= cate.getCateName() %>">
                                    <%= cate.getCateName() %>
                                </option>
                                <%
                                        }
                                %>
                            </select>
                        </td>
                    </tr>
                </tbody>
            </table>
            <%}%>
            <input style="background-color: red; color: white; padding: 0.5%" value="SavePlant" type="submit" name="btAction">
        </form>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
