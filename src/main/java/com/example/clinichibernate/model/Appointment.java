package com.example.clinichibernate.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Getter
    @OneToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Getter
    @OneToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    private LocalDateTime time;

    protected Appointment() {}

    public Appointment(Patient patient, Doctor doctor, LocalDateTime time) {
        this.patient = patient;
        this.doctor = doctor;
        this.time = time;
    }
}
