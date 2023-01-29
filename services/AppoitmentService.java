package com.szpital.szpital.services;

import com.szpital.szpital.Appointment;
import com.szpital.szpital.repositories.AppoitmentRepository;
import com.szpital.szpital.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppoitmentService {

    private final AppoitmentRepository appoitmentRepository;
    private final PatientRepository patientRepository;
    public Iterable<Appointment> getAppoitments() {

        List<Appointment> appointmentList = new ArrayList<>();
        List<Appointment> avaibleAppoitmentList = new ArrayList<>();
        Iterable<Appointment> appointments = appoitmentRepository.findAll();
        appointments.forEach(appointmentList::add);
        appointmentList.stream().filter(appointment -> appointment.getPatient() == null)
                .forEach(avaibleAppoitmentList::add);
        return avaibleAppoitmentList;
    }

    public String addNewAppoitment(String nameOfAppointment,
                                   String dateOfAppointment,
                                   String hourOfAppointment) {

        Appointment appointment = new Appointment();

        appointment.setNameOfAppointment(nameOfAppointment);
        appointment.setDateOfAppointment(dateOfAppointment);
        appointment.setHourOfAppointment(hourOfAppointment);

        appoitmentRepository.save(appointment);

        return "Wizyta ";
    }

    public List<Appointment> getPatientAppoitments(String pesel) {

        Long patientID = patientRepository.findByPesel(pesel).getId();

        Iterable<Appointment> appointments = appoitmentRepository.findAll();
        List<Appointment> patientAppoitments = new ArrayList<>();
        appointments.forEach(patientAppoitments::add);
        List<Appointment> finalListAppoitments = new ArrayList<>();
        patientAppoitments.stream().filter(appointment -> appointment.getPatient() != null)
                .filter(appointment -> appointment.getPatient().getId().equals(patientID))
                .forEach(finalListAppoitments::add);
        return finalListAppoitments;

    }
}
