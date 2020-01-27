package torshia.web.mbeans;

import org.modelmapper.ModelMapper;
import torshia.domain.models.services.TaskServiceModel;
import torshia.domain.models.views.TaskDetailViewModel;
import torshia.service.TaskService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class TaskDetailsBean {

    private TaskDetailViewModel taskDetailVM;

    private ModelMapper modelMapper;
    private TaskService taskService;

    public TaskDetailsBean() {
    }

    @Inject
    public TaskDetailsBean(ModelMapper modelMapper, TaskService taskService) {
        this.modelMapper = modelMapper;
        this.taskService = taskService;
    }

    @PostConstruct
    public void init() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        String taskId = context.getRequestParameterMap().get("taskId");
        if (taskId != null) {
            TaskServiceModel taskModel = this.taskService.getById(taskId);
            this.taskDetailVM = this.createTaskViewModel(taskModel);
        }
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

    public TaskDetailViewModel getTaskDetailVM() {
        return taskDetailVM;
    }
}
