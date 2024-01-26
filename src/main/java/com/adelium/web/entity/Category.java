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

public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

//la relation qui dit que une formation peut appartenir à une ou plusieurs catégories
// Établir une relation bidirectionnelle avec l'entité `Training` avec l'annotation @OneToMany

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="category_course",
            joinColumns=@JoinColumn(name="category_id",referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name ="course_id",referencedColumnName = "id")
    )
    private Set<Course> courses = new HashSet<>();


}
