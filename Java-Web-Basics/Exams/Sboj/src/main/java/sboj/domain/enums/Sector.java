package sboj.domain.enums;

import java.util.Arrays;

public enum Sector {

    Medicine, Car, Food, Domestic, Security;

    private static Sector[] vals = values();

    public static boolean containsSector(String sectorStr) {
        return Arrays.stream(vals).anyMatch(s -> s.name().equals(sectorStr));
    }
}
