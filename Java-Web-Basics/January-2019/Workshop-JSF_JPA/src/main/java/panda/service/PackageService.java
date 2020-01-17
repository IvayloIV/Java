package panda.service;

import panda.domain.enums.Status;
import panda.domain.models.services.PackageServiceModel;

import java.io.IOException;
import java.util.List;

public interface PackageService {

    public boolean save(PackageServiceModel entity);

    public List<PackageServiceModel> getAllPackages();

    public List<PackageServiceModel> getAllByStatus(Status status);

    public PackageServiceModel getById(String id);

    void changeStatus(String id, Status status) throws IOException;
}
