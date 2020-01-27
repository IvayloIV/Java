package torshia.service;

import torshia.domain.models.services.ReportServiceModel;

import java.util.List;

public interface ReportService {

    public Boolean createReport(String userId, String taskId);

    public List<ReportServiceModel> getAll();
}
