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
public class SessionTraining {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "training_id")
    private Training training;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "session_training_session_course",
            joinColumns = @JoinColumn(name = "session_training_id"),
            inverseJoinColumns = @JoinColumn(name = "session_course_id")
    )
    private Set<SessionCourse> sessionCourses = new HashSet<>();
}

