package br.unifil.macrostruct.dto;

import br.unifil.macrostruct.model.Diet;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DietResponse {

    private Integer id;
    private String description;
    private List<MealResponse> meals;
    private Double weight;
    private Double height;
    private Integer tdee;
    private Double totalCalories;

    public static DietResponse from(Diet diet) {
        List<MealResponse> meals = MealResponse.from(diet.getMeals());
        return DietResponse.builder()
                .id(diet.getId())
                .description(diet.getDescription())
                .meals(meals)
                .weight(diet.getWeight())
                .height(diet.getHeight())
                .tdee(diet.getTdee())
                .totalCalories(meals.stream().mapToDouble(MealResponse::getTotalCalories).sum())
                .build();
    }
}
