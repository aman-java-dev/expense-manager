package com.expmang.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double limitAmount;

    private String month;

    // One budget → one category
    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;
}