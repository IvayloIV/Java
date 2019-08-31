package com.game.store.controllers;

import com.game.store.domain.dtos.AddGameDto;
import com.game.store.domain.dtos.UserLoginDto;
import com.game.store.domain.dtos.UserRegisterDto;
import com.game.store.services.Base.GameService;
import com.game.store.services.Base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

@Controller
public class BaseController implements CommandLineRunner {
    private final UserService userService;
    private final GameService gameService;
    private Scanner scanner;

    @Autowired
    public BaseController(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
        this.scanner = new Scanner(System.in);
    }


    @Override
    public void run(String... args) throws Exception {
        while (true) {
            try {
                String[] tokens = this.scanner.nextLine().split("\\|");
                String command = tokens[0];
                String result = null;

                switch (command) {
                    case "RegisterUser":
                        UserRegisterDto userRegisterDto = new UserRegisterDto(tokens[1], tokens[2], tokens[3], tokens[4]);
                        result = this.userService.registerUser(userRegisterDto);
                        break;
                    case "LoginUser":
                        UserLoginDto userLoginDto = new UserLoginDto(tokens[1], tokens[2]);
                        result = this.userService.loginUser(userLoginDto);
                        break;
                    case "Logout":
                        result = this.userService.logout();
                        break;
                    case "AddGame":
                        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        AddGameDto addGameDto = new AddGameDto(tokens[1], new BigDecimal(tokens[2]), Double.parseDouble(tokens[3]), tokens[4], tokens[5], tokens[6], LocalDate.parse(tokens[7], dateTimeFormatter));
                        result = this.gameService.addGame(addGameDto);
                        break;
                    case "EditGame":
                        result = this.gameService.editGame(tokens[1], Arrays.stream(tokens).skip(2).toArray(String[]::new));
                        break;
                    case "DeleteGame":
                        result = this.gameService.deleteGame(tokens[1]);
                        break;
                    case "AllGames":
                        result = this.gameService.getAllGames();
                        break;
                    case "DetailGame":
                        result = this.gameService.gameDetails(tokens[1]);
                        break;
                    case "PurchaseGame":
                        result = this.gameService.purchaseGame(tokens[1], tokens[2]);
                        break;
                    case "OwnedGames":
                        result = this.gameService.loggedUserGames();
                        break;
                }

                System.out.println(result);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
