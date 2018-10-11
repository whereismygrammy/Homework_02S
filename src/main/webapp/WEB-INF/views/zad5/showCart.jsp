<%--
  Created by IntelliJ IDEA.
  User: tom
  Date: 10.10.18
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<html>
<head>
    <title>CART!!!</title>
</head>
<body>


<table border="1">
    <tr>
        <th>Produkt</th>
        <th>Ilość</th>
        <th>Zmień ilość</th>
        <th>Usuń</th>
    </tr>

    <c:forEach items="${items}" var="item">
        <tr>
            <td>${item.product.name}</td>
            <td>${item.quantity}</td>
            <td><a href="/cart/moreProduct?id=${item.product.id}">Dodaj</a>   <a href="/cart/lessProduct?id=${item.product.id}">Odejmij</a></td>
            <td><a href="/cart/delProduct?id=${item.product.id}">Usun</a></td>
        </tr>
    </c:forEach>
</table>

<p>
    </br>
    Podsumowanie
<table>

    <tr>
        <td>W koszyku jest ${cartsize} pozycji.</td>
    </tr>

    <tr>

        <td>W koszyku jest ${cartquant} produktów.</td>
    </tr>

    <tr>
        <td>Wartość koszyka to: ${cartprice}.</td>
    </tr>

</table>
</p>

</body>
</html>
