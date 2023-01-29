package com.szpital.szpital.services;

import com.szpital.szpital.Employee;
import com.szpital.szpital.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Integer loginEmployee(String pesel, String password) {
       String employeePassword = employeeRepository.findByPesel(pesel).getPassword();
       return checkPass(password,employeePassword);
    }

    public void addEmployee(String name, String surname, String pesel, String password) {

        Employee employee = new Employee();
        employee.setName(name);
        employee.setSurname(surname);
        employee.setPesel(pesel);
        employee.setPassword(hashPassword(password));

        employeeRepository.save(employee);

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
