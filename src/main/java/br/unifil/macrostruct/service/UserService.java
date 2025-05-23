package br.unifil.macrostruct.service;

import br.unifil.macrostruct.model.User;
import br.unifil.macrostruct.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public Optional<User> findByEmail(String email) {
        return this.repository.findByEmail(email);
    }
}
