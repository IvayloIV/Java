package torshia.web.servlets;

import org.modelmapper.ModelMapper;
import torshia.domain.models.services.TaskServiceModel;
import torshia.domain.models.views.ReportDetailsViewModel;
import torshia.service.TaskService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet("/report/details")
public class ReportDetailsServlet extends HttpServlet {

    private ModelMapper modelMapper;
    private TaskService taskService;

    @Inject
    public ReportDetailsServlet(ModelMapper modelMapper, TaskService taskService) {
        this.modelMapper = modelMapper;
        this.taskService = taskService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String taskId = req.getParameter("taskId");
        TaskServiceModel taskModel = this.taskService.getById(taskId);
        ReportDetailsViewModel reportDetailsViewModel = this.createReportDetailsView(taskModel);
        req.setAttribute("model", reportDetailsViewModel);
        req.getRequestDispatcher("/view/reportDetails.jsp")
                .forward(req, resp);
    }

    private ReportDetailsViewModel createReportDetailsView(TaskServiceModel taskModel) {
        ReportDetailsViewModel reportVM = this.modelMapper.map(taskModel.getReport(), ReportDetailsViewModel.class);

        reportVM.getTask().setAffectedSectors(String.join(", ",
                taskModel.getAffectedSectors().stream().map(Enum::toString).collect(Collectors.toList())));
        reportVM.getTask().setParticipants(String.join(", ",
                taskModel.getParticipants()));
        reportVM.getTask().setLevel(this.calculateLevel(taskModel));

        return reportVM;
    }

    private Long calculateLevel(TaskServiceModel task) {
        return task.getAffectedSectors().stream().distinct().count();
    }
}
