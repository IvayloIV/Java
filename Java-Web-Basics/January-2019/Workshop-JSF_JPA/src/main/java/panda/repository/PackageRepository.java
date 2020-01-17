package panda.repository;

import panda.domain.entities.Package;
import panda.domain.enums.Status;

import java.util.List;

public interface PackageRepository extends GenericRepository<Package, String> {

    public List<Package> getAllByStatus(Status status);

    void updatePackage(Package entity);
}
