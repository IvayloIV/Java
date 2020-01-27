package torshia.web.mbeans;

import org.modelmapper.ModelMapper;
import torshia.domain.models.services.TaskServiceModel;
import torshia.domain.models.views.TaskReportViewModel;
import torshia.service.ReportService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class ReportAllBean {

    private List<TaskReportViewModel> taskReportsVM;

    private ModelMapper modelMapper;
    private ReportService reportService;

    public ReportAllBean() {
    }

    @Inject
    public ReportAllBean(ModelMapper modelMapper, ReportService reportService) {
        this.modelMapper = modelMapper;
        this.reportService = reportService;
    }

    @PostConstruct
    public void init() {
        this.taskReportsVM = this.reportService.getAll()
                .stream()
                .map(r -> {
                    TaskReportViewModel taskVM = this.modelMapper
                            .map(r, TaskReportViewModel.class);
                    taskVM.setLevel(this.calculateLevel(r.getTask()));
                    return taskVM;
                })
                .collect(Collectors.toList());
    }

    private Long calculateLevel(TaskServiceModel task) {
        return task.getAffectedSectors().stream().distinct().count();
    }

    public List<TaskReportViewModel> getTaskReportsVM() {
        return taskReportsVM;
    }
}
