package torshia.web.mbeans;

import org.modelmapper.ModelMapper;
import torshia.domain.models.services.TaskServiceModel;
import torshia.domain.models.views.TaskHomeViewModel;
import torshia.service.ReportService;
import torshia.service.TaskService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class HomeBean {

    private List<TaskHomeViewModel> taskHomeVM;

    private ModelMapper modelMapper;
    private TaskService taskService;
    private ReportService reportService;

    public HomeBean() {
    }

    @Inject
    public HomeBean(ModelMapper modelMapper, TaskService taskService, ReportService reportService) {
        this.modelMapper = modelMapper;
        this.taskService = taskService;
        this.reportService = reportService;
    }

    @PostConstruct
    public void init() {
        this.taskHomeVM = this.taskService.getTaskWithReport(false)
                .stream()
                .map(t -> {
                    TaskHomeViewModel taskVM = this.modelMapper
                            .map(t, TaskHomeViewModel.class);
                    taskVM.setLevel(this.calculateLevel(t));
                    return taskVM;
                })
                .collect(Collectors.toList());
    }

    private Long calculateLevel(TaskServiceModel task) {
        return task.getAffectedSectors().stream().distinct().count();
    }

    public String createReport(String taskId) {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        String userId = (String) context.getSessionMap().get("id");

        this.reportService.createReport(userId, taskId);
        this.taskService.updateReport(taskId, true);
        return "pretty:home";
    }

    public List<TaskHomeViewModel> getTaskHomeVM() {
        return taskHomeVM;
    }
}
