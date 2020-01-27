package torshia.web.mbeans;

import org.modelmapper.ModelMapper;
import torshia.domain.models.services.TaskServiceModel;
import torshia.domain.models.views.ReportDetailsViewModel;
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
public class ReportDetailsBean {

    private ReportDetailsViewModel reportDetailsViewModel;

    private ModelMapper modelMapper;
    private TaskService taskService;

    public ReportDetailsBean() {
    }

    @Inject
    public ReportDetailsBean(ModelMapper modelMapper, TaskService taskService) {
        this.modelMapper = modelMapper;
        this.taskService = taskService;
    }

    @PostConstruct
    public void init() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        String taskId = context.getRequestParameterMap().get("taskId");
        if (taskId != null) {
            TaskServiceModel taskModel = this.taskService.getById(taskId);
            this.reportDetailsViewModel = this.createReportDetailsView(taskModel);
        }
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

    public ReportDetailsViewModel getReportDetailsViewModel() {
        return reportDetailsViewModel;
    }
}
