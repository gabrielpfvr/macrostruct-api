package br.unifil.macrostruct.service;

import br.unifil.macrostruct.model.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserResponse {

    private Long id;
    private String name;
    private String email;
    private LocalDate birthDate;

    public static UserResponse from(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .birthDate(user.getBirthDate())
                .build();
    }

}
