package torshia.web.servlets;

import org.modelmapper.ModelMapper;
import torshia.domain.models.bindings.TaskCreateBindingModel;
import torshia.domain.models.services.TaskServiceModel;
import torshia.service.TaskService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

@WebServlet("/task/create")
public class CreateTaskServlet extends HttpServlet {

    private ModelMapper modelMapper;
    private TaskService taskService;

    @Inject
    public CreateTaskServlet(ModelMapper modelMapper, TaskService taskService) {
        this.modelMapper = modelMapper;
        this.taskService = taskService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/createTask.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TaskCreateBindingModel model = (TaskCreateBindingModel) req.getAttribute("model");
        TaskServiceModel task = this.modelMapper.map(model, TaskServiceModel.class);
        task.setParticipants(Arrays.stream(model.getParticipants()
                .split(",\\s*"))
                .collect(Collectors.toList()));

        if (!this.taskService.createTask(task)) {
            resp.sendRedirect("/task/create");
            return;
        }
        resp.sendRedirect("/");
    }
}
