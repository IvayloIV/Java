package torshia.web.filters;

import torshia.domain.enums.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {
    "/report/details",
    "/report/all",
    "/task/create"
})
public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if (req.getSession().getAttribute("role") != Role.Admin) {
            resp.sendRedirect("/");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
