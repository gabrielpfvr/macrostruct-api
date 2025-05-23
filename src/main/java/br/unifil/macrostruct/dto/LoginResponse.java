package br.unifil.macrostruct.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {

    private String token;
    private long expiresIn;
}
