package com.adelium.web.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;



@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private boolean online;

    @Column(nullable = false)
    private double price;

    @ManyToMany(mappedBy = "courses")
    private Set<Category> categories = new HashSet<>();

    @OneToMany()
    private Set<SessionCourse> sessionCourse = new HashSet<>();
}

