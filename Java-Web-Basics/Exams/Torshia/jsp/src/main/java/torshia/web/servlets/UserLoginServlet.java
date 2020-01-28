package torshia.web.servlets;

import torshia.domain.models.bindings.LoginUserBindingModel;
import torshia.domain.models.services.UserServiceModel;
import torshia.service.UserService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/login")
public class UserLoginServlet extends HttpServlet {

    private ModelMapper modelMapper;
    private UserService userService;

    @Inject
    public UserLoginServlet(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/login.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginUserBindingModel model = (LoginUserBindingModel) req.getAttribute("model");

        UserServiceModel userServiceModel = this.modelMapper
                .map(model, UserServiceModel.class);
        UserServiceModel user = this.userService.loginUser(userServiceModel);
        if (user == null) {
            resp.sendRedirect("/user/login");
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute("id", user.getId());
        session.setAttribute("username", user.getUsername());
        session.setAttribute("role", user.getRole());
        resp.sendRedirect("/home");
    }
}
