package com.hospital.management.service.impl;

import com.hospital.management.constants.ApplicationConstants;
import com.hospital.management.dto.DoctorRequestDTO;
import com.hospital.management.dto.DoctorResponseDTO;
import com.hospital.management.entity.Department;
import com.hospital.management.entity.Doctor;
import com.hospital.management.exception.DuplicateResourceException;
import com.hospital.management.exception.InvalidRequestException;
import com.hospital.management.exception.ResourceNotFoundException;
import com.hospital.management.mapper.DoctorMapper;
import com.hospital.management.enums.AppointmentStatus;
import com.hospital.management.repository.DepartmentRepository;
import com.hospital.management.repository.DoctorRepository;
import com.hospital.management.service.DoctorService;
import com.hospital.management.specification.DoctorSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    @Transactional
    public DoctorResponseDTO createDoctor(DoctorRequestDTO requestDTO) {
        log.debug("Creating doctor with email {}", requestDTO.getEmail());
        if (doctorRepository.existsByEmail(requestDTO.getEmail())) {
            throw new DuplicateResourceException(String.format(ApplicationConstants.DUPLICATE_RESOURCE, requestDTO.getEmail()));
        }

        Department department = departmentRepository.findById(requestDTO.getDepartmentId())
                .orElseThrow(() -> new InvalidRequestException(String.format(ApplicationConstants.DEPARTMENT_NOT_FOUND, requestDTO.getDepartmentId())));

        Doctor doctor = DoctorMapper.toEntity(requestDTO);
        doctor.setDepartment(department);

        Doctor savedDoctor = doctorRepository.save(doctor);
        log.info("Doctor created successfully with id {}", savedDoctor.getDoctorId());
        return DoctorMapper.toDto(savedDoctor);
    }

    @Override
    @Transactional(readOnly = true)
    public DoctorResponseDTO getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ApplicationConstants.DOCTOR_NOT_FOUND, id)));
        return DoctorMapper.toDto(doctor);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DoctorResponseDTO> getAllDoctors(String specialization, Long departmentId, Integer experience, Pageable pageable) {
        Page<Doctor> doctors = doctorRepository.findAll(
                DoctorSpecification.hasSpecialization(specialization)
                        .and(DoctorSpecification.hasDepartmentId(departmentId))
                        .and(DoctorSpecification.hasExperienceGreaterThanEqual(experience)),
                pageable);
        return doctors.map(DoctorMapper::toDto);
    }

    @Override
    @Transactional
    public DoctorResponseDTO updateDoctor(Long id, DoctorRequestDTO requestDTO) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ApplicationConstants.DOCTOR_NOT_FOUND, id)));

        if (!doctor.getEmail().equals(requestDTO.getEmail()) && doctorRepository.existsByEmail(requestDTO.getEmail())) {
            throw new DuplicateResourceException(String.format(ApplicationConstants.DUPLICATE_RESOURCE, requestDTO.getEmail()));
        }

        Department department = departmentRepository.findById(requestDTO.getDepartmentId())
                .orElseThrow(() -> new InvalidRequestException(String.format(ApplicationConstants.DEPARTMENT_NOT_FOUND, requestDTO.getDepartmentId())));

        doctor.setDoctorName(requestDTO.getDoctorName());
        doctor.setSpecialization(requestDTO.getSpecialization());
        doctor.setEmail(requestDTO.getEmail());
        doctor.setPhone(requestDTO.getPhone());
        doctor.setExperience(requestDTO.getExperience());
        doctor.setConsultationFee(requestDTO.getConsultationFee());
        doctor.setDepartment(department);

        Doctor updatedDoctor = doctorRepository.save(doctor);
        log.info("Doctor updated successfully with id {}", updatedDoctor.getDoctorId());
        return DoctorMapper.toDto(updatedDoctor);
    }

    @Override
    @Transactional
    public void deleteDoctor(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ApplicationConstants.DOCTOR_NOT_FOUND, id)));

        boolean hasActiveAppointments = doctor.getAppointments() != null && !doctor.getAppointments().isEmpty();
        if (hasActiveAppointments) {
            throw new InvalidRequestException(String.format(ApplicationConstants.ACTIVE_APPOINTMENTS_EXIST, "Doctor"));
        }

        doctor.setDeleted(true);
        doctorRepository.save(doctor);
        log.info("Doctor soft-deleted successfully with id {}", id);
    }
}
