package solidLab.p04_InterfaceSegregation.p02_identity.interfaces;

public interface Validator {
    boolean getRequireUniqueEmail();

    int getMinRequiredPasswordLength();

    int getMaxRequiredPasswordLength();
}
