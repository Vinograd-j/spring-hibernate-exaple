package com.example.clinichibernate.repositories;

import com.example.clinichibernate.model.Appointment;
import com.example.clinichibernate.model.Doctor;
import com.example.clinichibernate.model.Patient;
import com.example.clinichibernate.repository.PatientRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Component
@Repository
public class DatabaseAppointmentRepository implements com.example.clinichibernate.repository.AppointmentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    @Transactional
    public void appoint(Appointment appointment) {
        entityManager.persist(appointment);
    }

    @Override
    @Transactional
    public Optional<Patient> take(Doctor doctor) {

        String query = "DELETE FROM appointment WHERE time = (SELECT MIN(time) FROM appointment WHERE doctor_id = :doctor_id) RETURNING patient_id";

        Query nativeQuery = entityManager.createNativeQuery(query, Integer.class);
        nativeQuery.setParameter("doctor_id", doctor.getId());

        Integer id = (Integer) nativeQuery.getSingleResult();

        return patientRepository.findById(id);
    }

}
