package com.szpital.szpital.repositories;

import com.szpital.szpital.Appointment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppoitmentRepository extends CrudRepository<Appointment, Integer> {

}
