package com.example.clinichibernate;

import com.example.clinichibernate.model.Doctor;
import com.example.clinichibernate.model.Patient;
import com.example.clinichibernate.model.Specialty;
import com.example.clinichibernate.repository.AppointmentRepository;
import com.example.clinichibernate.repository.DoctorRepository;
import com.example.clinichibernate.repository.PatientRepository;
import com.example.clinichibernate.repository.SpecialtyRepository;
import com.example.clinichibernate.service.DoctorService;
import com.example.clinichibernate.service.ReceptionService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class ClinicHibernateApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClinicHibernateApplication.class, args);
    }

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ReceptionService receptionService;


    private void prepare(){

        Specialty surgeonSpecialty = new Specialty(Specialty.SpecialtyType.SURGEON);

        Specialty dentistSpecialty = new Specialty(Specialty.SpecialtyType.DENTIST);

        specialtyRepository.save(surgeonSpecialty);
        specialtyRepository.save(dentistSpecialty);

        Doctor surgeon = new Doctor("Pasha", "Volya", surgeonSpecialty);

        Doctor dentist = new Doctor("Misha", "Ivanov", dentistSpecialty);

        doctorRepository.save(surgeon);
        doctorRepository.save(dentist);

    }


    @PostConstruct
    private void solve() {

        prepare();

        Patient patient = new Patient("I", "Ill");

        patientRepository.save(patient);

        Optional<Doctor> doctor = doctorRepository.findByName("Misha").stream().findFirst();

        if (doctor.isEmpty())
            throw new RuntimeException("Can`t find doctor");

        receptionService.createAnAppointment(patient, doctor.get());

        doctorService.observe(doctor.get());

    }

}
