package com.estoque.config;

import lombok.Builder;

public record JWTUserData(Long userId, String email) {
}
