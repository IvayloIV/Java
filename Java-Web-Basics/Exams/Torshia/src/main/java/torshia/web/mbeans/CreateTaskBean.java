package torshia.web.mbeans;

import org.modelmapper.ModelMapper;
import torshia.domain.enums.TaskSector;
import torshia.domain.models.bindings.TaskCreateBindingModel;
import torshia.domain.models.services.TaskServiceModel;
import torshia.service.TaskService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class CreateTaskBean {

    private TaskCreateBindingModel taskCreateBM;
    private List<String> sectors;

    private ModelMapper modelMapper;
    private TaskService taskService;

    public CreateTaskBean() {
    }

    @Inject
    public CreateTaskBean(ModelMapper modelMapper, TaskService taskService) {
        this.modelMapper = modelMapper;
        this.taskService = taskService;
    }

    @PostConstruct
    public void init() {
        this.taskCreateBM = new TaskCreateBindingModel();
    }

    public String createTask() {
        TaskServiceModel task = this.modelMapper.map(this.taskCreateBM, TaskServiceModel.class);
        task.setParticipants(Arrays.stream(this.taskCreateBM.getParticipants()
                .split(",\\s*"))
                .collect(Collectors.toList()));

        if (!this.taskService.createTask(task)) {
            return "pretty:createTask";
        }
        return "pretty:home";
    }

    public List<String> getSectors() {
        if (this.sectors == null) {
            this.sectors = Arrays.stream(TaskSector.values())
                .map(Enum::toString)
                .collect(Collectors.toList());
        }
        return sectors;
    }

    public TaskCreateBindingModel getTaskCreateBM() {
        return taskCreateBM;
    }
}
