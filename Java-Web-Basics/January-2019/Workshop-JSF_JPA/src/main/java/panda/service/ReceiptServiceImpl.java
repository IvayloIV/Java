package panda.service;

import org.modelmapper.ModelMapper;
import panda.domain.entities.Receipt;
import panda.domain.models.services.PackageServiceModel;
import panda.domain.models.services.ReceiptServiceModel;
import panda.domain.models.services.UserServiceModel;
import panda.repository.PackageRepository;
import panda.repository.ReceiptRepository;
import panda.repository.UserRepository;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Date;

public class ReceiptServiceImpl implements ReceiptService {

    private ModelMapper modelMapper;
    private ReceiptRepository receiptRepository;
    private UserRepository userRepository;
    private PackageRepository packageRepository;

    @Inject
    public ReceiptServiceImpl(ModelMapper modelMapper, ReceiptRepository receiptRepository, UserRepository userRepository, PackageRepository packageRepository) {
        this.modelMapper = modelMapper;
        this.receiptRepository = receiptRepository;
        this.userRepository = userRepository;
        this.packageRepository = packageRepository;
    }

    @Override
    public void createReceipt(String ownerName, String packageId) {
        UserServiceModel userServiceModel = this.modelMapper
                .map(this.userRepository.getByUsername(ownerName), UserServiceModel.class);

        PackageServiceModel packageServiceModel = this.modelMapper
                .map(this.packageRepository.getById(packageId), PackageServiceModel.class);


        ReceiptServiceModel receiptServiceModel = this.getReceiptServiceModel(userServiceModel, packageServiceModel);
        this.receiptRepository
                .save(this.modelMapper.map(receiptServiceModel, Receipt.class));
    }

    @Override
    public ReceiptServiceModel getById(String receiptId) {
        return this.modelMapper
                .map(this.receiptRepository.getById(receiptId), ReceiptServiceModel.class);
    }

    private ReceiptServiceModel getReceiptServiceModel(UserServiceModel userServiceModel, PackageServiceModel packageServiceModel) {
        ReceiptServiceModel receiptServiceModel = new ReceiptServiceModel();
        receiptServiceModel.setFee(BigDecimal.valueOf(packageServiceModel.getWeight() * 2.67));
        receiptServiceModel.setIssuedOn(new Date());
        receiptServiceModel.setRecipient(userServiceModel);
        receiptServiceModel.setReceiptPackage(packageServiceModel);

        return receiptServiceModel;
    }
}
