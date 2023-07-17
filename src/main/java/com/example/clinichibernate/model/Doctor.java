package com.example.clinichibernate.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
public class Doctor {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Getter
    @Column(name = "first_name")
    private String firstName;

    @Getter
    @Column(name = "last_name")
    private String lastName;

    @Getter
    @ManyToOne
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;

    protected Doctor() {}

    public Doctor(String firstName, String lastName, Specialty specialty) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialty = specialty;
    }
}
