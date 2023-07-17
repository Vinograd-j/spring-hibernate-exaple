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

    @PostConstruct
    private void solve() {

        Specialty specialty = new Specialty(Specialty.SpecialtyType.SURGEON);

        specialtyRepository.save(specialty);

        Doctor doctor = new Doctor("Pasha", "Volya", specialty);

        doctorRepository.save(doctor);

        Doctor doctor1 = new Doctor("Pasha", "123", specialty);

        doctorRepository.save(doctor1);

        Patient patient = new Patient("I", "Ill");

        patientRepository.save(patient);

        List<Doctor> pasha = doctorRepository.findByName("Pasha");

        System.out.println(pasha.get(1).getLastName());

        receptionService.createAnAppointment(patient, doctor);

        doctorService.observe(doctor);

    }

}
