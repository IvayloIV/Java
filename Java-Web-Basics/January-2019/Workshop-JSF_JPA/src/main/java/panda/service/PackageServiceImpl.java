package panda.service;

import org.modelmapper.ModelMapper;
import panda.domain.entities.Package;
import panda.domain.enums.Status;
import panda.domain.models.services.PackageServiceModel;
import panda.repository.PackageRepository;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class PackageServiceImpl implements PackageService {

    private static final int MAX_DAYS = 40;
    private static final int MIN_DAYS = 20;

    private ModelMapper modelMapper;
    private PackageRepository packageRepository;
    private Random random;
    private ReceiptService receiptService;

    @Inject
    public PackageServiceImpl(ModelMapper modelMapper, PackageRepository packageRepository, Random random, ReceiptService receiptService) {
        this.modelMapper = modelMapper;
        this.packageRepository = packageRepository;
        this.random = random;
        this.receiptService = receiptService;
    }

    @Override
    public boolean save(PackageServiceModel entity) {
        entity.setStatus(Status.Pending);
        return this.packageRepository.save(this.modelMapper.map(entity, Package.class));
    }

    @Override
    public List<PackageServiceModel> getAllPackages() {
        return this.packageRepository.getAll()
                .stream()
                .map(p -> this.modelMapper.map(p, PackageServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PackageServiceModel> getAllByStatus(Status status) {
        return this.packageRepository.getAllByStatus(status)
                .stream()
                .map(p -> this.modelMapper.map(p, PackageServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public PackageServiceModel getById(String id) {
        return this.modelMapper
                .map(this.packageRepository.getById(id), PackageServiceModel.class);
    }

    @Override
    public void changeStatus(String id, Status status) throws IOException {
        PackageServiceModel packageStatusVM = this.getById(id);
        packageStatusVM.setStatus(status);

        if (status.equals(Status.Shipped)) {
            int days = this.random.nextInt(MAX_DAYS - MIN_DAYS) + MIN_DAYS;
            packageStatusVM.setDeliveryDate(new Date(new Date().getTime() + (days * 24 * 60 * 60 * 1000)));
        } else if (status.equals(Status.Acquired)) {
            this.receiptService.createReceipt(packageStatusVM.getRecipient().getUsername(), packageStatusVM.getId());
        }

        this.packageRepository
                .updatePackage(this.modelMapper.map(packageStatusVM, Package.class));
    }
}
