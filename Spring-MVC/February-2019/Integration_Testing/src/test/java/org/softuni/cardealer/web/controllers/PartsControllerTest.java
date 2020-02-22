package org.softuni.cardealer.web.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PartsControllerTest {

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

    public PartsControllerTest() {
        this.supplier = new Supplier();
        this.supplier.setName("pedal");
        this.supplier.setIsImporter(true);

        this.part = new Part();
        this.part.setName("part1");
        this.part.setPrice(BigDecimal.valueOf(123));
        this.part.setSupplier(supplier);
    }

    @Before
    public void config() {
        this.carRepository.deleteAll();
        this.partRepository.deleteAll();
        this.supplierRepository.deleteAll();
    }

    @Test
    @WithMockUser("spring")
    public void addPart_withCorrectPart_1PartSaved() throws Exception {
        this.supplierRepository.saveAndFlush(this.supplier);
        this.mockMvc
                .perform(
                    post("/parts/add")
                    .param("name", "Part1")
                    .param("price", "33")
                    .param("supplier", this.supplier.getName())
                );

        Assert.assertEquals(1, this.supplierRepository.count());
    }

    @Test(expected = Exception.class)
    @WithMockUser("spring")
    public void addPart_withInvalidSupplier_throwException() throws Exception {
        this.mockMvc
                .perform(
                        post("/parts/add")
                                .param("name", "Part1")
                                .param("price", "33")
                                .param("supplier", "123")
                );
    }

    @Test(expected = Exception.class)
    @WithMockUser("spring")
    public void addPart_withInvalidParams_throwException() throws Exception {
        this.mockMvc.perform(post("/parts/add"));
    }

    @Test
    @WithMockUser("spring")
    public void addPart_withCorrectPart_redirectCorrect() throws Exception {
        this.supplierRepository.saveAndFlush(this.supplier);
        this.mockMvc
                .perform(
                        post("/parts/add")
                                .param("name", "Part1")
                                .param("price", "33")
                                .param("supplier", this.supplier.getName())
                )
                .andExpect(redirectedUrl("all"));
    }

    @Test
    @WithMockUser("spring")
    public void editPart_withCorrectPart_correctEditPart() throws Exception {
        this.supplierRepository.saveAndFlush(this.supplier);
        Part savedPart = this.partRepository.saveAndFlush(this.part);

        String expectedName = "Part2";
        BigDecimal expectedPrice = BigDecimal.valueOf(124);
        this.mockMvc
                .perform(
                        post("/parts/edit/" + savedPart.getId())
                                .param("name", expectedName)
                                .param("price", expectedPrice.toString())
                );

        Part actual = this.partRepository.findById(savedPart.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid part id."));
        Assert.assertEquals(expectedName, actual.getName());
        Assert.assertEquals(expectedPrice.toBigInteger(), actual.getPrice().toBigInteger());
    }

    @Test(expected = Exception.class)
    @WithMockUser("spring")
    public void editPart_withInvalidIdPart_throwException() throws Exception {
        this.supplierRepository.saveAndFlush(this.supplier);
        this.partRepository.saveAndFlush(this.part);

        String expectedName = "Part2";
        BigDecimal expectedPrice = BigDecimal.valueOf(124);
        this.mockMvc
                .perform(
                        post("/parts/edit/" + "333")
                                .param("name", expectedName)
                                .param("price", expectedPrice.toString())
                );
    }

    @Test
    @WithMockUser("spring")
    public void editPart_WithValidPart_redirectCorrect() throws Exception {
        this.supplierRepository.saveAndFlush(this.supplier);
        Part part = this.partRepository.saveAndFlush(this.part);

        String expectedName = "Part2";
        BigDecimal expectedPrice = BigDecimal.valueOf(124);
        this.mockMvc
                .perform(
                        post("/parts/edit/" + part.getId())
                                .param("name", expectedName)
                                .param("price", expectedPrice.toString())
                )
                .andExpect(redirectedUrl("/parts/all"));
    }

    @Test
    @WithMockUser("spring")
    public void deletePart_withCorrectIdPart_toDeletePart() throws Exception {
        this.supplierRepository.saveAndFlush(this.supplier);
        Part savedPart = this.partRepository.saveAndFlush(this.part);

        this.mockMvc.perform(post("/parts/delete/" + savedPart.getId()));
        Assert.assertEquals(0, this.partRepository.count());
    }

    @Test(expected = Exception.class)
    @WithMockUser("spring")
    public void deletePart_withInvalidIdPart_throwException() throws Exception {
        this.supplierRepository.saveAndFlush(this.supplier);
        this.partRepository.saveAndFlush(this.part);
        this.mockMvc.perform(post("/parts/delete/" + "333"));
    }

    @Test
    @WithMockUser("spring")
    public void deletePart_withCorrectIdPart_toRedirectCorrect() throws Exception {
        this.supplierRepository.saveAndFlush(this.supplier);
        Part savedPart = this.partRepository.saveAndFlush(this.part);

        this.mockMvc.perform(
                    post("/parts/delete/" + savedPart.getId())
                ).andExpect(redirectedUrl("/parts/all"));
    }

    @Test
    @WithMockUser("spring")
    public void allParts_returnCorrectView() throws Exception {
        this.supplierRepository.saveAndFlush(this.supplier);
        this.partRepository.saveAndFlush(this.part);

        this.mockMvc.perform(get("/parts/all"))
                .andExpect(view().name("all-parts"))
                .andExpect(model().attributeExists("parts"));
    }

    @Test
    public void allParts_withGuestUser() throws Exception {
        this.mockMvc.perform(get("/parts/all"))
                .andExpect(redirectedUrl("http://localhost/users/login"));
    }

    @Test
    @WithMockUser("spring")
    public void fetchParts_returnCorrectParts() throws Exception {
        Supplier supplier = this.supplierRepository.saveAndFlush(this.supplier);
        Part part = this.partRepository.saveAndFlush(this.part);

        String actual = this.mockMvc
                .perform(get("/parts/fetch"))
                .andReturn().getResponse().getContentAsString();


        String expected = String.format("[{\"id\":\"%s\",\"name\":\"%s\",\"price\":%s,\"supplier\":{\"id\":\"%s\",\"name\":\"%s\",\"isImporter\":%s}}]",
                part.getId(),
                part.getName(),
                String.format("%.2f", part.getPrice()).replace(",", "."),
                supplier.getId(),
                supplier.getName(),
                supplier.getIsImporter());
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void fetchParts_withGuestUser() throws Exception {
        this.mockMvc
                .perform(get("/parts/fetch"))
                .andExpect(redirectedUrl("http://localhost/users/login"));
    }

}
