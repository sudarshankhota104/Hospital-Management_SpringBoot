package com.hospital.management.service.impl;

import com.hospital.management.constants.ApplicationConstants;
import com.hospital.management.dto.PrescriptionRequestDTO;
import com.hospital.management.dto.PrescriptionResponseDTO;
import com.hospital.management.entity.Appointment;
import com.hospital.management.entity.Prescription;
import com.hospital.management.exception.InvalidRequestException;
import com.hospital.management.exception.ResourceNotFoundException;
import com.hospital.management.mapper.PrescriptionMapper;
import com.hospital.management.repository.AppointmentRepository;
import com.hospital.management.repository.PrescriptionRepository;
import com.hospital.management.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final AppointmentRepository appointmentRepository;

    @Override
    @Transactional
    public PrescriptionResponseDTO createPrescription(PrescriptionRequestDTO requestDTO) {
        log.debug("Creating prescription for appointment {}", requestDTO.getAppointmentId());

        Appointment appointment = appointmentRepository.findById(requestDTO.getAppointmentId())
                .orElseThrow(() -> new InvalidRequestException(String.format(ApplicationConstants.APPOINTMENT_NOT_FOUND, requestDTO.getAppointmentId())));

        Prescription prescription = PrescriptionMapper.toEntity(requestDTO);
        prescription.setAppointment(appointment);

        Prescription savedPrescription = prescriptionRepository.save(prescription);
        log.info("Prescription created successfully with id {}", savedPrescription.getPrescriptionId());
        return PrescriptionMapper.toDto(savedPrescription);
    }

    @Override
    @Transactional(readOnly = true)
    public PrescriptionResponseDTO getPrescriptionById(Long id) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ApplicationConstants.PRESCRIPTION_NOT_FOUND, id)));
        return PrescriptionMapper.toDto(prescription);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PrescriptionResponseDTO> getAllPrescriptions(Pageable pageable) {
        return prescriptionRepository.findAll(pageable).map(PrescriptionMapper::toDto);
    }

    @Override
    @Transactional
    public PrescriptionResponseDTO updatePrescription(Long id, PrescriptionRequestDTO requestDTO) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ApplicationConstants.PRESCRIPTION_NOT_FOUND, id)));

        Appointment appointment = appointmentRepository.findById(requestDTO.getAppointmentId())
                .orElseThrow(() -> new InvalidRequestException(String.format(ApplicationConstants.APPOINTMENT_NOT_FOUND, requestDTO.getAppointmentId())));

        prescription.setDiagnosis(requestDTO.getDiagnosis());
        prescription.setMedicines(requestDTO.getMedicines());
        prescription.setNotes(requestDTO.getNotes());
        prescription.setAppointment(appointment);

        Prescription updatedPrescription = prescriptionRepository.save(prescription);
        log.info("Prescription updated successfully with id {}", updatedPrescription.getPrescriptionId());
        return PrescriptionMapper.toDto(updatedPrescription);
    }

    @Override
    @Transactional
    public void deletePrescription(Long id) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ApplicationConstants.PRESCRIPTION_NOT_FOUND, id)));

        prescription.setDeleted(true);
        prescriptionRepository.save(prescription);
        log.info("Prescription soft-deleted successfully with id {}", id);
    }
}
