package br.unifil.macrostruct.controller;

import br.unifil.macrostruct.dto.LoginRequest;
import br.unifil.macrostruct.dto.LoginResponse;
import br.unifil.macrostruct.service.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        return this.loginService.login(loginRequest);
    }
}
