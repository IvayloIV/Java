package metube.web.servlet;

import metube.domain.model.view.TubeAllModelView;
import metube.service.TubeService;
import metube.util.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/tubes/all")
public class TubeAllServlet extends HttpServlet {
    private final TubeService tubeService;
    private final ModelMapper modelMapper;

    @Inject
    public TubeAllServlet(TubeService tubeService, ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TubeAllModelView> tubes = this.tubeService.findAll().stream()
                .map(t -> this.modelMapper.map(t, TubeAllModelView.class))
                .collect(Collectors.toList());

        req.setAttribute("tubes", tubes);
        req.getRequestDispatcher("/views/all-tubes.jsp")
                .forward(req, resp);
    }
}
