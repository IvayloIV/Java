<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<c:import url="templates/head.jsp"/>
<body>
    <div class="container-fluid">
        <header>
            <c:import url="templates/nav.jsp"/>
        </header>
        <main class="mt-3">
            <h1 class="text-center text-mishmash">Register</h1>
            <hr class="bg-mishmash w-50 hr-2"/>
            <form class="mx-auto w-50" method="post" action="/user/register">
                <div class="form-group">
                    <label for="username" class="text-mishmash">Username</label>
                    <input type="text" class="form-control" id="username" placeholder="Username..." name="username">
                </div>
                <div class="form-group">
                    <label for="password" class="text-mishmash">Password</label>
                    <input type="password" class="form-control" id="password" placeholder="Password..." name="password">
                </div>
                <div class="form-group">
                    <label for="confirmPassword" class="text-mishmash">Confirm Password</label>
                    <input type="password" class="form-control" id="confirmPassword" placeholder="Confirm Password..." name="confirmPassword">
                </div>
                <div class="form-group">
                    <label for="email" class="text-mishmash">Email</label>
                    <input type="email" class="form-control" id="email" placeholder="Email..." name="email">
                </div>
                <div class="button-holder mt-4 d-flex justify-content-center">
                    <button type="submit" class="btn bg-mishmash text-white">Register</button>
                </div>
            </form>
        </main>
        <c:import url="templates/footer.jsp"/>
    </div>
</body>
</html>
