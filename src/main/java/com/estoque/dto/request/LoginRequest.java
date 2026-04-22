package com.estoque.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record LoginRequest(@NotEmpty(message = "email é obrigatorio") String email,
                           @NotEmpty(message = "senha é obrigatória") String password) {
}
