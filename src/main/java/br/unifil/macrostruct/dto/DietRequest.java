package br.unifil.macrostruct.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class DietRequest {

    @NotBlank
    private String description;
    @NotEmpty
    private List<MealRequest> meals;
    @NotNull
    private Double weight;
    @NotNull
    private Double height;
    @NotNull
    private Integer tdeee;
}
