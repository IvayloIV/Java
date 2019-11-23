package metube.web.servlet;

import metube.domain.model.view.TubeDetailsModelView;
import metube.service.TubeService;
import metube.util.ModelMapper;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet("/tubes/details")
public class TubeDetailsServlet extends HttpServlet {
    private final TubeService tubeService;
    private final ModelMapper modelMapper;

    @Inject
    public TubeDetailsServlet(TubeService tubeService, ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = URLDecoder.decode(req.getQueryString().split("=")[1], "UTF-8");
        try {
            TubeDetailsModelView tube = this.modelMapper
                    .map(this.tubeService.findByName(name), TubeDetailsModelView.class);
            req.setAttribute("tube", tube);
        } catch (NoResultException ex) {
            req.setAttribute("tube", null);
        }

        req.getRequestDispatcher("/views/details-tube.jsp")
                .forward(req, resp);
    }
}
