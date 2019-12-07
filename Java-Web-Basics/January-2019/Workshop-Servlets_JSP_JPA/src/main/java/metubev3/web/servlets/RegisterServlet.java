package metubev3.web.servlets;

import metubev3.domain.models.services.UserServiceModel;
import metubev3.domain.models.bindings.RegisterBindingModel;
import metubev3.service.UserService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Inject
    public RegisterServlet(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/register.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RegisterBindingModel model = (RegisterBindingModel) req.getAttribute("model");

        if (!model.getPassword().equals(model.getConfirmPassword())) {
            req.getRequestDispatcher("/jsp/register.jsp")
                    .forward(req, resp);
            return;
        }

        UserServiceModel userServiceModel = this.modelMapper.map(model, UserServiceModel.class);
        if (!this.userService.registerUser(userServiceModel)) {
            req.getRequestDispatcher("/jsp/register.jsp")
                    .forward(req, resp);
            return;
        }

        resp.sendRedirect("/login");
    }
}
