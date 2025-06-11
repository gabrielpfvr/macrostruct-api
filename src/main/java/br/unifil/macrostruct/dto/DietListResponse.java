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
    private Double weight;
    private Double height;
    private Integer tdee;
    private Integer totalMeals;

    public static DietListResponse from(Diet diet) {
        return DietListResponse.builder()
                .id(diet.getId())
                .description(diet.getDescription())
                .creationDate(diet.getCreationDate())
                .weight(diet.getWeight())
                .height(diet.getHeight())
                .tdee(diet.getTdee())
                .totalMeals(diet.getMeals().size())
                .build();
    }
}
