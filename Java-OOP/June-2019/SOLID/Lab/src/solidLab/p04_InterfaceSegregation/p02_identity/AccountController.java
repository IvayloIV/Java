package solidLab.p04_InterfaceSegregation.p02_identity;

import solidLab.p04_InterfaceSegregation.p02_identity.interfaces.Account;
import solidLab.p04_InterfaceSegregation.p02_identity.interfaces.User;

public class AccountController implements Account {
    @Override
    public Iterable<User> getAllUsersOnline() {
        return null;
    }

    @Override
    public Iterable<User> getAllUsers() {
        return null;
    }

    @Override
    public User getUserByName(String name) {
        return null;
    }
}
