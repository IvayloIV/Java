package org.softuni.cardealer.web.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.cardealer.repository.CustomerRepository;
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
public class CustomersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @Before
    public void config() {
        this.customerRepository.deleteAll();
    }

    @Test
    @WithMockUser("spring")
    public void addCustomer_withValidCustomer_1CustomerSaved() throws Exception {
        this.mockMvc
            .perform(
                post("/customers/add")
                    .param("name", "Pesho")
                    .param("birthDate", "1933-03-01")
                    .param("isYoungDriver", "true")
            );

        Assert.assertEquals(1, this.customerRepository.count());
    }

    @Test(expected = Exception.class)
    @WithMockUser("spring")
    public void addCustomer_withInvalidCustomer_throwException() throws Exception {
        this.mockMvc.perform(post("/customers/add"));
    }

    @Test
    @WithMockUser("spring")
    public void addCustomer_withValidCustomer_redirectCorrect() throws Exception {
        this.mockMvc
            .perform(
                post("/customers/add")
                    .param("name", "Pesho")
                    .param("birthDate", "1933-03-01")
                    .param("isYoungDriver", "true")
            )
            .andExpect(redirectedUrl("all"));
    }

    @Test
    public void addCustomer_withGuestUser_redirectCorrect() throws Exception {
        this.mockMvc
            .perform(
                post("/customers/add")
                    .param("name", "Pesho")
                    .param("birthDate", "1933-03-01")
                    .param("isYoungDriver", "true")
            )
            .andExpect(redirectedUrl("http://localhost/users/login"));
    }

    @Test
    @WithMockUser("spring")
    public void allCustomers_toReturnCorrectView() throws Exception {
        this.mockMvc
            .perform(get("/customers/all"))
            .andExpect(view().name("all-customers"))
            .andExpect(model().attributeExists("customers"));
    }

    @Test
    public void allCustomers_withGuestUser_redirectCorrect() throws Exception {
        this.mockMvc.perform(get("/customers/all"))
                .andExpect(redirectedUrl("http://localhost/users/login"));
    }
}
