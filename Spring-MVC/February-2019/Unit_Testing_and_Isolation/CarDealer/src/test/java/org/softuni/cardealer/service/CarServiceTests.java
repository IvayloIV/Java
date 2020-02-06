package org.softuni.cardealer.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.models.service.CarServiceModel;
import org.softuni.cardealer.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CarServiceTests {
    @Autowired
    private CarRepository carRepository;
    private ModelMapper modelMapper;
    private CarService carService;

    @Before
    public void init() {
        this.modelMapper = new ModelMapper();
        this.carService = new CarServiceImpl(this.carRepository, this.modelMapper);
    }

    @Test
    public void saveCar_withValidCar_expectToCreateCar() {
        CarServiceModel carServiceModel = new CarServiceModel("China", "BMV", 12L);
        CarServiceModel actual = this.carService.saveCar(carServiceModel);
        CarServiceModel expected = this.modelMapper
                .map(this.carRepository.findAll().get(0), CarServiceModel.class);

        Assert.assertEquals(expected.getMake(), actual.getMake());
        Assert.assertEquals(expected.getModel(), actual.getModel());
        Assert.assertEquals(expected.getTravelledDistance(), actual.getTravelledDistance());
    }

    @Test(expected = Exception.class)
    public void saveCar_withNullValues_expectToThrowError() {
        CarServiceModel carServiceModel = new CarServiceModel();
        this.carService.saveCar(carServiceModel);
    }

    @Test
    public void editCar_withExistCar_expectToUpdateCar() {
        CarServiceModel carServiceModel = new CarServiceModel("China", "BMV", 12L);
        CarServiceModel newCar = this.carService.saveCar(carServiceModel);
        newCar.setMake("Bulgaria");
        newCar.setModel("Ferari");
        newCar.setTravelledDistance(23L);

        CarServiceModel actual = this.carService.editCar(newCar);
        CarServiceModel expected = this.modelMapper
                .map(this.carRepository.findAll().get(0), CarServiceModel.class);
        Assert.assertEquals(expected.getMake(), actual.getMake());
        Assert.assertEquals(expected.getModel(), actual.getModel());
        Assert.assertEquals(expected.getTravelledDistance(), actual.getTravelledDistance());
    }

    @Test(expected = Exception.class)
    public void editCar_withNonExistCar_expectToThrowError() {
        CarServiceModel carServiceModel = new CarServiceModel("China", "BMV", 12L);
        this.carService.editCar(carServiceModel);
    }

    @Test
    public void deleteCar_withExistCar_expectToDeleteCar() {
        CarServiceModel carServiceModel = new CarServiceModel("China", "BMV", 12L);
        CarServiceModel car = this.carService.saveCar(carServiceModel);
        this.carService.deleteCar(car.getId());
        long actual = this.carRepository.count();
        long expected = 0;

        Assert.assertEquals(expected, actual);
    }

    @Test(expected = Exception.class)
    public void deleteCar_withNonExistCar_expectToThrowError() {
        this.carService.deleteCar("12-12");
    }

    @Test
    public void findCarById_withExistId_expectToReturnCar() {
        CarServiceModel carServiceModel = new CarServiceModel("China", "BMV", 12L);
        CarServiceModel carModel = this.carService.saveCar(carServiceModel);
        CarServiceModel expected = this.carService.findCarById(carModel.getId());
        CarServiceModel actual = this.modelMapper
                .map(this.carRepository.findAll().get(0), CarServiceModel.class);

        Assert.assertEquals(expected.getMake(), actual.getMake());
        Assert.assertEquals(expected.getModel(), actual.getModel());
        Assert.assertEquals(expected.getTravelledDistance(), actual.getTravelledDistance());
    }

    @Test(expected = Exception.class)
    public void findCarById_withNonExistCar_expectToThrowError() {
        this.carService.findCarById("12-12");
    }
}
