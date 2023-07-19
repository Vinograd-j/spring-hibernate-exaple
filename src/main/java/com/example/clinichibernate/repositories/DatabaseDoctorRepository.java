package com.example.clinichibernate.repositories;

import com.example.clinichibernate.repository.DoctorRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.example.clinichibernate.model.Doctor;
import java.util.List;
import java.util.Optional;

@Repository
public class DatabaseDoctorRepository implements DoctorRepository {

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
        String query = "SELECT d FROM Doctor d WHERE d.firstName LIKE :name OR d.lastName LIKE :name";

        return entityManager.createQuery(query, Doctor.class).setParameter("name", "%" + name + "%").getResultList();
    }
}
