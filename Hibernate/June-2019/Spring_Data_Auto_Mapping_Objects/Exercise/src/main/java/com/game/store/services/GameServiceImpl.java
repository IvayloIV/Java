package com.game.store.services;

import com.game.store.domain.dtos.AddGameDto;
import com.game.store.domain.entities.Game;
import com.game.store.domain.entities.Role;
import com.game.store.domain.entities.User;
import com.game.store.repositories.GameRepository;
import com.game.store.repositories.UserRepository;
import com.game.store.services.Base.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;
    private User loggedUser;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, UserRepository userRepository) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    public String addGame(AddGameDto addGameDto) {
        StringBuilder sb = new StringBuilder();

        if (this.loggedUser == null || !this.loggedUser.getRole().equals(Role.ADMIN)) {
            return sb.append("Logged user must be admin!").toString();
        }
        Game game = this.modelMapper.map(addGameDto, Game.class);
        Set<ConstraintViolation<Game>> violations = this.validator.validate(game);

        if (violations.size() > 0) {
            addViolations(sb, violations);
        } else {
            this.gameRepository.saveAndFlush(game);
            sb.append(String.format("Added %s", game.getTitle()));
        }

        return sb.toString().trim();
    }

    public String editGame(String idStr, String... values) {
        StringBuilder sb = new StringBuilder();
        if (this.loggedUser == null || !this.loggedUser.getRole().equals(Role.ADMIN)) {
            return sb.append("Logged user must be admin!").toString();
        }

        Game game = this.gameRepository.findById(Integer.parseInt(idStr)).orElse(null);

        if (game == null) {
            return sb.append("Game not exist.").toString();
        }

        for (String value : values) {
            changeProps(game, value);
        }
        Set<ConstraintViolation<Game>> violations = this.validator.validate(game);
        if (violations.size() > 0) {
            addViolations(sb, violations);
        } else {
            this.gameRepository.saveAndFlush(game);
            sb.append(String.format("Edited %s", game.getTitle()));
        }

        return sb.toString().trim();
    }

    public String deleteGame(String idStr) {
        if (this.loggedUser == null || !this.loggedUser.getRole().equals(Role.ADMIN)) {
            return "Logged user must be admin!";
        }

        Game game = this.gameRepository.findById(Integer.parseInt(idStr)).orElse(null);

        if (game == null) {
            return "Game not exist.";
        }

        this.gameRepository.delete(game);
        return "Deleted " + game.getTitle();
    }

    private void addViolations(StringBuilder sb, Set<ConstraintViolation<Game>> violations) {
        for (ConstraintViolation<Game> violation : violations) {
            sb.append(violation.getMessage()).append(System.lineSeparator());
        }
    }

    public String getAllGames() {
        List<Game> games = this.gameRepository.findAll();
        return games.stream().map(g -> String.format("%s %.2f",
                g.getTitle(),
                g.getPrice()))
                .collect(Collectors.joining(System.lineSeparator()));
    }

    public String gameDetails(String gameTitle) {
        Game game = this.gameRepository.findByTitle(gameTitle).orElse(null);

        if (game == null) {
            return "Game not exist.";
        }

        return game.toString().trim();
    }

    @Transactional
    @Modifying
    public String purchaseGame(String userIdStr, String gameIdStr) {
        User user = this.userRepository.findById(Integer.parseInt(userIdStr))
                .orElseThrow(() -> new IllegalArgumentException("User does not exist."));

        Game game = this.gameRepository.findById(Integer.parseInt(gameIdStr))
                .orElseThrow(() -> new IllegalArgumentException("Game does not exist."));

        user.getGames().add(game);
        this.userRepository.saveAndFlush(user);
        return "Game added successfully.";
    }

    @Override
    public String loggedUserGames() {
        StringBuilder sb = new StringBuilder();
        if (this.loggedUser == null) {
            return "First login please.";
        }

        String userEmail = this.loggedUser.getEmail();
        User user = this.userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not exist!"));

        for (Game game : user.getGames()) {
            sb.append(game.getTitle()).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    private void changeProps(Game game, String value) {
        String[] tokens = value.split("=");
        String prop = tokens[0];

        switch (prop) {
            case "title":
                game.setTitle(tokens[1]);
                break;
            case "price":
                game.setPrice(new BigDecimal(tokens[1]));
                break;
            case "size":
                game.setSize(Double.parseDouble(tokens[1]));
                break;
            case "trailer":
                game.setTrailer(tokens[1]);
                break;
            case "thumbnailURL":
                game.setImageThumbnail(tokens[1]);
                break;
            case "description":
                game.setDescription(tokens[1]);
                break;
            case "releaseDate":
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate date = LocalDate.parse(tokens[1], dateTimeFormatter);
                game.setReleaseDate(date);
                break;
        }
    }

    public void changeUser(User user) {
        this.loggedUser = user;
    }
}
