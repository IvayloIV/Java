package org.softuni.cardealer.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.models.service.PartServiceModel;
import org.softuni.cardealer.domain.models.service.SupplierServiceModel;
import org.softuni.cardealer.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PartServiceTests {
    @Autowired
    private PartRepository partRepository;
    private ModelMapper modelMapper;
    private PartService partService;

    @Before
    public void init() {
        this.modelMapper = new ModelMapper();
        this.partService = new PartServiceImpl(this.partRepository, this.modelMapper);
    }

    @Test
    public void savePart_withValidData_expectToSaveCustomer() {
        SupplierServiceModel supplierServiceModel = new SupplierServiceModel("TTT", true);
        PartServiceModel partServiceModel = new PartServiceModel("Engine", BigDecimal.valueOf(23), supplierServiceModel);
        PartServiceModel expected = this.partService.savePart(partServiceModel);
        PartServiceModel actual = this.modelMapper
                .map(this.partRepository.findAll().get(0), PartServiceModel.class);

        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getPrice(), actual.getPrice());
        Assert.assertEquals(expected.getSupplier().getName(), actual.getSupplier().getName());
        Assert.assertEquals(expected.getSupplier().isImporter(), actual.getSupplier().isImporter());
    }

    @Test(expected = Exception.class)
    public void savePart_withInvalidData_expectToThrowError() {
        PartServiceModel partServiceModel = new PartServiceModel();
        this.partService.savePart(partServiceModel);
    }

    @Test
    public void editPart_withValidData_expectEditPart() {
        SupplierServiceModel supplierServiceModel = new SupplierServiceModel("TTT", true);
        PartServiceModel partServiceModel = new PartServiceModel("Engine", BigDecimal.valueOf(23), supplierServiceModel);
        PartServiceModel partModel = this.partService.savePart(partServiceModel);
        partModel.setName("Tide");
        partModel.setPrice(BigDecimal.valueOf(12));
        PartServiceModel expected = this.partService.editPart(partModel);

        PartServiceModel actual = this.modelMapper
                .map(this.partRepository.findAll().get(0), PartServiceModel.class);

        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getPrice(), actual.getPrice());
        Assert.assertEquals(expected.getSupplier().getName(), actual.getSupplier().getName());
        Assert.assertEquals(expected.getSupplier().isImporter(), actual.getSupplier().isImporter());
    }

    @Test(expected = Exception.class)
    public void editPart_withInvalidId_expectToThrowError() {
        SupplierServiceModel supplierServiceModel = new SupplierServiceModel("TTT", true);
        PartServiceModel partServiceModel = new PartServiceModel("Engine", BigDecimal.valueOf(23), supplierServiceModel);
        this.partService.editPart(partServiceModel);
    }

    @Test
    public void deletePart_withValidId_expectToDeletePart() {
        SupplierServiceModel supplierServiceModel = new SupplierServiceModel("TTT", true);
        PartServiceModel partServiceModel = new PartServiceModel("Engine", BigDecimal.valueOf(23), supplierServiceModel);
        PartServiceModel partModel = this.partService.savePart(partServiceModel);
        this.partService.deletePart(partModel.getId());

        long actual = this.partRepository.count();
        long expected = 0;

        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deletePart_withInvalidId_expectToThrowError() {
        this.partService.deletePart("12-32");
    }

    @Test
    public void findPartById_withValidId_expectToReturnPart() {
        SupplierServiceModel supplierServiceModel = new SupplierServiceModel("TTT", true);
        PartServiceModel partServiceModel = new PartServiceModel("Engine", BigDecimal.valueOf(23), supplierServiceModel);
        PartServiceModel partModel = this.partService.savePart(partServiceModel);
        PartServiceModel actual = this.partService.findPartById(partModel.getId());
        PartServiceModel expected = this.modelMapper
                .map(this.partRepository.findAll().get(0), PartServiceModel.class);

        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getPrice(), actual.getPrice());
        Assert.assertEquals(expected.getSupplier().getName(), actual.getSupplier().getName());
        Assert.assertEquals(expected.getSupplier().isImporter(), actual.getSupplier().isImporter());
    }

    @Test(expected = Exception.class)
    public void findPartById_withInvalidId_expectToThrowError() {
        this.partService.findPartById("12-32");
    }
}
