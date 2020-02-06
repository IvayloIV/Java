package org.softuni.cardealer.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.models.service.CarSaleServiceModel;
import org.softuni.cardealer.domain.models.service.CarServiceModel;
import org.softuni.cardealer.domain.models.service.PartSaleServiceModel;
import org.softuni.cardealer.repository.CarSaleRepository;
import org.softuni.cardealer.repository.PartSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class SaleServiceTests {

    @Autowired
    private PartSaleRepository partSaleRepository;

    @Autowired
    private CarSaleRepository carSaleRepository;

    private ModelMapper modelMapper;
    private SaleServiceImpl saleService;

    @Before
    public void init() {
        this.modelMapper = new ModelMapper();
        this.saleService = new SaleServiceImpl(carSaleRepository, partSaleRepository, modelMapper);
    }

    @Test
    public void saleCar_with2Cars_expect2CarsSaved() {
        CarServiceModel car1 = new CarServiceModel("China", "BMV", 10L);
        CarServiceModel car2 = new CarServiceModel("Bulgaria", "Opel", 23L);

        CarSaleServiceModel carModel1 = new CarSaleServiceModel();
        carModel1.setCar(car1);
        carModel1.setDiscount(23.1);
        CarSaleServiceModel carModel2 = new CarSaleServiceModel();
        carModel2.setCar(car2);
        carModel2.setDiscount(21.1);
        this.saleService.saleCar(carModel1);
        this.saleService.saleCar(carModel1);

        long actual = this.carSaleRepository.count();
        long expected = 2;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void saleCar_with1Car_expectCorrectReturnCar() {
        CarServiceModel car1 = new CarServiceModel("China", "BMV", 10L);

        CarSaleServiceModel carModel1 = new CarSaleServiceModel();
        carModel1.setCar(car1);
        carModel1.setDiscount(23.1);
        CarSaleServiceModel actual = this.saleService.saleCar(carModel1);
        CarSaleServiceModel expected = this.modelMapper
                .map(this.carSaleRepository.findAll().get(0), CarSaleServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getDiscount(), actual.getDiscount());
    }

    @Test(expected = Exception.class)
    public void saleCar_withoutDiscount_expectThrowError() {
        CarServiceModel car1 = new CarServiceModel("China", "BMV", 10L);

        CarSaleServiceModel carModel = new CarSaleServiceModel();
        carModel.setCar(car1);
         this.saleService.saleCar(carModel);
    }

    @Test
    public void salePart_with2Parts_expect2PartsSaved() {
        PartSaleServiceModel partSaleModel1 = new PartSaleServiceModel();
        partSaleModel1.setDiscount(20.2);
        partSaleModel1.setQuantity(20);

        PartSaleServiceModel partSaleModel2 = new PartSaleServiceModel();
        partSaleModel2.setDiscount(23.2);
        partSaleModel2.setQuantity(25);

        this.saleService.salePart(partSaleModel1);
        this.saleService.salePart(partSaleModel2);

        long actual = this.partSaleRepository.count();
        long expected = 2;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void salePart_with1PartSale_expectCorrectReturnPartSale() {
        PartSaleServiceModel partSaleModel = new PartSaleServiceModel();
        partSaleModel.setDiscount(20.2);
        partSaleModel.setQuantity(20);

        PartSaleServiceModel actual = this.saleService.salePart(partSaleModel);
        PartSaleServiceModel expected = this.modelMapper
                .map(this.partSaleRepository.findAll().get(0), PartSaleServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getDiscount(), actual.getDiscount());
    }

    @Test(expected = Exception.class)
    public void salePart_withoutDiscount_expectThrowError() {
        PartSaleServiceModel partSaleModel = new PartSaleServiceModel();
        partSaleModel.setQuantity(20);
        this.saleService.salePart(partSaleModel);
    }
}
