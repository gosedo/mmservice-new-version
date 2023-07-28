package com.mmsystem.property.dto;

import java.util.Objects;

public record MmsUserCredential(String userEmail, String password) {
    public MmsUserCredential {
        Objects.requireNonNull(userEmail);
        Objects.requireNonNull(password);
    }
}
