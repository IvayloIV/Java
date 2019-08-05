package solidLab.p04_InterfaceSegregation.p02_identity;

import solidLab.p04_InterfaceSegregation.p02_identity.interfaces.User;

public class AccountManager implements User {
    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public String getPasswordHash() {
        return null;
    }

    @Override
    public void register(String username, String password) {

    }

    @Override
    public void login(String username, String password) {

    }

    @Override
    public void changePassword(String oldPass, String newPass) {

    }
}
