package com.szpital.szpital.repositories;

import com.szpital.szpital.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee,Integer> {

    Employee findByPesel(String pesel);

}
