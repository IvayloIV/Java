package panda.web.filters;

import panda.domain.enums.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {
        "/faces/view/detailsPackage",
        "/faces/view/home",
        "/faces/view/myReceipts",
        "/faces/view/receiptDetails",
})
public class GuestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if (req.getSession().getAttribute("username") == null) {
            resp.sendRedirect("/faces/view/index.xhtml");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}