package br.unifil.macrostruct.dto;

import lombok.Data;

@Data
public class MealFoodRequest {

    private String foodDescription;
    private Integer portion;
    private Double carbohydrates;
    private Double protein;
    private Double totalFat;
    private Double calories;
}
