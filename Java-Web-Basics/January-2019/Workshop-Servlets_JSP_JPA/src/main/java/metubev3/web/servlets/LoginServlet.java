package metubev3.web.servlets;

import metubev3.domain.models.bindings.LoginBindingModel;
import metubev3.domain.models.services.UserServiceModel;
import metubev3.service.UserService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Inject
    public LoginServlet(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/login.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginBindingModel model = (LoginBindingModel) req.getAttribute("model");

        UserServiceModel userServiceModel = this.userService.loginUser(this.modelMapper.map(model, UserServiceModel.class));
        if (userServiceModel == null) {
            req.getRequestDispatcher("/jsp/login.jsp")
                    .forward(req, resp);
            return;
        }

        req.getSession().setAttribute("username", userServiceModel.getUsername());
        req.getSession().setAttribute("role", userServiceModel.getRole());
        resp.sendRedirect("/home");
    }
}
