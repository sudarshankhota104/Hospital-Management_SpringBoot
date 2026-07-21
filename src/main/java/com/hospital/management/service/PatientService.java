package com.hospital.management.service;

import com.hospital.management.dto.PatientRequestDTO;
import com.hospital.management.dto.PatientResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PatientService {

    PatientResponseDTO createPatient(PatientRequestDTO requestDTO);

    PatientResponseDTO getPatientById(Long id);

    Page<PatientResponseDTO> getAllPatients(String name, String bloodGroup, Pageable pageable);

    PatientResponseDTO updatePatient(Long id, PatientRequestDTO requestDTO);

    void deletePatient(Long id);
}
