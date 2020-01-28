package torshia.web.servlets;

import org.modelmapper.ModelMapper;
import torshia.domain.models.services.TaskServiceModel;
import torshia.domain.models.views.TaskReportViewModel;
import torshia.service.ReportService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/report/all")
public class ReportAllServlet extends HttpServlet {

    private ModelMapper modelMapper;
    private ReportService reportService;

    @Inject
    public ReportAllServlet(ModelMapper modelMapper, ReportService reportService) {
        this.modelMapper = modelMapper;
        this.reportService = reportService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TaskReportViewModel> taskReportsVM = this.reportService.getAll()
                .stream()
                .map(r -> {
                    TaskReportViewModel taskVM = this.modelMapper
                            .map(r, TaskReportViewModel.class);
                    taskVM.setLevel(this.calculateLevel(r.getTask()));
                    return taskVM;
                })
                .collect(Collectors.toList());

        req.setAttribute("models", taskReportsVM);
        req.getRequestDispatcher("/view/reportAll.jsp")
                .forward(req, resp);
    }
    private Long calculateLevel(TaskServiceModel task) {
        return task.getAffectedSectors().stream().distinct().count();
    }

}
