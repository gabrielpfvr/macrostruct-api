package br.unifil.macrostruct.dto;

import br.unifil.macrostruct.model.MealFood;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MealFoodResponse {

    private Integer id;
    private String foodDescription;
    private Integer portion;
    private Double carbohydrates;
    private Double protein;
    private Double totalFat;
    private Double calories;

    public static List<MealFoodResponse> from(List<MealFood> mealFoodList) {
        return mealFoodList.stream()
                .map(MealFoodResponse::from)
                .toList();
    }

    private static MealFoodResponse from(MealFood mealFood) {
        return MealFoodResponse.builder()
                .id(mealFood.getId())
                .foodDescription(mealFood.getFoodDescription())
                .portion(mealFood.getPortion())
                .carbohydrates(mealFood.getCarbohydrates())
                .protein(mealFood.getProtein())
                .totalFat(mealFood.getTotalFat())
                .calories(mealFood.getCalories())
                .build();
    }
}
