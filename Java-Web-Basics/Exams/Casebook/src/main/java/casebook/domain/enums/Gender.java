package casebook.domain.enums;

import java.util.Arrays;

public enum Gender {

    Male, Female;
    
    public static Gender[] vals = values();
    
    public static boolean contains(String gender) {
        return Arrays.stream(vals).anyMatch(v -> v.name().equals(gender));
    }
}
