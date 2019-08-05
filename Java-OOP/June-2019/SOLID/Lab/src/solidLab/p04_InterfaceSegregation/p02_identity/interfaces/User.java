package solidLab.p04_InterfaceSegregation.p02_identity.interfaces;

public interface User {
    String getEmail();

    String getPasswordHash();

    void register(String username, String password);

    void login(String username, String password);

    void changePassword(String oldPass, String newPass);
}
