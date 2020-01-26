package mishMash.web.filters;

import mishMash.domain.models.bindings.RegisterUserBindingModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/user/register")
public class UserRegisterFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if (req.getMethod().toUpperCase().equals("POST")) {
            RegisterUserBindingModel model = new RegisterUserBindingModel();
            model.setUsername(req.getParameter("username"));
            model.setPassword(req.getParameter("password"));
            model.setConfirmPassword(req.getParameter("confirmPassword"));
            model.setEmail(req.getParameter("email"));

            req.setAttribute("model", model);
        }

        filterChain.doFilter(req, resp);
    }
}
