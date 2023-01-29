package com.szpital.szpital.services;

import com.szpital.szpital.Appointment;
import com.szpital.szpital.Patient;
import com.szpital.szpital.repositories.AppoitmentRepository;
import com.szpital.szpital.repositories.PatientRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final AppoitmentRepository appoitmentRepository;
    public String addPatient(String name, String surname, String dob, String pesel, String password) {



        Patient patient = new Patient();
        patient.setName(name);
        patient.setSurname(surname);
        patient.setDOB(dob);
        patient.setPesel(pesel);
        patient.setPassword(hashPassword(password));
        patientRepository.save(patient);

        return "Pacjent został dodany";

    }

    public Iterable<Patient> getPatients() {

        return patientRepository.findAll();
    }

    public Patient getPatientByPesel(String pesel) {

        return patientRepository.findByPesel(pesel);
    }

    public String editPatient(String whatToChange, String value, String pesel) {

        Patient patient = patientRepository.findByPesel(pesel);

        switch (whatToChange) {
            case "name" -> patient.setName(value);
            case "surname" -> patient.setSurname(value);
            case "DOB" -> patient.setDOB(value);
            case "pesel" -> patient.setPesel(value);
            default -> {
                return "Nie znaleziono Pacjenta o podanym Peselu";
            }
        }
        patientRepository.save(patient);

        return "Zmieniono " + value;
    }

    public String addAppoitment(String appoitmentId, String pesel) {

        Patient patient = patientRepository.findByPesel(pesel);
        Optional<Appointment> appo = appoitmentRepository.findById(Integer.parseInt(appoitmentId));
        Appointment appotiment = appo.get();
        appotiment.setPatient(patient);
        appoitmentRepository.save(appotiment);

        return "Zapisano na Wizyte: " ;
    }

    public int loginUser(String pesel, String password) {

        Patient patient = patientRepository.findByPesel(pesel);
        String pas = patient.getPassword();
        return checkPass(password,pas);
    }

    public static String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static int checkPass(String plainPassword, String hashedPassword) {
        if (BCrypt.checkpw(plainPassword, hashedPassword))
            return 1;
        else
            return 0;
    }


}
