package com.example.clinichibernate.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
public class Specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private SpecialtyType type;

    protected Specialty() {}

    public Specialty(SpecialtyType type){
        this.type = type;
    }

    public enum SpecialtyType{

        DENTIST, TRAUMATOLOGIST, SURGEON

    }

}


