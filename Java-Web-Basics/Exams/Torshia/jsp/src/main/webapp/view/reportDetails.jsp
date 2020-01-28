<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="torshia.domain.models.views.ReportDetailsViewModel" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<html>
<c:import url="templates/head.jsp"/>
<body>
<div class="container-fluid">
    <header>
        <c:import url="templates/nav.jsp"/>
    </header>
    <main class="mt-3 mb-5">
        <% ReportDetailsViewModel report = (ReportDetailsViewModel) request.getAttribute("model"); %>
        <div class="task-head">
            <h1 class="text-center text-torshia">Report - <%= report.getId() %></h1>
        </div>
        <hr class="hr-2 bg-torshia"/>
        <div class="d-flex justify-content-between">
            <div class="task-main-data-holder w-75 mx-auto d-flex flex-column">
                <h3 class="text-center text-torshia">Task: <%= report.getTask().getTitle() %></h3>
                <h3 class="text-center text-torshia">Level: <%= report.getTask().getLevel() %></h3>
                <h3 class="text-center text-torshia">Status: <%= report.getStatus() %></h3>
            </div>
            <div class="task-main-data-holder w-75 mx-auto d-flex flex-column">
                <% DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); %>
                <h3 class="text-center text-torshia">Due Date: <%= df.format(report.getTask().getDueDate()) %></h3>
                <h3 class="text-center text-torshia">Reported On: <%= df.format(report.getReportedOn()) %></h3>
                <h3 class="text-center text-torshia">Reporter: <%= report.getReporterUsername() %></h3>
            </div>
        </div>
        <hr class="hr-2 bg-torshia"/>
        <div class="task-secondary-data-holder row d-flex justify-content-between">
            <div class="task-participants-holder col-md-5">
                <h3 class="text-center text-torshia">Participants</h3>
                <div class="task-tags-holder w-75 mx-auto">
                    <h6 class="text-center text-torshia"><%= report.getTask().getParticipants() %></h6>
                </div>
            </div>
            <div class="task-participants-holder col-md-5">
                <h3 class="text-center text-torshia">Affected Sectors</h3>
                <div class="task-tags-holder w-75 mx-auto">
                    <h6 class="text-center text-torshia"><%= report.getTask().getAffectedSectors() %></h6>
                </div>
            </div>
        </div>
        <hr class="hr-2 bg-torshia"/>
        <h3 class="text-center text-torshia">Task Description</h3>
        <div class="product-description-holder">
            <p class="text-center text-torshia mt-4"><%= report.getTask().getDescription() %></p>
        </div>
        <hr class="hr-2 bg-torshia"/>
        <div class="button-holder d-flex w-25 mx-auto justify-content-center">
            <a href="/report/all">
                <button class="btn bg-torshia text-white">Back</button>
            </a>
        </div>
    </main>
    <c:import url="templates/footer.jsp"/>
</div>
</body>
</html>
