package br.unifil.macrostruct.dto;

import br.unifil.macrostruct.model.Meal;
import br.unifil.macrostruct.model.MealFood;
import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
@Builder
public class MealResponse {

    private Integer id;
    private String description;
    private LocalTime time;
    private List<MealFoodResponse> foodList;
    private Double totalCalories;
    private Integer ordination;

    public static List<MealResponse> from(List<Meal> mealList) {
        return mealList.stream()
                .map(MealResponse::from)
                .toList();
    }

    private static MealResponse from(Meal meal) {
        return MealResponse.builder()
                .id(meal.getId())
                .description(meal.getDescription())
                .time(meal.getTime())
                .foodList(MealFoodResponse.from(meal.getFoodList()))
                .totalCalories(meal.getFoodList().stream().mapToDouble(MealFood::getCalories).sum())
                .ordination(meal.getOrdination())
                .build();
    }
}
