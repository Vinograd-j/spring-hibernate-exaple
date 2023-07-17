package com.example.clinichibernate.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.example.clinichibernate.model.Doctor;
import java.util.List;
import java.util.Optional;

@Repository
public class DatabaseDoctorRepository implements com.example.clinichibernate.repository.DoctorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Doctor doctor) {
        entityManager.persist(doctor);
    }

    @Override
    @Transactional
    public Optional<Doctor> findById(int id) {
        return Optional.ofNullable(entityManager.find(Doctor.class, id));
    }

    @Override
    @Transactional
    public List<Doctor> findByName(String name) {
        String query = "SELECT id, first_name, last_name , specialty_id FROM doctor WHERE first_name LIKE :name OR last_name LIKE :name";

        Query nativeQuery = entityManager.createNativeQuery(query, Doctor.class);
        nativeQuery.setParameter("name", "%"+name+"%");

        return nativeQuery.getResultList();
    }
}
