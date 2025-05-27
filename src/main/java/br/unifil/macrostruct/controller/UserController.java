package br.unifil.macrostruct.controller;

import br.unifil.macrostruct.dto.UserRequest;
import br.unifil.macrostruct.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody UserRequest dto) {
        this.userService.save(dto);
    }
}
