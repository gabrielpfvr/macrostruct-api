package br.unifil.macrostruct.model;

import br.unifil.macrostruct.dto.MealFoodRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "meal_food")
@Entity
public class MealFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String foodDescription;

    @Column(nullable = false)
    private Integer portion;

    @Column(nullable = false)
    private Double carbohydrates;

    @Column(nullable = false)
    private Double protein;

    @Column(nullable = false)
    private Double totalFat;

    @Column(nullable = false)
    private Double calories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "meal_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_meal_food_meal")
    )
    private Meal meal;

    public static List<MealFood> from(List<MealFoodRequest> requestList) {
        return requestList.stream()
                .map(MealFood::from)
                .toList();
    }

    private static MealFood from(MealFoodRequest request) {
        return MealFood.builder()
                .foodDescription(request.getFoodDescription())
                .portion(request.getPortion())
                .carbohydrates(request.getCarbohydrates())
                .protein(request.getProtein())
                .totalFat(request.getTotalFat())
                .calories(request.getCalories())
                .build();
    }
}
