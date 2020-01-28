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
            <div class="jumbotron mt-3 bg-torshia">
                <h1 class="text-white">Welcome to TORSHIA Task Management.</h1>
                <hr class="bg-white hr-2"/>
                <h3 class="text-white"><a href="/user/login">Login</a> if you have an account.</h3>
                <h3 class="text-white"><a href="/user/register">Register</a> if you don't.</h3>
            </div>
        </main>
        <c:import url="templates/footer.jsp"/>
    </div>
</body>
</html>
