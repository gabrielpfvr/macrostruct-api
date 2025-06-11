package br.unifil.macrostruct.service;

import br.unifil.macrostruct.dto.LoginRequest;
import br.unifil.macrostruct.dto.LoginResponse;
import br.unifil.macrostruct.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final JwtService jwtService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest loginRequest) {
        Optional<User> user = this.userService.findByEmail(loginRequest.getUsername());

        if (user.isEmpty() || !this.isPasswordCorrect(loginRequest, user.get())) {
            throw new BadCredentialsException("Usuário/Senha incorretos!");
        }
        if (!user.get().isAccountNonLocked()) {
            throw new AuthenticationServiceException("Usuário inativo, por favor contate um administrador.");
        }

        String token = this.jwtService.generateToken(user.get());

        return LoginResponse.builder()
                .token(token)
                .expiresIn(jwtService.getExpiresIn())
                .build();
    }

    private boolean isPasswordCorrect(LoginRequest request, User user) {
        return this.passwordEncoder.matches(request.getPassword(), user.getPassword());
    }
}
