package com.szpital.szpital.controllers;

import com.szpital.szpital.Appointment;
import com.szpital.szpital.services.AppoitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class AppotimentController {

    private final AppoitmentService appoitmentService;

    @PostMapping(path = "/addApp")
    public @ResponseBody void addNewAppoitment (@RequestParam String nameOfAppointment
            , @RequestParam String dateOfAppointment
            ,@RequestParam String hourOfAppointment){

        appoitmentService.addNewAppoitment(nameOfAppointment, dateOfAppointment, hourOfAppointment);

    }

    @GetMapping(path = "/getApp")
    public @ResponseBody Iterable<Appointment> getAppoitments(){

        return appoitmentService.getAppoitments();

    }

    @PostMapping(path = "/getPatientAppoitments")
    public @ResponseBody Iterable<Appointment> getPatientAppoitments(@RequestParam String pesel){

        return appoitmentService.getPatientAppoitments(pesel);
    }

}
