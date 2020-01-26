<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="mishMash.domain.models.views.ChannelHomeViewModel" %>
<%@ page import="mishMash.domain.enums.Role" %>
<html>
<c:import url="templates/head.jsp"/>
<body>
<div class="container-fluid">
    <% List<ChannelHomeViewModel> followedChannels = (List<ChannelHomeViewModel>) request.getAttribute("followedChannels"); %>
    <% List<ChannelHomeViewModel> suggestChannels = (List<ChannelHomeViewModel>) request.getAttribute("suggestChannels"); %>
    <% List<ChannelHomeViewModel> otherChannels = (List<ChannelHomeViewModel>) request.getAttribute("otherChannels"); %>
    <header>
        <c:import url="templates/nav.jsp"/>
    </header>
    <main class="mt-3">
        <h4 class="text-mishmash text-center">Welcome,
            <% if (session.getAttribute("role") == Role.Admin) { %>
                Admin-<% } %><%= session.getAttribute("username") %>!</h4>
        <h5 class="text-mishmash">Your channels</h5>
        <hr class="hr-2 bg-mishmash">
        <div class="followed-channels-holder">
            <% for(int i = 0; i < followedChannels.size(); i++) { %>
            <% if (i % 5 == 0) { %><div class="channels-row row mt-4 justify-content-start "><% } %>
            <% ChannelHomeViewModel channel = followedChannels.get(i); %>
            <div class="channel col mx-3 bg-mishmash rounded py-3 col-2">
                <h6 class="channel-name text-white text-center"><%= channel.getName() %></h6>
                <hr class="bg-white hr-2 w-75">
                <h6 class="channel-name text-white text-center"><%= channel.getDescription() %></h6>
                <hr class="bg-white hr-2 w-75">
                <h6 class="channel-name text-white text-center"><%= channel.getFollowers().size() %> Following</h6>
                <hr class="bg-white hr-2 w-75">
                <div class="follow-button-holder d-flex justify-content-center">
                    <h6 class="text-center text-white">Following</h6>
                </div>
            </div>
            <% if (i % 5 == 4) { %></div><% } %>
            <% } %>
        </div>
        <h5 class="mt-4 text-mishmash">Suggested</h5>
        <hr class="bg-mishmash hr-2">
        <div class="suggested-channels-holder">
            <div class="followed-channels-holder">
                <% for(int i = 0; i < suggestChannels.size(); i++) { %>
                <% if (i % 5 == 0) { %><div class="channels-row row mt-4 justify-content-start "><% } %>
                <% ChannelHomeViewModel channel = suggestChannels.get(i); %>
                <div class="channel col mx-3 bg-mishmash rounded py-3 col-2">
                    <h6 class="channel-name text-white text-center"><%= channel.getName() %></h6>
                    <hr class="bg-white hr-2 w-75">
                    <h6 class="channel-name text-white text-center"><%= channel.getDescription() %></h6>
                    <hr class="bg-white hr-2 w-75">
                    <h6 class="channel-name text-white text-center"><%= channel.getFollowers().size() %> Following</h6>
                    <hr class="bg-white hr-2 w-75">
                    <div class="follow-button-holder d-flex justify-content-between w-50 mx-auto">
                        <a href="/channel/follow?channelId=<%= channel.getId() %>"><h6 class="text-center text-white">Follow</h6></a>
                        <a href="/channel/details?channelId=<%= channel.getId() %>"><h6 class="text-center text-white ml-3">Details</h6></a>
                    </div>
                </div>
                <% if (i % 5 == 4) { %></div><% } %>
                <% } %>
            </div>
        </div>
        <h5 class="mt-4 text-mishmash">See Other</h5>
        <hr class="mt-4 bg-mishmash hr-2">
        <div class="other-channels-holder mb-5">
            <% for(int i = 0; i < otherChannels.size(); i++) { %>
                <% if (i % 5 == 0) { %><div class="channels-row row mt-4 justify-content-start "><% } %>
                <% ChannelHomeViewModel channel = otherChannels.get(i); %>
                <div class="channel col mx-3 bg-mishmash rounded py-3 col-2">
                    <h6 class="channel-name text-white text-center"><%= channel.getName() %></h6>
                    <hr class="bg-white hr-2 w-75">
                    <h6 class="channel-name text-white text-center"><%= channel.getDescription() %></h6>
                    <hr class="bg-white hr-2 w-75">
                    <h6 class="channel-name text-white text-center"><%= channel.getFollowers().size() %> Following</h6>
                    <hr class="bg-white hr-2 w-75">
                    <div class="follow-button-holder d-flex justify-content-between w-50 mx-auto">
                        <a href="/channel/follow?channelId=<%= channel.getId() %>"><h6 class="text-center text-white">Follow</h6></a>
                        <a href="/channel/details?channelId=<%= channel.getId() %>"><h6 class="text-center text-white ml-3">Details</h6></a>
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
