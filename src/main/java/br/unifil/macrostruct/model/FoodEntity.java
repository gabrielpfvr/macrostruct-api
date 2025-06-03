package br.unifil.macrostruct.model;

import br.unifil.macrostruct.enums.Type;
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

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type mainType;

}
