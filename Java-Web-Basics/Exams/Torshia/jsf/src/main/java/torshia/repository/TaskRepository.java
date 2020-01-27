package torshia.repository;

import torshia.domain.entities.Task;

import java.util.List;

public interface TaskRepository extends GenericRepository<Task, String> {

    public List<Task> getTaskWithReport(Boolean isReported);

    public Boolean update(Task task);
}
