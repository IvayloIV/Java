<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="torshia.domain.enums.Role" %>
<html>
<nav class="navbar navbar-expand-lg navbar-dark bg-torshia">
    <a class="navbar-brand nav-link-white" href="/">TORSHIA</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse d-flex justify-content-between" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link nav-link-white active" href="/">Home</a>
            </li>
            <% if (session.getAttribute("id") == null) { %>
            <li class="nav-item">
                <a class="nav-link nav-link-white active" href="/user/login">Login</a>
            </li>
            <li class="nav-item">
                <a class="nav-link nav-link-white active" href="/user/register">Register</a>
            </li>
            <% } else { %>
                <% if (session.getAttribute("role") == Role.Admin) { %>
                <li class="nav-item">
                    <a class="nav-link nav-link-white active" href="/task/create">Create Task</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link nav-link-white active" href="/report/all">Reports</a>
                </li>
                <% } %>
            <li class="nav-item">
                <a class="nav-link nav-link-white active" href="/logout">Logout</a>
            </li>
            <% } %>
        </ul>
    </div>
</nav>
</html>
