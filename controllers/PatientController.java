package com.szpital.szpital.controllers;

import com.szpital.szpital.Patient;
import com.szpital.szpital.services.AppoitmentService;
import com.szpital.szpital.services.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class PatientController {


    private final PatientService patientService;

    @PostMapping(path = "/addPatient")
    public String addPatient(@RequestParam String name,
                                           @RequestParam String surname,
                                           @RequestParam String DOB,
                                           @RequestParam String pesel,
                                            @RequestParam String password){

        return patientService.addPatient(name,surname,DOB,pesel,password);

    }

    @GetMapping(path = "/getPatients")
    public @ResponseBody Iterable<Patient> getPatietnts()
    {
        return patientService.getPatients();
    }

    @PostMapping(path = "/getPatientsByPesel")
    public @ResponseBody Patient getPatientByPesel(@RequestParam String pesel){

        return patientService.getPatientByPesel(pesel);

    }

    @PostMapping(path = "/editPatient")
    public @ResponseBody String editPatient(@RequestParam String whatToChange,
                                             @RequestParam String value,
                                             @RequestParam String pesel){
        return patientService.editPatient(whatToChange, value, pesel);

    }

    @PostMapping(path = "/addAppoitment")
    public @ResponseBody String addAppoitment(@RequestParam String appoitmentId,
                                              @RequestParam String pesel){

        return patientService.addAppoitment(appoitmentId,pesel);

    }

    @PostMapping(path= "/loginUser")
    public @ResponseBody Integer loginUser(@RequestParam String pesel,
                                          @RequestParam String password){

        return patientService.loginUser(pesel, password);
    }


}
