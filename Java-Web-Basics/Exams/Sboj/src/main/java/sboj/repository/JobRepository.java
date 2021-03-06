package sboj.repository;

import sboj.domain.entities.JobApplication;

public interface JobRepository extends GenericRepository<JobApplication, String> {

    public boolean removeById(String id);
}
