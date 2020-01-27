package torshia.service;

import org.modelmapper.ModelMapper;
import torshia.domain.entities.Task;
import torshia.domain.models.services.TaskServiceModel;
import torshia.repository.TaskRepository;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TaskServiceImpl implements TaskService {

    private ModelMapper modelMapper;
    private TaskRepository taskRepository;

    @Inject
    public TaskServiceImpl(ModelMapper modelMapper, TaskRepository taskRepository) {
        this.modelMapper = modelMapper;
        this.taskRepository = taskRepository;
    }

    @Override
    public boolean createTask(TaskServiceModel taskServiceModel) {
        taskServiceModel.setReported(false);
        taskServiceModel.setDueDate(new Date());
        return this.taskRepository
                .save(this.modelMapper.map(taskServiceModel, Task.class));
    }

    @Override
    public List<TaskServiceModel> getTaskWithReport(Boolean isReported) {
        return this.taskRepository.getTaskWithReport(isReported)
                .stream()
                .map(t -> this.modelMapper.map(t, TaskServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void updateReport(String taskId, Boolean isReported) {
        TaskServiceModel taskModel = this.modelMapper
                .map(this.taskRepository.getById(taskId), TaskServiceModel.class);
        taskModel.setReported(isReported);
        this.taskRepository.update(this.modelMapper.map(taskModel, Task.class));
    }

    @Override
    public TaskServiceModel getById(String taskId) {
        return this.modelMapper
                .map(this.taskRepository.getById(taskId), TaskServiceModel.class);
    }
}
