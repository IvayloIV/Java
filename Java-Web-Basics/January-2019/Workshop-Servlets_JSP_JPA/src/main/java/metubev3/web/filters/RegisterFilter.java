package metubev3.web.filters;

import metubev3.domain.models.bindings.RegisterBindingModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/register")
public class RegisterFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        if (req.getMethod().toUpperCase().equals("POST")) {
            RegisterBindingModel model = new RegisterBindingModel();
            model.setUsername(req.getParameter("username"));
            model.setPassword(req.getParameter("password"));
            model.setConfirmPassword(req.getParameter("confirmPassword"));
            model.setEmail(req.getParameter("email"));

            req.setAttribute("model", model);
        }

        filterChain.doFilter(req, res);
    }
}
