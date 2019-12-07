<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="metubev3.domain.models.views.TubeProfileViewModel" %>
<%@ page import="metubev3.domain.models.views.UserProfileViewModel" %>
<html>
    <c:import url="templates/head.jsp" />
<body>
    <% UserProfileViewModel user = (UserProfileViewModel) request.getAttribute("user"); %>
    <% List<TubeProfileViewModel> tubes = user.getTubes(); %>
    <div class="container-fluid">
        <c:import url="templates/nav.jsp" />
        <hr class="my-2"/>
        <div class="text-center mt-3">
            <h4 class="text-info text-center">@<%= user.getUsername() %></h4>
            <h4 class="text-info text-center">(<%= user.getEmail() %>)</h4>
        </div>
        <hr>
        <div class="container-fluid">
            <div class="row d-flex flex-column">
                <table class="table table-hover table-dark">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Title</th>
                            <th scope="col">Author</th>
                            <th scope="col">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for(int i = 0; i < tubes.size(); i++) { %>
                            <tr>
                                <td><%= i + 1 %></td>
                                <td><%= tubes.get(i).getTitle() %></td>
                                <td><%= tubes.get(i).getAuthor() %></td>
                                <td><a href="/tube/details/<%= tubes.get(i).getId() %>">Details</a></td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
        <hr class="my-3"/>
        <c:import url="templates/footer.jsp" />
    </div>
</body>
</html>
