package org.softuni.cardealer.web.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class SuppliersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private CarRepository carRepository;

    private Supplier supplier;

    public SuppliersControllerTest() {
        this.supplier = new Supplier();
        this.supplier.setName("pedal");
        this.supplier.setIsImporter(true);
    }

    @Before
    public void config() {
        this.carRepository.deleteAll();
        this.partRepository.deleteAll();
        this.supplierRepository.deleteAll();
    }

    @Test
    @WithMockUser("spring")
    public void addSupplier_withValidSupplier_saveSupplier() throws Exception {
        this.mockMvc
                .perform(
                        post("/suppliers/add")
                        .param("name", "pedal")
                        .param("isImporter", "true")
                );

        Assert.assertEquals(1, this.supplierRepository.count());
    }

    @Test(expected = Exception.class)
    @WithMockUser("spring")
    public void addSupplier_withInvalidSupplier_throwException() throws Exception {
        this.mockMvc.perform(post("/suppliers/add"));
    }

    @Test
    @WithMockUser("spring")
    public void addSupplier_withValidSupplier_redirectCorrect() throws Exception {
        this.mockMvc
                .perform(
                        post("/suppliers/add")
                                .param("name", "pedal")
                                .param("isImporter", "true")
                )
                .andExpect(redirectedUrl("all"));
    }

    @Test
    @WithMockUser("spring")
    public void editSupplier_withValidSupplier_correctEditSupplier() throws Exception {
        Supplier savedSupplier = this.supplierRepository.saveAndFlush(this.supplier);
        String expectedName = "pedal1";
        this.mockMvc
                .perform(
                        post("/suppliers/edit/" + savedSupplier.getId())
                                .param("name", expectedName)
                                .param("isImporter", "false")
                );

        Supplier actual = this.supplierRepository.findByName(expectedName)
                .orElseThrow(() -> new IllegalArgumentException("Supplier id is invalid."));

        Assert.assertEquals(expectedName, actual.getName());
        Assert.assertFalse(actual.getIsImporter());
    }

    @Test(expected = Exception.class)
    @WithMockUser("spring")
    public void editSupplier_withInvalidId_throwException() throws Exception {
        this.supplierRepository.saveAndFlush(this.supplier);
        this.mockMvc
                .perform(
                        post("/suppliers/edit/" + "123")
                                .param("name", "pedal1")
                                .param("isImporter", "false")
                );
    }

    @Test
    @WithMockUser("spring")
    public void editSupplier_withCorrectDate_redirectCorrect() throws Exception {
        Supplier supplier = this.supplierRepository.saveAndFlush(this.supplier);
        this.mockMvc
                .perform(
                        post("/suppliers/edit/" + supplier.getId())
                                .param("name", "pedal1")
                                .param("isImporter", "false")
                )
                .andExpect(redirectedUrl("/suppliers/all"));
    }

    @Test
    @WithMockUser("spring")
    public void deleteSupplier_withValidSupplier_toDeleteSupplier() throws Exception {
        Supplier savedSupplier = this.supplierRepository.saveAndFlush(this.supplier);
        this.mockMvc
                .perform(post("/suppliers/delete/" + savedSupplier.getId()));

        Assert.assertEquals(0, this.supplierRepository.count());
    }

    @Test(expected = Exception.class)
    @WithMockUser("spring")
    public void deleteSupplier_withInvalidId_throwException() throws Exception {
        this.supplierRepository.saveAndFlush(this.supplier);
        this.mockMvc
                .perform(post("/suppliers/delete/" + "123"));

    }

    @Test
    @WithMockUser("spring")
    public void deleteSupplier_withValidSupplier_correctRedirect() throws Exception {
        Supplier supplier = this.supplierRepository.saveAndFlush(this.supplier);
        this.mockMvc
                .perform(post("/suppliers/delete/" + supplier.getId()))
                .andExpect(redirectedUrl("/suppliers/all"));
    }

    @Test
    @WithMockUser("spring")
    public void allSuppliers_returnCorrectView() throws Exception {
        this.mockMvc
                .perform(get("/suppliers/all"))
                .andExpect(view().name("all-suppliers"))
                .andExpect(model().attributeExists("suppliers"));
    }

    @Test
    public void allSuppliers_withAnonymousUser_redirectCorrect() throws Exception {
        this.mockMvc
                .perform(get("/suppliers/all"))
                .andExpect(redirectedUrl("http://localhost/users/login"));
    }

    @Test
    @WithMockUser("spring")
    public void fetchSuppliers_returnCorrectSuppliers() throws Exception {
        Supplier supplier = this.supplierRepository.saveAndFlush(this.supplier);
        String actual = this.mockMvc
                .perform(get("/suppliers/fetch"))
                .andReturn().getResponse().getContentAsString();


        String expected = String.format("[{\"id\":\"%s\",\"name\":\"%s\",\"isImporter\":%s}]",
                supplier.getId(),
                supplier.getName(),
                supplier.getIsImporter());
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void fetchSuppliers_withAnonymousUser_redirectCorrect() throws Exception {
        this.mockMvc
                .perform(get("/suppliers/fetch"))
                .andExpect(redirectedUrl("http://localhost/users/login"));
    }
}
