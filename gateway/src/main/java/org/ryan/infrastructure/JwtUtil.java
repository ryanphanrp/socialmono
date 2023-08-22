package org.ryan.infrastructure;

import java.util.Objects;

public class JwtUtil {
    public static boolean isInValid(String token) {
        return Objects.isNull(token);
    }
}
