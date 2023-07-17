package com.example.clinichibernate.repositories;

import com.example.clinichibernate.model.Specialty;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class DatabaseSpecialtyRepository implements com.example.clinichibernate.repository.SpecialtyRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Specialty specialty) {
        entityManager.persist(specialty);
    }
}
