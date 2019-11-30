package metubev3.web.servlets;

import metubev3.domain.models.bindings.UploadTubeBindingModel;
import metubev3.domain.models.services.TubeServiceModel;
import metubev3.service.TubeService;
import metubev3.utils.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/upload-tube")
public class UploadTubeServlet extends HttpServlet {
    private final TubeService tubeService;
    private final ModelMapper modelMapper;

    @Inject
    public UploadTubeServlet(TubeService tubeService, ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/upload-tube.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UploadTubeBindingModel model = (UploadTubeBindingModel) req.getAttribute("model");
        String username = (String) req.getSession().getAttribute("username");

        if (!this.tubeService.save(this.modelMapper.map(model, TubeServiceModel.class), username)) {
            req.getRequestDispatcher("/jsp/upload-tube.jsp")
                    .forward(req, resp);
            return;
        }

        resp.sendRedirect("/home");
    }
}
