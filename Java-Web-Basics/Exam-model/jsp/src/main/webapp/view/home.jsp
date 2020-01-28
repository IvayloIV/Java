<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<c:import url="templates/head.jsp"/>
<body>
<div class="container-fluid">
    <header>
        <c:import url="templates/nav.jsp"/>
    </header>
    <main>
        <div class="jumbotron mt-3 bg-mishmash">
            <h3>Welcome, <%= session.getAttribute("username") %>!</h3>
        </div>
    </main>
    <c:import url="templates/footer.jsp"/>
</div>
</body>
</html>
