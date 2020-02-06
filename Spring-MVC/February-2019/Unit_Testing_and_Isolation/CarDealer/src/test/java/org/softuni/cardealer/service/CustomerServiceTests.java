package org.softuni.cardealer.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.models.service.CustomerServiceModel;
import org.softuni.cardealer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CustomerServiceTests {
    @Autowired
    private CustomerRepository customerRepository;
    private ModelMapper modelMapper;
    private CustomerService customerService;

    @Before
    public void init() {
        this.modelMapper = new ModelMapper();
        this.customerService = new CustomerServiceImpl(this.customerRepository, this.modelMapper);
    }

    @Test
    public void saveCustomer_withValidData_expectToSaveCustomer() {
        CustomerServiceModel customerServiceModel = new CustomerServiceModel("Pesho", LocalDate.of(2012, 2, 1), false);
        CustomerServiceModel actual = this.customerService.saveCustomer(customerServiceModel);
        CustomerServiceModel expected = this.modelMapper
                .map(this.customerRepository.findAll().get(0), CustomerServiceModel.class);

        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getBirthDate(), actual.getBirthDate());
        Assert.assertEquals(expected.isYoungDriver(), actual.isYoungDriver());
    }

    @Test(expected = Exception.class)
    public void saveCustomer_withInvalidData_expectToThrowError() {
        CustomerServiceModel customerServiceModel = new CustomerServiceModel();
        this.customerService.saveCustomer(customerServiceModel);
    }

    @Test
    public void editCustomer_withValidData_expectToEditCustomer() {
        CustomerServiceModel customerServiceModel = new CustomerServiceModel("Pesho", LocalDate.of(2012, 2, 1), false);
        CustomerServiceModel customerModel = this.customerService.saveCustomer(customerServiceModel);
        customerModel.setName("Gosho");
        customerModel.setYoungDriver(true);
        customerModel.setBirthDate(LocalDate.of(2011, 5, 2));

        CustomerServiceModel actual = this.customerService.editCustomer(customerModel);
        CustomerServiceModel expected = this.modelMapper
                .map(this.customerRepository.findAll().get(0), CustomerServiceModel.class);

        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getBirthDate(), actual.getBirthDate());
        Assert.assertEquals(expected.isYoungDriver(), actual.isYoungDriver());
    }

    @Test(expected = Exception.class)
    public void editCustomer_withInvalidId_expectToThrowError() {
        CustomerServiceModel customerServiceModel = new CustomerServiceModel("Pesho", LocalDate.of(2012, 2, 1), false);
        this.customerService.editCustomer(customerServiceModel);
    }

    @Test
    public void deleteCustomer_withValidId_expectToDeleteCustomer() {
        CustomerServiceModel customerServiceModel = new CustomerServiceModel("Pesho", LocalDate.of(2012, 2, 1), false);
        CustomerServiceModel customerModel = this.customerService.saveCustomer(customerServiceModel);
        this.customerService.deleteCustomer(customerModel.getId());
        long actual = this.customerRepository.count();
        long expected = 0;

        Assert.assertEquals(expected, actual);
    }

    @Test(expected = Exception.class)
    public void deleteCustomer_withInvalidId_expectToThrowError() {
        this.customerService.deleteCustomer("12-34");
    }

    @Test
    public void findCustomerById_withValidId_expectToReturnCustomer() {
        CustomerServiceModel customerServiceModel = new CustomerServiceModel("Pesho", LocalDate.of(2012, 2, 1), false);
        CustomerServiceModel customerModel = this.customerService.saveCustomer(customerServiceModel);
        CustomerServiceModel actual = this.customerService.findCustomerById(customerModel.getId());
        CustomerServiceModel expected = this.modelMapper
                .map(this.customerRepository.findAll().get(0), CustomerServiceModel.class);

        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getBirthDate(), actual.getBirthDate());
        Assert.assertEquals(expected.isYoungDriver(), actual.isYoungDriver());
    }

    @Test(expected = Exception.class)
    public void findCustomerById_withInvalidId_expectToThrowError() {
        this.customerService.findCustomerById("12-34");
    }
}
