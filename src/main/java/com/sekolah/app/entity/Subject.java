package com.sekolah.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subject_name")
    private String subjectName;

    @Column(name = "grade")
    private String grade;

    @Column(name = "major")
    private String major;

    @ManyToMany(mappedBy = "subjects")
    @JsonBackReference
    private List<User> users;
}
