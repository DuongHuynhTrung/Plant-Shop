<%-- 
    Document   : ManagePlants
    Created on : Mar 1, 2022, 2:51:29 PM
    Author     : Trung Duong
--%>

<%@page import="duonght.dao.PlantDao"%>
<%@page import="duonght.dto.Category"%>
<%@page import="duonght.dto.Plant"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="mycss.css" type="text/css" />
        <title>JSP Page</title>
    </head>
    <body>
        <input type="hidden" value="<%= request.getAttribute("MESSAGE") %>" id="ms" />
        <script>
            var message = document.getElementById("ms").value;
            if (message !== "" && message !== "null") {
                alert(message);
            }
        </script>
        <header>
            <%@include file="header_AdminIndex.jsp" %>
        </header>
        <section style="padding-bottom: 2%; background: wheat; margin-bottom: 1%">
            <h2 style="color: blue" >Create New Plant:</h2>
            <%
                ArrayList<Category> result = (ArrayList<Category>) request.getAttribute("categories");
            %>
            <form style="display: flex; justify-content: space-around" action="mainController" method="POST" >
                <p>
                    <h3>Product Name:</h3>
                    <input type="text" value="" required="" name="name" />
                </p>
                <p>
                    <h3>Product Price:</h3>
                    <input type="number" min="1" required="" value="" name="price" />
                </p>
                <p>
                    <h3>Product Image URL:</h3>
                    <input type="text" value="" required="" name="Img" />
                </p>
                <p>
                    <h3>Product Description:</h3>
                    <input type="text" value="" required="" name="description" />
                </p>
                <p>
                    <h3>Product Category: </h3>
                    <select name="category">
                    <%
                        for (Category cate : result) {
                    %>
                    <option value="<%= cate.getCateName() %>" ><%= cate.getCateName() %></option>
                    <%
                        }
                    %>
                    </select>
                </p>
                <input style="padding: 0 2%" type="submit" value="Create" name="btAction" />
            </form>
        </section>
        <%
            ArrayList<Plant> list = (ArrayList<Plant>) request.getAttribute("plants");
            String [] tmp = {"Out of Stock", "Available"};
            if (list == null || list.isEmpty()) {
        %>
            <h2 style="color: red">PLANTS LIST IS EMPTY</h2>
        <%
            } else {
        %>
        <h2 style="color: blue;">PLANT'S AVAILABLE</h2>
            <%
                ArrayList<Plant> avl = (ArrayList<Plant>) request.getAttribute("avl");
                if (avl.isEmpty() || avl == null) {
            %>
            <h2 style="color: red">PLANTS'S AVAILABLE IS EMPTY</h2>
        <%
            } else {
        %>
        <table border="1" style="width: 100%">
            <thead>
                <tr>
                    <th style="width: 10%">Product ID</th>
                    <th style="width: 10%">Product Name</th>
                    <th style="width: 10%">Price</th>
                    <th style="width: 40%">Image</th>
                    <th style="width: 10%">Status</th>
                    <th style="width: 10%">Category</th>
                    <th style="width: 30%">Action</th>
                </tr>
            </thead>
            <tbody>
            <%
                for (Plant plant : list) {
                    if (plant.getStatus() == 1) {
            %>
                <tr>
                    <td style="width: 10%; text-align: center"><%= plant.getId() %></td>
                    <td style="width: 10%; text-align: center"><%= plant.getName() %></td>
                    <td style="width: 10%; text-align: center"><%= plant.getPrice() %></td>
                    <td style="width: 40%"><img style="width: 30%; height: 200px; margin-left: 35%;" src="<%= plant.getImgPath() %>" class="product"/></td>
                    <td style="width: 10%; text-align: center"><%= tmp[plant.getStatus()] %></td>
                    <td style="width: 10%; text-align: center"><%= plant.getCateName() %></td>
                    <td style="width: 30%; text-align: center">
                        <a href="mainController?btAction=updateStatusPlant&pid=<%= plant.getId() %>&status=<%= plant.getStatus() %>&check=plant">Update Status/</a>
                        <a href="mainController?btAction=viewdetail&pid=<%= plant.getId() %>&check=updateplant">Update Plant/</a>
                        <a href="mainController?btAction=deletePlant&pid=<%= plant.getId() %>" onclick="return confirmDelete()">Delete Plant</a>
                    </td>
                </tr>
            <%
                    }
                }
            }   
            %> 
            </tbody>
        </table>
        
            <h2 style="color: blue">PLANT'S OUT OF STOCK</h2>
        <%
            ArrayList<Plant> oos = (ArrayList<Plant>) request.getAttribute("oos");
            if (oos.isEmpty() || oos == null) {
        %>
        <h3 style="color: red">HAVE NO PLANT IN THIS CASE</h3>
        <%
            } else {
        %>
        <table border="1" style="width: 100%">
            <thead>
                <tr>
                    <th style="width: 10%">Product ID</th>
                    <th style="width: 10%">Product Name</th>
                    <th style="width: 10%">Price</th>
                    <th style="width: 40%">Image</th>
                    <th style="width: 10%">Status</th>
                    <th style="width: 10%">Category</th>
                    <th style="width: 30%">Action</th>
                </tr>
            </thead>
            <tbody>
            <%
                for (Plant plant : list) {
                    if (plant.getStatus() == 0) {
            %>
                <tr>
                    <td style="width: 10%; text-align: center"><%= plant.getId() %></td>
                    <td style="width: 10%; text-align: center"><%= plant.getName() %></td>
                    <td style="width: 10%; text-align: center"><%= plant.getPrice() %></td>
                    <td style="width: 40%"><img style="width: 30%; height: 200px; margin-left: 35%;" src="<%= plant.getImgPath() %>" class="product"/></td>
                    <td style="width: 10%; text-align: center"><%= tmp[plant.getStatus()] %></td>
                    <td style="width: 10%; text-align: center"><%= plant.getCateName() %></td>
                    <td style="width: 30%; text-align: center">
                        <a href="mainController?btAction=updateStatusPlant&pid=<%= plant.getId() %>&status=<%= plant.getStatus() %>&check=plant">Update Status/</a>
                        <a href="mainController?btAction=deletePlant&pid=<%= plant.getId() %>" onclick="return confirmDelete()">Delete Plant</a>
                    </td>
                </tr>
        <%          }
                }   
            }
        %> 
            
            </tbody>
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
