package torshia.web.servlets;

import org.modelmapper.ModelMapper;
import torshia.domain.models.services.TaskServiceModel;
import torshia.domain.models.views.TaskHomeViewModel;
import torshia.service.ReportService;
import torshia.service.TaskService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    private ModelMapper modelMapper;
    private TaskService taskService;

    @Inject
    public HomeServlet(ModelMapper modelMapper, TaskService taskService) {
        this.modelMapper = modelMapper;
        this.taskService = taskService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TaskHomeViewModel> taskHomeVM = this.taskService.getTaskWithReport(false)
                .stream()
                .map(t -> {
                    TaskHomeViewModel taskVM = this.modelMapper
                            .map(t, TaskHomeViewModel.class);
                    taskVM.setLevel(this.calculateLevel(t));
                    return taskVM;
                })
                .collect(Collectors.toList());

        req.setAttribute("models", taskHomeVM);
        req.getRequestDispatcher("/view/home.jsp")
                .forward(req, resp);
    }

    private Long calculateLevel(TaskServiceModel task) {
        return task.getAffectedSectors().stream().distinct().count();
    }
}
