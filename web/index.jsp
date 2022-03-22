<%-- 
    Document   : index
    Created on : Jan 10, 2022, 10:22:27 PM
    Author     : Trung Duong
--%>

<%@page import="duonght.dao.PlantDao"%>
<%@page import="duonght.dto.Plant"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Index Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="mycss.css" type="text/css" />
    </head>
    <body>
        <input id="MESSAGE" type="hidden" value="${requestScope.MESSAGE}" />
        <script>
            if (document.getElementById("MESSAGE").value !== "") {
                alert(document.getElementById("MESSAGE").value);
            }
        </script>
        <header>
            <%@include file="header.jsp" %>
        </header>

        <c:set var="keyword" value="${requestScope.txtsearch}" />
        <c:set var="searchby" value="${requestScope.searchby}" />
        <c:set var="list" value="${requestScope.RESULT}" />
        <c:if test="${empty list}">
            <h4>LIST IS EMPTY</h4>
        </c:if>
        <c:if test="${not empty list}">
            <table border="1" style="width: 100%">
                <thead>
                    <tr>
                        <th style="width: 5%">No.</th>
                        <th style="width: 5%">Product ID</th>
                        <th style="width: 10%">Product Name</th>
                        <th style="width: 10%">Price</th>
                        <th style="width: 40%">Image</th>
                        <th style="width: 10%">Status</th>
                        <th style="width: 10%">Category</th>
                        <th style="width: 30%">Action</th>
                    </tr>
                </thead>
                <tbody>
   
                    <c:forEach var="plant" items="${list}" varStatus="counter" >
                        <tr>
                            <td style="width: 5%; text-align: center">${counter.count}</td>
                            <td style="width: 5%; text-align: center">${plant.id}</td>
                            <td style="width: 10%; text-align: center">${plant.name}</td>
                            <td style="width: 10%; text-align: center">${plant.price}</td>
                            <td style="width: 40%"><img style="width: 30%; height: 200px; margin-left: 35%;" src="${plant.imgPath}" class="product"/></td>
                            <td style="width: 10%; text-align: center">${plant.status == 1 ? 'Available' : 'Out of Stock'} </td>
                            <td style="width: 10%; text-align: center">${plant.cateName}</td>
                            <td style="width: 30%; text-align: center">
                                <c:if test="${plant.status == 1}">
                                    <a href="mainController?btAction=addtocart&pid=${plant.id}">Add to Card</a>
                                </c:if>
                                <c:if test="${plant.status != 1}">
                                    <h4>INABLE</h4>
                                </c:if>
 
                            </td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </c:if>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
