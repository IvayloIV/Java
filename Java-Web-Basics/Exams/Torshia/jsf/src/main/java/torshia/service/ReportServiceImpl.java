package torshia.service;

import org.modelmapper.ModelMapper;
import torshia.domain.entities.Report;
import torshia.domain.enums.ReportStatus;
import torshia.domain.models.services.ReportServiceModel;
import torshia.domain.models.services.TaskServiceModel;
import torshia.domain.models.services.UserServiceModel;
import torshia.repository.ReportRepository;
import torshia.repository.TaskRepository;
import torshia.repository.UserRepository;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ReportServiceImpl implements ReportService {

    private ModelMapper modelMapper;
    private ReportRepository reportRepository;
    private UserRepository userRepository;
    private TaskRepository taskRepository;
    private Random random;

    @Inject
    public ReportServiceImpl(ModelMapper modelMapper,
                             ReportRepository reportRepository,
                             UserRepository userRepository,
                             TaskRepository taskRepository,
                             Random random) {
        this.modelMapper = modelMapper;
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.random = random;
    }

    @Override
    public Boolean createReport(String userId, String taskId) {
        TaskServiceModel taskModel = this.modelMapper
                .map(this.taskRepository.getById(taskId), TaskServiceModel.class);

        UserServiceModel userModel = this.modelMapper
                .map(this.userRepository.getById(userId), UserServiceModel.class);

        ReportServiceModel report = new ReportServiceModel();
        report.setReportedOn(new Date());
        report.setReporter(userModel);
        report.setTask(taskModel);
        report.setStatus(this.generateStatus());

        return this.reportRepository.save(this.modelMapper.map(report, Report.class));
    }

    @Override
    public List<ReportServiceModel> getAll() {
        return this.reportRepository.getAll()
                .stream()
                .map(r -> this.modelMapper.map(r, ReportServiceModel.class))
                .collect(Collectors.toList());
    }

    private ReportStatus generateStatus() {
        int num = this.random.nextInt(100) + 1;

        if (num <= 75) {
            return ReportStatus.Completed;
        }
        return ReportStatus.Archived;
    }
}
