package com.hospital.management.service;

import com.hospital.management.dto.DoctorRequestDTO;
import com.hospital.management.dto.DoctorResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DoctorService {

    DoctorResponseDTO createDoctor(DoctorRequestDTO requestDTO);

    DoctorResponseDTO getDoctorById(Long id);

    Page<DoctorResponseDTO> getAllDoctors(String specialization, Long departmentId, Integer experience, Pageable pageable);

    DoctorResponseDTO updateDoctor(Long id, DoctorRequestDTO requestDTO);

    void deleteDoctor(Long id);
}
