package com.example.clinichibernate.repository;

import com.example.clinichibernate.model.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientRepository {

    void save(Patient patient);

    Optional<Patient> findById(int id);

    List<Patient> findByName(String name);

}
