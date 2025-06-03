package br.unifil.macrostruct.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FoodEntityRequest {

    @NotBlank
    private String description;
    @NotNull
    private Integer servingSize;
    @NotNull
    private Double carbohydrates;
    @NotNull
    private Double protein;
    @NotNull
    private Double totalFat;
    private Double calories;
}
