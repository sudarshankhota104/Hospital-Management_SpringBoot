package com.hospital.management.mapper;

import com.hospital.management.dto.PatientRequestDTO;
import com.hospital.management.dto.PatientResponseDTO;
import com.hospital.management.entity.Patient;

public final class PatientMapper {

    private PatientMapper() {
        throw new UnsupportedOperationException("Mapper class should not be instantiated");
    }

    public static Patient toEntity(PatientRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        return Patient.builder()
                .patientName(dto.getPatientName())
                .gender(dto.getGender())
                .bloodGroup(dto.getBloodGroup())
                .age(dto.getAge())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .address(dto.getAddress())
                .build();
    }

    public static PatientResponseDTO toDto(Patient entity) {
        if (entity == null) {
            return null;
        }

        return PatientResponseDTO.builder()
                .patientId(entity.getPatientId())
                .patientName(entity.getPatientName())
                .gender(entity.getGender())
                .bloodGroup(entity.getBloodGroup())
                .age(entity.getAge())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .address(entity.getAddress())
                .deleted(entity.getDeleted())
                .build();
    }
}
