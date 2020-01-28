package torshia.service;

import torshia.domain.models.services.TaskServiceModel;

import java.util.List;

public interface TaskService {

    public boolean createTask(TaskServiceModel taskServiceModel);

    public List<TaskServiceModel> getTaskWithReport(Boolean isReported);

    public void updateReport(String taskId, Boolean isReported);

    public TaskServiceModel getById(String taskId);
}
