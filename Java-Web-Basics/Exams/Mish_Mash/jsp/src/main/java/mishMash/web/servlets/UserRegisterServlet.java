package mishMash.web.servlets;

import mishMash.domain.models.bindings.RegisterUserBindingModel;
import mishMash.domain.models.services.UserServiceModel;
import mishMash.service.UserService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/register")
public class UserRegisterServlet extends HttpServlet {

    private ModelMapper modelMapper;
    private UserService userService;

    @Inject
    public UserRegisterServlet(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/register.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RegisterUserBindingModel model = (RegisterUserBindingModel) req.getAttribute("model");
        if (!model.getPassword().equals(model.getConfirmPassword())) {
            resp.sendRedirect("/user/register");
            return;
        }

        UserServiceModel userServiceModel = this.modelMapper
                .map(model, UserServiceModel.class);

        if (!this.userService.registerUser(userServiceModel)) {
            resp.sendRedirect("/user/register");
            return;
        }

        resp.sendRedirect("/user/login");
    }
}
