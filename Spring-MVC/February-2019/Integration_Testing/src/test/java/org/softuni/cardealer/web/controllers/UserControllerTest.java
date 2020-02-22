package org.softuni.cardealer.web.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.cardealer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void config() {
        this.userRepository.deleteAll();
    }

    @Test
    public void login_returnCorrectVIew() throws Exception {
        this.mockMvc
                .perform(get("/users/login"))
                .andExpect(view().name("login"));
    }

    @Test
    public void register_returnCorrectVIew() throws Exception {
        this.mockMvc
                .perform(get("/users/register"))
                .andExpect(view().name("register"));
    }

    @Test
    public void register_withValidUser_SaveUsed() throws Exception {
        this.mockMvc
                .perform(
                        post("/users/register")
                        .param("username", "pesho")
                        .param("password", "1234")
                        .param("confirmPassword", "1234")
                        .param("email", "pesho@abv.bg")
                );

        Assert.assertEquals(1, this.userRepository.count());
    }

    @Test(expected = Exception.class)
    public void register_withInvalidUser_throwException() throws Exception {
        this.mockMvc.perform(post("/users/register"));
    }

    @Test
    public void register_withValidUser_redirectCorrect() throws Exception {
        this.mockMvc
                .perform(
                        post("/users/register")
                                .param("username", "pesho")
                                .param("password", "1234")
                                .param("confirmPassword", "1234")
                                .param("email", "pesho@abv.bg")
                )
                .andExpect(redirectedUrl("login"));
    }
}
