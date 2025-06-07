package br.unifil.macrostruct.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class MealRequest {

    @NotBlank
    private String description;
    @NotNull
    private LocalTime time;
    @NotBlank
    private List<MealFoodRequest> foodList;
    @NotNull
    private Integer ordination;
}
