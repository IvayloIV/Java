package metubev3.web.servlets;

import metubev3.domain.models.views.TubeProfileViewModel;
import metubev3.domain.models.views.UserProfileViewModel;
import metubev3.service.TubeService;
import metubev3.service.UserService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Inject
    public ProfileServlet(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String) req.getSession().getAttribute("username");

        UserProfileViewModel user = this.modelMapper
                .map(this.userService.findByUsername(username), UserProfileViewModel.class);

        req.setAttribute("user", user);
        req.getRequestDispatcher("/jsp/profile.jsp")
                .forward(req, resp);
    }
}
