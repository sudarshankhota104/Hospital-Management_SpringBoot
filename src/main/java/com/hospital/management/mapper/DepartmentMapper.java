package com.hospital.management.mapper;

import com.hospital.management.dto.DepartmentRequestDTO;
import com.hospital.management.dto.DepartmentResponseDTO;
import com.hospital.management.entity.Department;

public final class DepartmentMapper {

    private DepartmentMapper() {
        throw new UnsupportedOperationException("Mapper class should not be instantiated");
    }

    public static Department toEntity(DepartmentRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        return Department.builder()
                .departmentName(dto.getDepartmentName())
                .departmentCode(dto.getDepartmentCode())
                .location(dto.getLocation())
                .build();
    }

    public static DepartmentResponseDTO toDto(Department entity) {
        if (entity == null) {
            return null;
        }

        return DepartmentResponseDTO.builder()
                .departmentId(entity.getDepartmentId())
                .departmentName(entity.getDepartmentName())
                .departmentCode(entity.getDepartmentCode())
                .location(entity.getLocation())
                .deleted(entity.getDeleted())
                .build();
    }
}
