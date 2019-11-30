package metubev3.web.servlets;

import metubev3.domain.models.views.TubeDetailsViewModel;
import metubev3.service.TubeService;
import metubev3.utils.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tube/details")
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
        String tubeId = req.getQueryString().split("=")[1];

        this.tubeService.increaseViews(tubeId);
        TubeDetailsViewModel tube = this.modelMapper
                .map(this.tubeService.getById(tubeId), TubeDetailsViewModel.class);

        req.setAttribute("tube", tube);
        req.getRequestDispatcher("/jsp/tube-details.jsp")
                .forward(req, resp);
    }
}
