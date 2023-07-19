package com.example.clinichibernate.repositories;

import com.example.clinichibernate.model.Doctor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import com.example.clinichibernate.model.Patient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DatabasePatientRepository implements com.example.clinichibernate.repository.PatientRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Patient patient) {
        entityManager.persist(patient);
    }

    @Override
    @Transactional
    public Optional<Patient> findById(int id) {
        return Optional.ofNullable(entityManager.find(Patient.class, id));
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<Patient> findByName(String name) {
        String query = "SELECT p FROM Patient p WHERE p.firstName LIKE :name OR p.lastName LIKE :name";

        Query nativeQuery = entityManager.createNativeQuery(query, Doctor.class);
        nativeQuery.setParameter("name", "%"+name+"%");

        return nativeQuery.getResultList();
    }
}
