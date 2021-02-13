package bg.softuni.mobilelele.utils;

import bg.softuni.mobilelele.entities.*;
import bg.softuni.mobilelele.entities.enums.EngineEnum;
import bg.softuni.mobilelele.entities.enums.ModelCategoryEnum;
import bg.softuni.mobilelele.entities.enums.TransmissionEnum;
import bg.softuni.mobilelele.entities.enums.UserRoleEnum;
import bg.softuni.mobilelele.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;
    private final OfferRepository offerRepository;

    @Autowired
    public DataInitializer(UserRoleRepository userRoleRepository,
                           UserRepository userRepository,
                           BrandRepository brandRepository,
                           ModelRepository modelRepository,
                           OfferRepository offerRepository) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
        this.offerRepository = offerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.userRoleRepository.count() == 0) {
            this.createUserRole(UserRoleEnum.USER);
            UserRoleEntity adminRole= this.createUserRole(UserRoleEnum.ADMIN);
            UserEntity userEntity = this.createAdminUser(adminRole);
            BrandEntity brandEntity = this.createBrand();
            ModelEntity modelEntity = this.createModel(brandEntity);
            this.createOffer(userEntity, modelEntity);
        }
    }

    private UserRoleEntity createUserRole(UserRoleEnum role) {
        UserRoleEntity userRole = new UserRoleEntity();
        userRole.setRole(role);

        this.userRoleRepository.save(userRole);
        return userRole;
    }

    private void createOffer(UserEntity userEntity, ModelEntity modelEntity) {
        OfferEntity offerEntity = new OfferEntity();
        offerEntity.setUser(userEntity);
        offerEntity.setModel(modelEntity);
        offerEntity.setEngine(EngineEnum.ELECTRIC);
        offerEntity.setTransmission(TransmissionEnum.AUTOMATIC);
        offerEntity.setPrice(new BigDecimal(1500));

        this.offerRepository.save(offerEntity);
    }

    private ModelEntity createModel(BrandEntity brandEntity) {
        ModelEntity modelEntity = new ModelEntity();
        modelEntity.setCategory(ModelCategoryEnum.CAR);
        modelEntity.setName("FastCar");
        modelEntity.setBrand(brandEntity);

        this.modelRepository.save(modelEntity);
        return modelEntity;
    }

    private BrandEntity createBrand() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setName("Opel");

        this.brandRepository.save(brandEntity);
        return brandEntity;
    }

    private UserEntity createAdminUser(UserRoleEntity adminRole) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("admin");
        userEntity.setFirstName("Ivan");
        userEntity.setLastName("Ivanov");
        userEntity.setActive(true);
        userEntity.setRoles(Collections.singletonList(adminRole));

        this.userRepository.save(userEntity);
        return userEntity;
    }
}
