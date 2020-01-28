<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="torshia.domain.models.views.TaskReportViewModel" %>
<%@ page import="java.util.List" %>
<html>
<c:import url="templates/head.jsp"/>
<body>
<div class="container-fluid">
    <header>
        <c:import url="templates/nav.jsp"/>
    </header>
    <main class="mt-3 mb-5">
        <% List<TaskReportViewModel> models = (List<TaskReportViewModel>) request.getAttribute("models"); %>
        <h1 class="text-center text-torshia">Task Reports</h1>
        <hr class="hr-2 bg-torshia"/>
        <table class="table w-75 mx-auto table-hover">
            <thead>
            <tr class="row">
                <th class="col-md-1">#</th>
                <th class="col-md-5">Task</th>
                <th class="col-md-1">Level</th>
                <th class="col-md-2">Status</th>
                <th class="col-md-2">Actions</th>
            </tr>
            </thead>
            <tbody>
            <% for(int i = 0; i < models.size(); i++) { %>
            <% TaskReportViewModel task = models.get(i); %>
            <tr class="row">
                <th class="col-md-1"><%= i + 1 %></th>
                <td class="col-md-5"><%= task.getTaskTitle() %></td>
                <td class="col-md-1"><%= task.getLevel() %></td>
                <td class="col-md-2"><%= task.getReportStatus() %></td>
                <td class="col-md-2">
                    <div class="button-holder d-flex justify-content-between">
                        <a href="/report/details?taskId=<%= task.getTaskId() %>">
                            <button class="btn bg-torshia text-white">Details</button>
                        </a>
                    </div>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </main>
    <c:import url="templates/footer.jsp"/>
</div>
</body>
</html>
