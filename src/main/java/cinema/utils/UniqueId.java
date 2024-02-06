package cinema.utils;

import java.util.UUID;

public class UniqueId {
    public static String generateUniqueId() {
        return UUID.randomUUID().toString();
    }
}