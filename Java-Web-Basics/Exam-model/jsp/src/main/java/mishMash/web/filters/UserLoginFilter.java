package mishMash.web.filters;

import mishMash.domain.models.bindings.LoginUserBindingModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/user/login")
public class UserLoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if (req.getMethod().toUpperCase().equals("POST")) {
            LoginUserBindingModel model = new LoginUserBindingModel();
            model.setUsername(req.getParameter("username"));
            model.setPassword(req.getParameter("password"));

            req.setAttribute("model", model);
        }

        filterChain.doFilter(req, resp);
    }
}
