<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="templates/head.jsp" />
    <link rel="stylesheet" href="/styles/create-product.css">
</head>
<body>
    <main>
        <h2>Create Tube!</h2>
        <form action="/tubes/create" method="POST">
            <hr>
            <label>
                <span>Title</span>
                <input type="text" name="name">
            </label>
            <hr>
            <label>
                <span>Description</span>
                <textarea name="description" cols="24" rows="5"></textarea>
            </label>
            <hr>
            <label>
                <span>YouTube Link</span>
                <input type="text" name="youtubeLink">
            </label>
            <hr>
            <label>
                <span>Uploader</span>
                <input type="text" name="uploader">
            </label>
            <hr>
            <input type="submit" value="Create Tube" class="button-blue">
        </form>
        <a href="/">Back to Home.</a>
    </main>
    <c:import url="templates/footer.jsp" />
</body>
</html>
