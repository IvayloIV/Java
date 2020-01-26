<%@ page import="mishMash.domain.models.views.ChannelDetailsViewModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<c:import url="templates/head.jsp"/>
<body>
<div class="container-fluid">
    <% ChannelDetailsViewModel channel = (ChannelDetailsViewModel) request.getAttribute("channel"); %>
    <header>
        <c:import url="templates/nav.jsp"/>
    </header>
    <main class="mt-3 mb-5">
        <hr class="hr-2 bg-mishmash"/>
        <div class="channel-main-data-holder w-75 mx-auto d-flex justify-content-between">
            <h3 class="text-center text-mishmash">Channel: <%= channel.getName() %></h3>
            <h3 class="text-center text-mishmash">Type: <%= channel.getType() %></h3>
            <h3 class="text-center text-mishmash">Followers: <%= channel.getFollowers().size() %></h3>
        </div>
        <hr class="hr-2 bg-mishmash"/>
        <h3 class="text-center text-mishmash">Tags</h3>
        <div class="channel-tags-holder w-75 mx-auto">
            <h6 class="text-center text-mishmash"><%= channel.getTags() %></h6>
        </div>
        <hr class="hr-2 bg-mishmash"/>
        <h3 class="text-center text-mishmash">Description</h3>
        <div class="product-description-holder">
            <p class="text-center text-mishmash mt-4">
                <%= channel.getDescription() %>
            </p>
        </div>
        <hr class="hr-2 bg-mishmash"/>
        <div class="button-holder d-flex justify-content-center">
            <a href="/home">
                <button class="btn bg-mishmash text-white">Back</button>
            </a>
        </div>
    </main>
    <c:import url="templates/footer.jsp"/>
</div>
</body>
</html>
