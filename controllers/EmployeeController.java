package com.szpital.szpital.controllers;

import com.szpital.szpital.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping(path= "/addEmployee")
    public @ResponseBody void addEmployee(@RequestParam String name,
                                               @RequestParam String surname,
                                             @RequestParam String pesel,
                                             @RequestParam String password){

        employeeService.addEmployee(name,surname,pesel,password);
    }

    @PostMapping(path= "/loginEmployee")
    public @ResponseBody Integer loginEmployee(@RequestParam String pesel,
                                               @RequestParam String password){

        return employeeService.loginEmployee(pesel, password);
    }
}
