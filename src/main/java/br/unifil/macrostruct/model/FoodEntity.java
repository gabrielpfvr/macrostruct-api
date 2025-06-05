package br.unifil.macrostruct.model;

import br.unifil.macrostruct.dto.FoodEntityRequest;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "food")
@Entity
public class FoodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer servingSize;

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
            name = "user_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_food_user")
    )
    private User user;

    public static FoodEntity from(FoodEntityRequest request, User user) {
        FoodEntity food = new FoodEntity();
        food.setDescription(request.getDescription());
        food.setServingSize(request.getServingSize());
        food.setCarbohydrates(request.getCarbohydrates());
        food.setProtein(request.getProtein());
        food.setTotalFat(request.getTotalFat());
        food.setCalories(request.getCalories());
        food.setUser(user);

        return food;
    }

    public static FoodEntity fromUser(User user) {
        FoodEntity food = new FoodEntity();
        food.setUser(user);

        return food;
    }

}
