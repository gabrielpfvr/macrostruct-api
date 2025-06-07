package br.unifil.macrostruct.model;

import br.unifil.macrostruct.dto.DietRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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
public class Diet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "diet", cascade = CascadeType.ALL)
    private List<Meal> meals;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_diet_user")
    )
    private User user;

    @Column(nullable = false)
    private Double weight;

    @Column(nullable = false)
    private Double height;

    @Column(nullable = false)
    private Integer tdee;

    @Column(nullable = false)
    private LocalDateTime creationDate;

    public static Diet from(DietRequest dietRequest, User user) {
        return Diet.builder()
                .description(dietRequest.getDescription())
                .meals(Meal.from(dietRequest.getMeals()))
                .user(user)
                .weight(dietRequest.getWeight())
                .height(dietRequest.getHeight())
                .tdee(dietRequest.getTdeee())
                .creationDate(LocalDateTime.now())
                .build();
    }

    public static Diet fromUser(User user) {
        Diet diet = new Diet();
        diet.setUser(user);

        return diet;
    }
}
