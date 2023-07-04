package org.ryan.domain.constant;

public enum UserStatus {
    ACTIVE,
    NOT_VERIFIED,
    DEACTIVATED;

    public static UserStatus ofDefault() {
        return NOT_VERIFIED;
    }
}
