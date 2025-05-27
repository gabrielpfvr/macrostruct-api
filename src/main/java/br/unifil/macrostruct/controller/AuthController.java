package br.unifil.macrostruct.controller;

import br.unifil.macrostruct.dto.LoginRequest;
import br.unifil.macrostruct.dto.LoginResponse;
import br.unifil.macrostruct.service.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final LoginService loginService;

    @PostMapping
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        return this.loginService.login(loginRequest);
    }

    @GetMapping("/check-token")
    public ResponseEntity<Void> checkToken(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).build();
        }

        return ResponseEntity.ok().build();
    }
}
