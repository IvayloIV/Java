package metubev3.web.servlets;

import metubev3.domain.models.views.TubeProfileViewModel;
import metubev3.domain.models.views.UserProfileViewModel;
import metubev3.service.TubeService;
import metubev3.service.UserService;
import metubev3.utils.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private final TubeService tubeService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Inject
    public ProfileServlet(TubeService tubeService, UserService userService, ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String) req.getSession().getAttribute("username");
        List<TubeProfileViewModel> tubes = this.tubeService.getByUsername(username)
                .stream()
                .map(t -> this.modelMapper.map(t, TubeProfileViewModel.class))
                .collect(Collectors.toList());

        UserProfileViewModel user = this.modelMapper
                .map(this.userService.findByUsername(username), UserProfileViewModel.class);

        req.setAttribute("tubes", tubes);
        req.setAttribute("user", user);
        req.getRequestDispatcher("/jsp/profile.jsp")
                .forward(req, resp);
    }
}
