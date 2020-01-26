package mishMash.web.filters;

import mishMash.domain.models.bindings.ChannelCreateBindingModel;
import mishMash.domain.models.bindings.LoginUserBindingModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/channel/create")
public class ChannelCreateFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if (req.getMethod().toUpperCase().equals("POST")) {
            ChannelCreateBindingModel model = new ChannelCreateBindingModel();
            model.setName(req.getParameter("name"));
            model.setDescription(req.getParameter("description"));
            model.setTags(req.getParameter("tags"));
            model.setType(req.getParameter("inlineRadioOptions"));

            req.setAttribute("model", model);
        }

        filterChain.doFilter(req, resp);
    }
}
