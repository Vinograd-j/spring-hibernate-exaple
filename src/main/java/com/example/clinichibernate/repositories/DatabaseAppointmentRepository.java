package com.example.clinichibernate.repositories;

import com.example.clinichibernate.model.Appointment;
import com.example.clinichibernate.model.Doctor;
import com.example.clinichibernate.model.Patient;
import com.example.clinichibernate.repository.AppointmentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DatabaseAppointmentRepository implements AppointmentRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @Transactional
    public void appoint(Appointment appointment) {
        entityManager.persist(appointment);
    }

    @Override
    @Transactional
    public Optional<Patient> take(Doctor doctor) {

        String appointmentQuery = "SELECT a FROM Appointment a WHERE a.time = (SELECT MIN(sub.time) FROM Appointment sub WHERE sub.doctor = :doctor)";

        Optional<Appointment> desired = entityManager.createQuery(appointmentQuery, Appointment.class).setParameter("doctor", doctor).getResultList().stream().findFirst();

        if(desired.isEmpty())
            return Optional.empty();

        Appointment appointment = desired.get();

        entityManager.remove(appointment);

        return Optional.of(appointment.getPatient());
    }

}