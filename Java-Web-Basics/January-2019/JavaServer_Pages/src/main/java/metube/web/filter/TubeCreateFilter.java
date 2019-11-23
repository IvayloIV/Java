package metube.web.filter;

import metube.domain.model.binding.TubeCreateBindingModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/tubes/create")
public class TubeCreateFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        if (req.getMethod().toUpperCase().equals("POST")) {
            TubeCreateBindingModel tubeCreateBindingModel = new TubeCreateBindingModel();
            tubeCreateBindingModel.setName(req.getParameter("name"));
            tubeCreateBindingModel.setDescription(req.getParameter("description"));
            tubeCreateBindingModel.setYoutubeLink(req.getParameter("youtubeLink"));
            tubeCreateBindingModel.setUploader(req.getParameter("uploader"));

            req.setAttribute("tubeBindingModel", tubeCreateBindingModel);
        }

        filterChain.doFilter(req, res);
    }
}
