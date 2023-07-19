package com.example.clinichibernate.repository;

import com.example.clinichibernate.model.Appointment;
import com.example.clinichibernate.model.Doctor;
import com.example.clinichibernate.model.Patient;

import java.util.Optional;

public interface AppointmentRepository {

    void appoint(Appointment appointment);

    Optional<Patient> take(Doctor doctor);

}
