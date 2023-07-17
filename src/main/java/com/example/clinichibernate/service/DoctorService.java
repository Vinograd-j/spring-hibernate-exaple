package com.example.clinichibernate.service;

import com.example.clinichibernate.model.Doctor;
import com.example.clinichibernate.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void observe(Doctor doctor){
        appointmentRepository.take(doctor).ifPresentOrElse(
                patient ->
                        System.out.println("Observe patient: "+patient.getFirstName() + " " + patient.getLastName()),

                () ->
                        System.out.println("Queue is empty. I go to sleep...")
        );
    }

}
