package com.szpital.szpital.repositories;

import com.szpital.szpital.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends CrudRepository<Patient,Integer> {

    Patient findByPesel(String pesel);

}
