<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="metube.domain.model.view.TubeAllModelView" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <c:import url="templates/head.jsp" />
    <link rel="stylesheet" href="/styles/all-tubes.css">
</head>
<body>
    <% List<TubeAllModelView> tubes = (List<TubeAllModelView>) request.getAttribute("tubes"); %>
    <main>
        <h2>All Tubes</h2>
        <hr>
        <h3>Check out tubes below.</h3>
        <hr>
            <% if (tubes.size() == 0) { %>
                <p>No tubes - <a href="/tubes/create">Create some</a>!</p>
            <% } else { %>
                <ul>
                    <% for (TubeAllModelView tube : tubes) { %>
                        <li><a href="/tubes/details?title=<%= tube.getName() %>"><%= tube.getName() %></a></li>
                    <% } %>
                </ul>
            <% } %>
        <hr>
        <a href="/">Back to Home.</a>
    </main>
    <c:import url="templates/footer.jsp" />
</body>
</html>
