<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="metubev3.domain.models.views.TubeHomeViewModel" %>
<%@ page import="java.util.List" %>
<html>
<c:import url="templates/head.jsp" />
<body>
    <% List<TubeHomeViewModel> tubes = (List<TubeHomeViewModel>) request.getAttribute("tubes"); %>
    <div class="container-fluid">
        <c:import url="templates/nav.jsp" />
        <hr class="my-2"/>
        <div class="text-center mt-3">
            <h4 class="h4 text-info">Welcome, <%= request.getSession().getAttribute("username") %></h4>
        </div>
        <hr class="my-4">
        <div class="container tubes">
            <% for (TubeHomeViewModel tube: tubes) { %>
                <div class="tube">
                    <img src="https://img.youtube.com/vi/<%= tube.getYoutubeId() %>/0.jpg" alt="youtube-img">
                    <p><%= tube.getTitle() %></p>
                    <p class="author"><%= tube.getAuthor() %></p>
                </div>
            <% } %>
        </div>
        <hr class="my-3"/>
        <c:import url="templates/footer.jsp" />
    </div>
</body>
</html>
