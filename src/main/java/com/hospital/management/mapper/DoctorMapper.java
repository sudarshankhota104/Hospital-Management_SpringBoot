package com.hospital.management.mapper;

import com.hospital.management.dto.DoctorRequestDTO;
import com.hospital.management.dto.DoctorResponseDTO;
import com.hospital.management.entity.Doctor;

public final class DoctorMapper {

    private DoctorMapper() {
        throw new UnsupportedOperationException("Mapper class should not be instantiated");
    }

    public static Doctor toEntity(DoctorRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        return Doctor.builder()
                .doctorName(dto.getDoctorName())
                .specialization(dto.getSpecialization())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .experience(dto.getExperience())
                .consultationFee(dto.getConsultationFee())
                .build();
    }

    public static DoctorResponseDTO toDto(Doctor entity) {
        if (entity == null) {
            return null;
        }

        return DoctorResponseDTO.builder()
                .doctorId(entity.getDoctorId())
                .doctorName(entity.getDoctorName())
                .specialization(entity.getSpecialization())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .experience(entity.getExperience())
                .consultationFee(entity.getConsultationFee())
                .departmentId(entity.getDepartment() != null ? entity.getDepartment().getDepartmentId() : null)
                .departmentName(entity.getDepartment() != null ? entity.getDepartment().getDepartmentName() : null)
                .deleted(entity.getDeleted())
                .build();
    }
}
