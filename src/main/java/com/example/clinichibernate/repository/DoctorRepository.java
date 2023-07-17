package com.example.clinichibernate.repository;


import com.example.clinichibernate.model.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository {

    void save(Doctor doctor);

    Optional<Doctor> findById(int id);

    List<Doctor> findByName(String name);

}
