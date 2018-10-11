<%--
  Created by IntelliJ IDEA.
  User: tom
  Date: 11.10.18
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<form method="get" action="/cart/addtocartdao">
    <p>
        Produkt
        <select name="id">
            <c:forEach items="${list}" var="item">
                <option value="${item.id}">${item.name}</option>
            </c:forEach>
        </select>
    </p>
    <p>
        Ilość
        <input type="number" name="quantity">
    </p>
    <p>
        <input type="submit" value="Wyślij">
    </p>
</form>

</body>
</html>