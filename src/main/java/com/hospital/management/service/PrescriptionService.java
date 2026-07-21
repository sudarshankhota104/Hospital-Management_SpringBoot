package com.hospital.management.service;

import com.hospital.management.dto.PrescriptionRequestDTO;
import com.hospital.management.dto.PrescriptionResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PrescriptionService {

    PrescriptionResponseDTO createPrescription(PrescriptionRequestDTO requestDTO);

    PrescriptionResponseDTO getPrescriptionById(Long id);

    Page<PrescriptionResponseDTO> getAllPrescriptions(Pageable pageable);

    PrescriptionResponseDTO updatePrescription(Long id, PrescriptionRequestDTO requestDTO);

    void deletePrescription(Long id);
}
