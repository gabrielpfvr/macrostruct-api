package br.unifil.macrostruct.model;

import br.unifil.macrostruct.dto.MealRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Builder
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table
@Entity
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalTime time;

    @OneToMany(cascade = CascadeType.ALL)
    private List<MealFood> foodList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "diet_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_meal_diet")
    )
    private Diet diet;

    @Column(nullable = false)
    private Integer ordination;

    public static List<Meal> from(List<MealRequest> requestList) {
        return requestList.stream()
                .map(Meal::from)
                .toList();
    }

    private static Meal from(MealRequest request) {
        return Meal.builder()
                .description(request.getDescription())
                .time(request.getTime())
                .foodList(MealFood.from(request.getFoodList()))
                .ordination(request.getOrdination())
                .build();
    }
}
