<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="torshia.domain.enums.Role" %>
<%@ page import="torshia.domain.models.views.TaskHomeViewModel" %>
<%@ page import="java.util.List" %>
<html>
<c:import url="templates/head.jsp"/>
<body>
<div class="container-fluid">
    <header>
        <c:import url="templates/nav.jsp"/>
    </header>
    <main class="mt-3">
        <% List<TaskHomeViewModel> models = (List<TaskHomeViewModel>) request.getAttribute("models"); %>
        <h4 class="text-torshia text-center">Welcome,
            <% if (session.getAttribute("role") == Role.Admin) { %>Admin-<% } %><%= session.getAttribute("username") %>!
        </h4>
        <h4 class="text-torshia text-center">Have a nice tasking experience!</h4>
        <hr class="hr-2 bg-torshia">
        <div class="tasks-holder">
            <% for(int i = 0; i < models.size(); i++) { %>
                <% TaskHomeViewModel model = models.get(i); %>
                <% if (i % 5 == 0) { %><div class="tasks-row row mt-4 justify-content-start"><% } %>
                    <div class="task col mx-3 bg-torshia rounded py-3 col-2">
                        <h6 class="task-title text-white text-center my-3"><%= model.getTitle() %></h6>
                        <hr class="bg-white hr-2 w-75">
                        <h6 class="task-title text-white text-center my-4">Level: <%= model.getLevel() %></h6>
                        <hr class="bg-white hr-2 w-75">
                        <div class="follow-button-holder d-flex justify-content-between w-50 mx-auto mt-4">
                            <a href="/report/create?taskId=<%= model.getId() %>"><h6 class="text-center text-white">Report</h6></a>
                            <a href="/task/details?taskId=<%= model.getId() %>"><h6 class="text-center text-white">Details</h6></a>
                        </div>
                    </div>
                <% if (i % 5 == 4) { %></div><% } %>
            <% } %>
        </div>
    </main>
    <c:import url="templates/footer.jsp"/>
</div>
</body>
</html>
