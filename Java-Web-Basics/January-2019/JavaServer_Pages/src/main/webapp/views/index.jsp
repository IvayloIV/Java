<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="templates/head.jsp" />
    <link rel="stylesheet" href="/styles/index.css">
</head>
<body>
    <main>
        <h1>Welcome to MeTube!</h1>
        <hr>
        <h3>Cool app in beta version</h3>
        <hr>
        <p>
            <a href="/tubes/create"><button class="button-blue">Create tube</button></a>
            <a href="/tubes/all"><button class="button-blue">All Tubes</button></a>
        </p>
    </main>
    <c:import url="templates/footer.jsp" />
</body>
</html>
