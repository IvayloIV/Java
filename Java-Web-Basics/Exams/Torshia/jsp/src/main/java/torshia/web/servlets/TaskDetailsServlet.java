package torshia.web.servlets;

import org.modelmapper.ModelMapper;
import torshia.domain.models.services.TaskServiceModel;
import torshia.domain.models.views.TaskDetailViewModel;
import torshia.service.TaskService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet("/task/details")
public class TaskDetailsServlet extends HttpServlet {

    private ModelMapper modelMapper;
    private TaskService taskService;

    @Inject
    public TaskDetailsServlet(ModelMapper modelMapper, TaskService taskService) {
        this.modelMapper = modelMapper;
        this.taskService = taskService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String taskId = req.getParameter("taskId");
        TaskServiceModel taskModel = this.taskService.getById(taskId);
        TaskDetailViewModel taskDetailVM = this.createTaskViewModel(taskModel);
        req.setAttribute("model", taskDetailVM);

        req.getRequestDispatcher("/view/taskDetails.jsp")
                .forward(req, resp);
    }

    private TaskDetailViewModel createTaskViewModel(TaskServiceModel taskModel) {
        TaskDetailViewModel taskVM = this.modelMapper
                .map(taskModel, TaskDetailViewModel.class);

        taskVM.setParticipants(String.join(", ", taskModel.getParticipants()));
        taskVM.setAffectedSectors(String.join(", ", taskModel.getAffectedSectors().stream()
                .map(Enum::name).collect(Collectors.toList())));
        taskVM.setLevel(this.calculateLevel(taskModel));
        return taskVM;
    }

    private Long calculateLevel(TaskServiceModel task) {
        return task.getAffectedSectors().stream().distinct().count();
    }
}
