package com.szpital.szpital;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@RequiredArgsConstructor
@Table
public class Appointment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    String nameOfAppointment;
    String dateOfAppointment;
    String hourOfAppointment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="patient_id")
    Patient patient;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameOfAppointment() {
        return nameOfAppointment;
    }

    public void setNameOfAppointment(String nameOfAppointment) {
        this.nameOfAppointment = nameOfAppointment;
    }

    public String getDateOfAppointment() {
        return dateOfAppointment;
    }

    public void setDateOfAppointment(String dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
    }

    public String getHourOfAppointment() {
        return hourOfAppointment;
    }

    public void setHourOfAppointment(String hourOfAppointment) {
        this.hourOfAppointment = hourOfAppointment;
    }

    @JsonBackReference
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
