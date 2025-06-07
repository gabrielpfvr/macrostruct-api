package br.unifil.macrostruct.dto;

import br.unifil.macrostruct.model.Diet;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class DietListResponse {

    private Integer id;
    private String description;
    private LocalDateTime creationDate;

    public static DietListResponse from(Diet diet) {
        return DietListResponse.builder()
                .id(diet.getId())
                .description(diet.getDescription())
                .creationDate(diet.getCreationDate())
                .build();
    }
}
