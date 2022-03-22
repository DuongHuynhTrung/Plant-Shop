<%-- 
    Document   : index
    Created on : Jan 10, 2022, 10:22:27 PM
    Author     : Trung Duong
--%>

<%@page import="duonght.dao.PlantDao"%>
<%@page import="duonght.dto.Plant"%>
<%@page import="java.util.ArrayList"%>
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
            <jsp:useBean id="Plant" class="duonght.dto.Plant" scope="request"/>
            <% String[] status = {"", "Processing", "Completed", "Canceled"}; %>
            
        <table border="1" style="width: 100%">
            <thead>
            <th><a href="mainController?btAction=viewcart" >Back To Cart</a></th>
                <tr>
                    <th style="width: 10%">Product ID</th>
                    <th style="width: 10%">Product Name</th>
                    <th style="width: 10%">Price</th>
                    <th style="width: 40%">Image</th>
                    <th style="width: 10%">Status</th>
                    <th style="width: 10%">Category</th>
                    <th style="width: 30%">Description</th>
                </tr>
            </thead>
            <tbody>
<!--                <tr>
                    <td style="width: 10%; text-align: center">${Plant.id}</td>
                    <td style="width: 10%; text-align: center">${Plant.name}</td>
                    <td style="width: 10%; text-align: center">${Plant.price}</td>
                    <td style="width: 40%"><img style="width: 30%; height: 200px; margin-left: 35%;" src="${Plant.imgPath}" class="product"/></td>
                    <td style="width: 10%; text-align: center">${status[Plant.status]}</td>
                    <td style="width: 10%; text-align: center">${Plant.cateName}</td>
                    <td style="width: 30%; text-align: center">${[Plant.description]}</td>
                </tr>-->
                <tr>
                    <td style="width: 10%; text-align: center"><jsp:getProperty name="Plant" property="id"></jsp:getProperty></td>
                    <td style="width: 10%; text-align: center"><jsp:getProperty name="Plant" property="name"></jsp:getProperty></td>
                    <td style="width: 10%; text-align: center"><jsp:getProperty name="Plant" property="price"></jsp:getProperty></td>
                    <td style="width: 40%; text-align: center"><img style="width: 30%; height: 200px; margin-left: 35%;" src="<jsp:getProperty name="Plant" property="imgPath"></jsp:getProperty>" class="product" /></td>
                    <td style="width: 10%; text-align: center"><jsp:getProperty name="Plant" property="status"></jsp:getProperty></td>
                    <td style="width: 10%; text-align: center"><jsp:getProperty name="Plant" property="cateName"></jsp:getProperty></td>
                    <td style="width: 30%; text-align: center"><jsp:getProperty name="Plant" property="description"></jsp:getProperty></td>
                </tr>
            </tbody>
        </table>
                
            <footer>
                <%@include file="footer.jsp" %>
            </footer>
    </body>
