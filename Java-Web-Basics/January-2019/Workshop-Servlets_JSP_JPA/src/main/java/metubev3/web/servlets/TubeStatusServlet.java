package metubev3.web.servlets;

import metubev3.domain.enums.TubeStatus;
import metubev3.service.TubeService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/tube/status")
public class TubeStatusServlet extends HttpServlet {
    private final TubeService tubeService;

    @Inject
    public TubeStatusServlet(TubeService tubeService) {
        this.tubeService = tubeService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] queries = req.getQueryString().split("&");
        String tubeId = queries[0].split("=")[1];
        TubeStatus status = TubeStatus.valueOf(queries[1].split("=")[1]);

        this.tubeService.changeStatus(tubeId, status);
        resp.sendRedirect("/admin/tube/pending");
    }
}
