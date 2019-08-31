package com.game.store.services.Base;

import com.game.store.domain.dtos.AddGameDto;
import com.game.store.domain.entities.User;

public interface GameService {
    String addGame(AddGameDto addGameDto);

    String editGame(String idStr, String... values);

    String deleteGame(String idStr);

    String getAllGames();

    String gameDetails(String gameTitle);

    String purchaseGame(String userIdStr, String gameIdStr);

    String loggedUserGames();

    void changeUser(User user);
}
