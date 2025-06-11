package br.unifil.macrostruct.dto;

import br.unifil.macrostruct.model.FoodEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FoodEntityResponse {

    private Integer id;
    private String description;
    private Integer servingSize;
    private Double carbohydrates;
    private Double protein;
    private Double totalFat;
    private Double calories;

    public static FoodEntityResponse from(FoodEntity foodEntity) {
        return FoodEntityResponse.builder()
                .id(foodEntity.getId())
                .description(foodEntity.getDescription())
                .servingSize(foodEntity.getServingSize())
                .carbohydrates(foodEntity.getCarbohydrates())
                .protein(foodEntity.getProtein())
                .totalFat(foodEntity.getTotalFat())
                .calories(foodEntity.getCalories())
                .build();
    }
}
