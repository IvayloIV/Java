package torshia.web.servlets;

import org.modelmapper.ModelMapper;
import torshia.service.ReportService;
import torshia.service.TaskService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/report/create")
public class CreateReportServlet extends HttpServlet {

    private TaskService taskService;
    private ReportService reportService;

    @Inject
    public CreateReportServlet(TaskService taskService, ReportService reportService) {
        this.taskService = taskService;
        this.reportService = reportService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String taskId = req.getParameter("taskId");
        String userId = (String) req.getSession().getAttribute("id");

        this.reportService.createReport(userId, taskId);
        this.taskService.updateReport(taskId, true);
        resp.sendRedirect("/");
    }
}
