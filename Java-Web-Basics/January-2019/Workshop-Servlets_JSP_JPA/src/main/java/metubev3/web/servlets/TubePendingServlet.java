package metubev3.web.servlets;

import metubev3.domain.enums.TubeStatus;
import metubev3.domain.models.views.TubePendingViewModel;
import metubev3.service.TubeService;
import metubev3.utils.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/admin/tube/pending")
public class TubePendingServlet extends HttpServlet {
    private final TubeService tubeService;
    private final ModelMapper modelMapper;

    @Inject
    public TubePendingServlet(TubeService tubeService, ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TubePendingViewModel> tubePendingViewModel = this.tubeService.getAllWithStatus(TubeStatus.Pending)
                .stream()
                .map(t -> this.modelMapper.map(t, TubePendingViewModel.class))
                .collect(Collectors.toList());

        req.setAttribute("tubes", tubePendingViewModel);
        req.getRequestDispatcher("/jsp/tube-pending.jsp")
                .forward(req, resp);
    }
}
