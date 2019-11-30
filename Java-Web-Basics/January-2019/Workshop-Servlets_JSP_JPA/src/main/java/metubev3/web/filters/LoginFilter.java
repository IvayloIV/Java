package metubev3.web.filters;

import metubev3.domain.models.bindings.LoginBindingModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/login")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        if (req.getMethod().toUpperCase().equals("POST")) {
            LoginBindingModel model = new LoginBindingModel();
            model.setUsername(req.getParameter("username"));
            model.setPassword(req.getParameter("password"));

            req.setAttribute("model", model);
        }

        filterChain.doFilter(req, res);
    }
}
