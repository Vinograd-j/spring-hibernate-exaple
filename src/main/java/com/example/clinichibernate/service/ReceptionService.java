package com.example.clinichibernate.service;

import com.example.clinichibernate.model.Appointment;
import com.example.clinichibernate.model.Doctor;
import com.example.clinichibernate.model.Patient;
import com.example.clinichibernate.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ReceptionService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void createAnAppointment(Patient patient, Doctor doctor){
        appointmentRepository.appoint(new Appointment(patient, doctor, LocalDateTime.now()));
    }

}
