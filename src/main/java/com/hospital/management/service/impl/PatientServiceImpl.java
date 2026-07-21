package com.hospital.management.service.impl;

import com.hospital.management.constants.ApplicationConstants;
import com.hospital.management.dto.PatientRequestDTO;
import com.hospital.management.dto.PatientResponseDTO;
import com.hospital.management.entity.Patient;
import com.hospital.management.exception.DuplicateResourceException;
import com.hospital.management.exception.InvalidRequestException;
import com.hospital.management.exception.ResourceNotFoundException;
import com.hospital.management.mapper.PatientMapper;
import com.hospital.management.repository.PatientRepository;
import com.hospital.management.service.PatientService;
import com.hospital.management.specification.PatientSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    @Transactional
    public PatientResponseDTO createPatient(PatientRequestDTO requestDTO) {
        log.debug("Creating patient with email {}", requestDTO.getEmail());
        if (patientRepository.existsByEmail(requestDTO.getEmail())) {
            throw new DuplicateResourceException(String.format(ApplicationConstants.DUPLICATE_RESOURCE, requestDTO.getEmail()));
        }

        Patient patient = PatientMapper.toEntity(requestDTO);
        Patient savedPatient = patientRepository.save(patient);
        log.info("Patient created successfully with id {}", savedPatient.getPatientId());
        return PatientMapper.toDto(savedPatient);
    }

    @Override
    @Transactional(readOnly = true)
    public PatientResponseDTO getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ApplicationConstants.PATIENT_NOT_FOUND, id)));
        return PatientMapper.toDto(patient);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PatientResponseDTO> getAllPatients(String name, String bloodGroup, Pageable pageable) {
        Page<Patient> patients = patientRepository.findAll(
                PatientSpecification.hasName(name)
                        .and(PatientSpecification.hasBloodGroup(bloodGroup)),
                pageable);
        return patients.map(PatientMapper::toDto);
    }

    @Override
    @Transactional
    public PatientResponseDTO updatePatient(Long id, PatientRequestDTO requestDTO) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ApplicationConstants.PATIENT_NOT_FOUND, id)));

        if (!patient.getEmail().equals(requestDTO.getEmail()) && patientRepository.existsByEmail(requestDTO.getEmail())) {
            throw new DuplicateResourceException(String.format(ApplicationConstants.DUPLICATE_RESOURCE, requestDTO.getEmail()));
        }

        patient.setPatientName(requestDTO.getPatientName());
        patient.setGender(requestDTO.getGender());
        patient.setBloodGroup(requestDTO.getBloodGroup());
        patient.setAge(requestDTO.getAge());
        patient.setPhone(requestDTO.getPhone());
        patient.setEmail(requestDTO.getEmail());
        patient.setAddress(requestDTO.getAddress());

        Patient updatedPatient = patientRepository.save(patient);
        log.info("Patient updated successfully with id {}", updatedPatient.getPatientId());
        return PatientMapper.toDto(updatedPatient);
    }

    @Override
    @Transactional
    public void deletePatient(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ApplicationConstants.PATIENT_NOT_FOUND, id)));

        if (patient.getAppointments() != null && !patient.getAppointments().isEmpty()) {
            throw new InvalidRequestException(String.format(ApplicationConstants.ACTIVE_APPOINTMENTS_EXIST, "Patient"));
        }

        patient.setDeleted(true);
        patientRepository.save(patient);
        log.info("Patient soft-deleted successfully with id {}", id);
    }
}
