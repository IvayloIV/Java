<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="metube.domain.model.view.TubeDetailsModelView" %>
<html>
<head>
    <c:import url="templates/head.jsp" />
    <link rel="stylesheet" href="/styles/details-product.css">
</head>
<body>
    <% TubeDetailsModelView tube = (TubeDetailsModelView) request.getAttribute("tube"); %>
    <main>
        <% if (tube == null) { %>
            <p>Invalid tube!</p>
        <% } else { %>
            <h2><%= tube.getName() %></h2>
            <hr>
            <h4><%= tube.getDescription() %></h4>
            <hr>
            <p>
                <a href="<%= tube.getYoutubeLink() %>" target="_blank">Link to Video.</a>
                <span><%= tube.getUploader() %></span>
            </p>
        <% } %>
        <hr>
        <a href="/">Back to Home.</a>
    </main>
    <c:import url="templates/footer.jsp" />
</body>
</html>
