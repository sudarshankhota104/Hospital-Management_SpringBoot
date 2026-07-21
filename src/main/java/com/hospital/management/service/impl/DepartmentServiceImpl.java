package com.hospital.management.service.impl;

import com.hospital.management.constants.ApplicationConstants;
import com.hospital.management.dto.DepartmentRequestDTO;
import com.hospital.management.dto.DepartmentResponseDTO;
import com.hospital.management.entity.Department;
import com.hospital.management.exception.DuplicateResourceException;
import com.hospital.management.exception.ResourceNotFoundException;
import com.hospital.management.mapper.DepartmentMapper;
import com.hospital.management.repository.DepartmentRepository;
import com.hospital.management.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    @Transactional
    public DepartmentResponseDTO createDepartment(DepartmentRequestDTO requestDTO) {
        log.debug("Creating department with code {}", requestDTO.getDepartmentCode());
        if (departmentRepository.existsByDepartmentCode(requestDTO.getDepartmentCode())) {
            throw new DuplicateResourceException(String.format(ApplicationConstants.DUPLICATE_RESOURCE, requestDTO.getDepartmentCode()));
        }

        Department department = DepartmentMapper.toEntity(requestDTO);
        Department savedDepartment = departmentRepository.save(department);
        log.info("Department created successfully with id {}", savedDepartment.getDepartmentId());
        return DepartmentMapper.toDto(savedDepartment);
    }

    @Override
    @Transactional(readOnly = true)
    public DepartmentResponseDTO getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ApplicationConstants.DEPARTMENT_NOT_FOUND, id)));
        return DepartmentMapper.toDto(department);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DepartmentResponseDTO> getAllDepartments(Pageable pageable) {
        return departmentRepository.findAll(pageable).map(DepartmentMapper::toDto);
    }

    @Override
    @Transactional
    public DepartmentResponseDTO updateDepartment(Long id, DepartmentRequestDTO requestDTO) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ApplicationConstants.DEPARTMENT_NOT_FOUND, id)));

        if (!department.getDepartmentCode().equals(requestDTO.getDepartmentCode())
                && departmentRepository.existsByDepartmentCode(requestDTO.getDepartmentCode())) {
            throw new DuplicateResourceException(String.format(ApplicationConstants.DUPLICATE_RESOURCE, requestDTO.getDepartmentCode()));
        }

        department.setDepartmentName(requestDTO.getDepartmentName());
        department.setDepartmentCode(requestDTO.getDepartmentCode());
        department.setLocation(requestDTO.getLocation());

        Department updatedDepartment = departmentRepository.save(department);
        log.info("Department updated successfully with id {}", updatedDepartment.getDepartmentId());
        return DepartmentMapper.toDto(updatedDepartment);
    }

    @Override
    @Transactional
    public void deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ApplicationConstants.DEPARTMENT_NOT_FOUND, id)));
        department.setDeleted(true);
        departmentRepository.save(department);
        log.info("Department soft-deleted successfully with id {}", id);
    }
}
