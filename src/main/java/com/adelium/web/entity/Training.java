package com.adelium.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;


    //relation pour dire qu'une formation peut avoir plusieurs categoris
    // Ajoutez le nouveau champ category
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


        // Établir une relation bidirectionnelle avec l'entité `Session` avec l'annotation @OneToMany
    @OneToMany(mappedBy = "training", cascade = CascadeType.ALL)
    private Set<SessionTraining> sessionTrainings = new HashSet<>();
}
