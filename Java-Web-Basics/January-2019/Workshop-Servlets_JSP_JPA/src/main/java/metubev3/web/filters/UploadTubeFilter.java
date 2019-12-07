package metubev3.web.filters;

import metubev3.domain.enums.TubeStatus;
import metubev3.domain.models.bindings.UploadTubeBindingModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/tube/upload")
public class UploadTubeFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        if (req.getMethod().toUpperCase().equals("POST")) {
            UploadTubeBindingModel model = new UploadTubeBindingModel();
            model.setTitle(req.getParameter("title"));
            model.setAuthor(req.getParameter("author"));
            model.setYoutubeLink(req.getParameter("youtube-link"));
            model.setDescription(req.getParameter("description"));
            model.setStatus(TubeStatus.Pending);

            req.setAttribute("model", model);
        }

        filterChain.doFilter(req, res);
    }
}
