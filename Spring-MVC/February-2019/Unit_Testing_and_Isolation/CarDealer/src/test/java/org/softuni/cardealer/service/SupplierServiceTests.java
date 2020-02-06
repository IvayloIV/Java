package org.softuni.cardealer.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.entities.Supplier;
import org.softuni.cardealer.domain.models.service.SupplierServiceModel;
import org.softuni.cardealer.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class SupplierServiceTests {
    @Autowired
    private SupplierRepository supplierRepository;
    private ModelMapper modelMapper;
    private SupplierService supplierService;

    @Before
    public void init() {
        this.modelMapper = new ModelMapper();
        this.supplierService = new SupplierServiceImpl(this.supplierRepository, this.modelMapper);
    }

    @Test
    public void saveSupplier_with2Suppliers_expect2SuppliersSaved() {
        SupplierServiceModel supplierModel1 = new SupplierServiceModel("Test1", true);
        SupplierServiceModel supplierModel2 = new SupplierServiceModel("Test2", false);
        this.supplierService.saveSupplier(supplierModel1);
        this.supplierService.saveSupplier(supplierModel2);

        long actual = this.supplierRepository.count();
        long expected = 2;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void saveSupplier_with1Supplier_expectCorrectReturnSupplier() {
        SupplierServiceModel supplierModel = new SupplierServiceModel("Test1", true);
        SupplierServiceModel actual = this.supplierService.saveSupplier(supplierModel);
        SupplierServiceModel expected = this.modelMapper
                .map(this.supplierRepository.findAll().get(0), SupplierServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
    }

    @Test(expected = Exception.class)
    public void saveSupplier_withoutName_expectThrowError() {
        SupplierServiceModel supplierModel = new SupplierServiceModel(null, true);
        this.supplierService.saveSupplier(supplierModel);
    }

    @Test(expected = Exception.class)
    public void editSupplier_withNonExistId_expectThrowError() {
        SupplierServiceModel supplierModel = new SupplierServiceModel("Test", true);
        supplierModel.setId("123");
        this.supplierService.editSupplier(supplierModel);
    }

    @Test
    public void editSupplier_with1Supplier_expectCorrectReturnSupplier() {
        SupplierServiceModel supplierModel = new SupplierServiceModel("Test1", true);
        this.supplierRepository.save(this.modelMapper.map(supplierModel, Supplier.class));
        SupplierServiceModel expected = this.modelMapper
                .map(this.supplierRepository.findAll().get(0), SupplierServiceModel.class);
        expected.setName("Test2");
        expected.setImporter(false);

        SupplierServiceModel actual = this.supplierService.editSupplier(expected);

        Assert.assertEquals(actual.getId(), expected.getId());
        Assert.assertEquals(actual.getName(), expected.getName());
        Assert.assertEquals(actual.isImporter(), expected.isImporter());
    }

    @Test(expected = Exception.class)
    public void editSupplier_withIncorrectSupplier_expectThrowError() {
        SupplierServiceModel supplierModel = new SupplierServiceModel("Test1", true);
        this.supplierRepository.save(this.modelMapper.map(supplierModel, Supplier.class));
        SupplierServiceModel expected = this.modelMapper
                .map(this.supplierRepository.findAll().get(0), SupplierServiceModel.class);
        expected.setName(null);
        expected.setImporter(false);

        this.supplierService.editSupplier(expected);
    }

    @Test(expected = Exception.class)
    public void deleteSupplier_withNonExistId_expectThrowError() {
        this.supplierService.deleteSupplier("123");
    }

    @Test
    public void deleteSupplier_withSupplier_expectCorrectReturnSupplier() {
        SupplierServiceModel supplierModel = new SupplierServiceModel("Test1", true);
        this.supplierRepository.save(this.modelMapper.map(supplierModel, Supplier.class));
        SupplierServiceModel expected = this.modelMapper
                .map(this.supplierRepository.findAll().get(0), SupplierServiceModel.class);

        SupplierServiceModel actual = this.supplierService.deleteSupplier(expected.getId());

        Assert.assertEquals(actual.getId(), expected.getId());
        Assert.assertEquals(actual.getName(), expected.getName());
        Assert.assertEquals(actual.isImporter(), expected.isImporter());
    }

    @Test
    public void deleteSupplier_withSupplier_expectDeletedSupplierInDb() {
        SupplierServiceModel supplierModel = new SupplierServiceModel("Test1", true);
        this.supplierRepository.save(this.modelMapper.map(supplierModel, Supplier.class));
        SupplierServiceModel supplier = this.modelMapper
                .map(this.supplierRepository.findAll().get(0), SupplierServiceModel.class);

        this.supplierService.deleteSupplier(supplier.getId());
        long expected = 0;
        long actual = this.supplierRepository.count();

        Assert.assertEquals(expected, actual);
    }

    @Test(expected = Exception.class)
    public void findSupplierById_withNonExistId_expectThrowError() {
        this.supplierService.findSupplierById("InvalidId");
    }

    @Test
    public void findSupplierById_withCorrectId_expectCorrectReturnSupplier() {
        SupplierServiceModel supplierModel = new SupplierServiceModel("Test1", true);
        this.supplierRepository.save(this.modelMapper.map(supplierModel, Supplier.class));
        SupplierServiceModel expected = this.modelMapper
                .map(this.supplierRepository.findAll().get(0), SupplierServiceModel.class);

        SupplierServiceModel actual = this.supplierService.findSupplierById(expected.getId());

        Assert.assertEquals(actual.getId(), expected.getId());
        Assert.assertEquals(actual.getName(), expected.getName());
        Assert.assertEquals(actual.isImporter(), expected.isImporter());
    }
}
