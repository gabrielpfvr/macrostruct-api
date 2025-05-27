package br.unifil.macrostruct.service;

import br.unifil.macrostruct.dto.UserRequest;
import br.unifil.macrostruct.exception.ValidationException;
import br.unifil.macrostruct.model.User;
import br.unifil.macrostruct.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public Optional<User> findByEmail(String email) {
        return this.repository.findByEmail(email);
    }

    public void save(UserRequest userRequest) {
        this.validateExistingUser(userRequest.getEmail());
        User user = User.from(userRequest);
        this.setEncodedPassword(user, userRequest.getPassword());

        this.repository.save(user);
    }

    private void validateExistingUser(String email) {
        if (this.findByEmail(email).isPresent()) {
            throw new ValidationException("Email já cadastrado!");
        }
    }

    private void setEncodedPassword(User user, String password) {
        user.setPassword(this.passwordEncoder.encode(password));
    }
}
