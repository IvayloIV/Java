<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="mishMash.domain.models.views.MyChannelViewModel" %>
<html>
<c:import url="templates/head.jsp"/>
<body>
<div class="container-fluid">
    <% List<MyChannelViewModel> channels = (List<MyChannelViewModel>) request.getAttribute("channels"); %>
    <header>
        <c:import url="templates/nav.jsp"/>
    </header>
    <main class="mt-3 mb-5">
        <h1 class="text-center text-mishmash">Following</h1>
        <hr class="hr-2 bg-mishmash"/>
        <table class="table w-75 mx-auto table-hover">
            <thead>
            <tr class="row">
                <th class="col-md-1">#</th>
                <th class="col-md-5">Channel</th>
                <th class="col-md-1">Type</th>
                <th class="col-md-2">Followers</th>
                <th class="col-md-2">Actions</th>
            </tr>
            </thead>
            <tbody>
            <% for (int i = 0; i < channels.size(); i++) { %>
                <% MyChannelViewModel channel = channels.get(i); %>
                <tr class="row">
                    <th class="col-md-1"><%= i + 1 %></th>
                    <td class="col-md-5"><%= channel.getName() %></td>
                    <td class="col-md-1"><%= channel.getType() %></td>
                    <td class="col-md-2"><%= channel.getFollowers().size() %></td>
                    <td class="col-md-2">
                        <div class="button-holder d-flex justify-content-between">
                            <a href="/channel/unfollow?channelId=<%= channel.getId() %>">
                                <button class="btn bg-mishmash text-white">Unfollow</button>
                            </a>
                            <a href="/channel/details?channelId=<%= channel.getId() %>">
                                <button class="btn bg-mishmash text-white">Details</button>
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
