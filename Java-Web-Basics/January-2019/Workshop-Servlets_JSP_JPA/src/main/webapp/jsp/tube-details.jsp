<%@ page import="metubev3.domain.models.views.TubeDetailsViewModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="templates/head.jsp" />
</head>
<body>
    <% TubeDetailsViewModel tube = (TubeDetailsViewModel) request.getAttribute("tube"); %>
    <div class="container-fluid">
        <c:import url="templates/nav.jsp" />
        <hr class="my-2">
        <div class="container-fluid">
            <h2 class="text-center"><%= tube.getTitle() %></h2>
            <div class="row">
                <div class="col-md-6 my-5">
                    <div class="embed-responsive embed-responsive-16by9">
                        <iframe class="embed-responsive-item" src="https://www.youtube.com/embed/<%= tube.getYoutubeId() %>" allowfullscreen frameborder="0"></iframe>
                    </div>
                </div>
                <div class="col-md-6 my-5">
                    <h1 class="text-center text-info"><%= tube.getAuthor() %></h1>
                    <h3 class="text-center text-info"><%= tube.getViews() %> Views</h3>
                    <div class="h5 my-5 text-center"><%= tube.getDescription() %></div>
                </div>
            </div>
        </div>
        <hr class="my-3"/>
        <c:import url="templates/footer.jsp" />
    </div>
</body>
</html>
