package com.hospital.management.repository;

import com.hospital.management.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>, JpaSpecificationExecutor<Patient> {

    List<Patient> findByPatientNameContainingIgnoreCase(String patientName);

    List<Patient> findByBloodGroup(String bloodGroup);

    boolean existsByEmail(String email);
}
