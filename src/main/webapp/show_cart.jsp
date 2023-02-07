<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show carters</title>
</head>
<body>
    <%@ page import="servlets.Cart" %>
    <% Cart cart = (Cart)session.getAttribute("cart"); %>
    <%= cart.getName() %>
    <%= cart.getQuantity() %>
</body>
</html>