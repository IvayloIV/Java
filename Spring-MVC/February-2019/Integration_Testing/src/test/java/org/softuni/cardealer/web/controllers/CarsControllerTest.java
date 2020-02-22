package org.softuni.cardealer.web.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.cardealer.domain.entities.Car;
import org.softuni.cardealer.domain.entities.Part;
import org.softuni.cardealer.domain.entities.Supplier;
import org.softuni.cardealer.repository.CarRepository;
import org.softuni.cardealer.repository.PartRepository;
import org.softuni.cardealer.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CarsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private CarRepository carRepository;

    private Part part;

    private Supplier supplier;

    private Car car;

    public CarsControllerTest() {
        this.supplier = new Supplier();
        this.supplier.setName("pedal");
        this.supplier.setIsImporter(true);

        this.part = new Part();
        this.part.setName("part1");
        this.part.setPrice(BigDecimal.valueOf(123));
        this.part.setSupplier(supplier);

        List<Part> parts = new ArrayList<>();
        parts.add(this.part);
        this.car = new Car();
        this.car.setMake("China");
        this.car.setModel("BMW");
        this.car.setTravelledDistance(101L);
        this.car.setParts(parts);
    }

    @Before
    public void config() {
        this.carRepository.deleteAll();
        this.partRepository.deleteAll();
        this.supplierRepository.deleteAll();
    }

    @Test
    @WithMockUser("spring")
    public void addCar_withValidCar_1CarSaved() throws Exception {
        this.supplierRepository.saveAndFlush(this.supplier);
        Part part = this.partRepository.saveAndFlush(this.part);

        this.mockMvc
            .perform(
                post("/cars/add")
                    .param("make", "BMW")
                    .param("model", "China")
                    .param("travelledDistance", "32")
                    .param("parts", part.getId())
            );

        Assert.assertEquals(1, this.carRepository.count());
    }

    @Test(expected = Exception.class)
    @WithMockUser("spring")
    public void addCar_withInvalidCar_throwException() throws Exception {
        this.mockMvc.perform(post("/cars/add"));
    }

    @Test
    @WithMockUser("spring")
    public void addCar_withValidCar_redirectCorrect() throws Exception {
        this.supplierRepository.saveAndFlush(this.supplier);
        Part part = this.partRepository.saveAndFlush(this.part);

        this.mockMvc
                .perform(
                        post("/cars/add")
                                .param("make", "BMW")
                                .param("model", "China")
                                .param("travelledDistance", "32")
                                .param("parts", part.getId())
                )
                .andExpect(redirectedUrl("all"));
    }

    @Test
    public void addCar_withGuestUser_redirectCorrect() throws Exception {
        this.supplierRepository.saveAndFlush(this.supplier);
        Part part = this.partRepository.saveAndFlush(this.part);

        this.mockMvc
                .perform(
                        post("/cars/add")
                                .param("make", "BMW")
                                .param("model", "China")
                                .param("travelledDistance", "32")
                                .param("parts", part.getId())
                )
                .andExpect(redirectedUrl("http://localhost/users/login"));
    }

    @Test
    @WithMockUser("spring")
    public void editCar_withValidCar_toEdtCar() throws Exception {
        this.supplierRepository.saveAndFlush(this.supplier);
        this.partRepository.saveAndFlush(this.part);
        Car car = this.carRepository.saveAndFlush(this.car);

        String expectedMake = "BMW2";
        String expectedModel = "China2";
        String travelledDistance = "322";
        this.mockMvc
            .perform(
                post("/cars/edit/" + car.getId())
                    .param("make", expectedMake)
                    .param("model", expectedModel)
                    .param("travelledDistance", travelledDistance)
            );

        Car actual = this.carRepository.findById(car.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid id car!"));

        Assert.assertEquals(expectedMake, actual.getMake());
        Assert.assertEquals(expectedModel, actual.getModel());
        Assert.assertEquals(travelledDistance, actual.getTravelledDistance().toString());
    }

    @Test(expected = Exception.class)
    @WithMockUser("spring")
    public void editCar_withInvalidCarId_throwException() throws Exception {
        this.supplierRepository.saveAndFlush(this.supplier);
        this.partRepository.saveAndFlush(this.part);
        this.carRepository.saveAndFlush(this.car);

        String expectedMake = "BMW2";
        String expectedModel = "China2";
        String travelledDistance = "322";
        this.mockMvc
            .perform(
                post("/cars/edit/" + "123")
                    .param("make", expectedMake)
                    .param("model", expectedModel)
                    .param("travelledDistance", travelledDistance)
            );
    }

    @Test
    @WithMockUser("spring")
    public void editCar_withValidCar_redirectCorrect() throws Exception {
        this.supplierRepository.saveAndFlush(this.supplier);
        this.partRepository.saveAndFlush(this.part);
        Car car = this.carRepository.saveAndFlush(this.car);

        String expectedMake = "BMW2";
        String expectedModel = "China2";
        String travelledDistance = "322";
        this.mockMvc
            .perform(
                post("/cars/edit/" + car.getId())
                    .param("make", expectedMake)
                    .param("model", expectedModel)
                    .param("travelledDistance", travelledDistance)
            )
            .andExpect(redirectedUrl("/cars/all"));
    }

    @Test
    @WithMockUser("spring")
    public void deleteCar_withValidIdCar_toRemoveCar() throws Exception {
        this.supplierRepository.saveAndFlush(this.supplier);
        this.partRepository.saveAndFlush(this.part);
        Car car = this.carRepository.saveAndFlush(this.car);

        this.mockMvc.perform(post("/cars/delete/" + car.getId()));
        Assert.assertEquals(0, this.carRepository.count());
    }

    @Test(expected = Exception.class)
    @WithMockUser("spring")
    public void deleteCar_withInvalidIdCar_throwException() throws Exception {
        this.supplierRepository.saveAndFlush(this.supplier);
        this.partRepository.saveAndFlush(this.part);
        this.carRepository.saveAndFlush(this.car);

        this.mockMvc.perform(post("/cars/delete/" + "123"));
    }

    @Test
    @WithMockUser("spring")
    public void deleteCar_withValidIdCar_redirectCorrect() throws Exception {
        this.supplierRepository.saveAndFlush(this.supplier);
        this.partRepository.saveAndFlush(this.part);
        Car car = this.carRepository.saveAndFlush(this.car);

        this.mockMvc
                .perform(post("/cars/delete/" + car.getId()))
                .andExpect(redirectedUrl("/cars/all"));
    }

    @Test
    @WithMockUser("spring")
    public void allCars_returnValidView() throws Exception {
        this.supplierRepository.saveAndFlush(this.supplier);
        this.partRepository.saveAndFlush(this.part);
        this.carRepository.saveAndFlush(this.car);

        this.mockMvc.perform(get("/cars/all"))
            .andExpect(view().name("all-cars"))
            .andExpect(model().attributeExists("cars"));
    }

    @Test
    public void allCars_withGuestUser_redirectCorrect() throws Exception {
        this.supplierRepository.saveAndFlush(this.supplier);
        this.partRepository.saveAndFlush(this.part);
        this.carRepository.saveAndFlush(this.car);

        this.mockMvc.perform(get("/cars/all"))
                .andExpect(redirectedUrl("http://localhost/users/login"));
    }
}
