package com.hospital.management.service.impl;

import com.hospital.management.constants.ApplicationConstants;
import com.hospital.management.dto.AppointmentRequestDTO;
import com.hospital.management.dto.AppointmentResponseDTO;
import com.hospital.management.entity.Appointment;
import com.hospital.management.entity.Doctor;
import com.hospital.management.entity.Patient;
import com.hospital.management.enums.AppointmentStatus;
import com.hospital.management.exception.InvalidRequestException;
import com.hospital.management.exception.ResourceNotFoundException;
import com.hospital.management.mapper.AppointmentMapper;
import com.hospital.management.repository.AppointmentRepository;
import com.hospital.management.repository.DoctorRepository;
import com.hospital.management.repository.PatientRepository;
import com.hospital.management.service.AppointmentService;
import com.hospital.management.specification.AppointmentSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Override
    @Transactional
    public AppointmentResponseDTO createAppointment(AppointmentRequestDTO requestDTO) {
        log.debug("Creating appointment for doctor {} and patient {}", requestDTO.getDoctorId(), requestDTO.getPatientId());

        Doctor doctor = doctorRepository.findById(requestDTO.getDoctorId())
                .orElseThrow(() -> new InvalidRequestException(String.format(ApplicationConstants.DOCTOR_NOT_FOUND, requestDTO.getDoctorId())));

        Patient patient = patientRepository.findById(requestDTO.getPatientId())
                .orElseThrow(() -> new InvalidRequestException(String.format(ApplicationConstants.PATIENT_NOT_FOUND, requestDTO.getPatientId())));

        if (requestDTO.getAppointmentStatus() == null) {
            throw new InvalidRequestException(ApplicationConstants.APPOINTMENT_MUST_HAVE_DOCTOR);
        }

        Appointment appointment = AppointmentMapper.toEntity(requestDTO);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);

        Appointment savedAppointment = appointmentRepository.save(appointment);
        log.info("Appointment created successfully with id {}", savedAppointment.getAppointmentId());
        return AppointmentMapper.toDto(savedAppointment);
    }

    @Override
    @Transactional(readOnly = true)
    public AppointmentResponseDTO getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ApplicationConstants.APPOINTMENT_NOT_FOUND, id)));
        return AppointmentMapper.toDto(appointment);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AppointmentResponseDTO> getAllAppointments(AppointmentStatus status, Long doctorId, Long patientId, LocalDate appointmentDate, Pageable pageable) {
        Page<Appointment> appointments = appointmentRepository.findAll(
                AppointmentSpecification.hasStatus(status)
                        .and(AppointmentSpecification.hasDoctorId(doctorId))
                        .and(AppointmentSpecification.hasPatientId(patientId))
                        .and(AppointmentSpecification.hasDate(appointmentDate)),
                pageable);
        return appointments.map(AppointmentMapper::toDto);
    }

    @Override
    @Transactional
    public AppointmentResponseDTO updateAppointment(Long id, AppointmentRequestDTO requestDTO) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ApplicationConstants.APPOINTMENT_NOT_FOUND, id)));

        Doctor doctor = doctorRepository.findById(requestDTO.getDoctorId())
                .orElseThrow(() -> new InvalidRequestException(String.format(ApplicationConstants.DOCTOR_NOT_FOUND, requestDTO.getDoctorId())));

        Patient patient = patientRepository.findById(requestDTO.getPatientId())
                .orElseThrow(() -> new InvalidRequestException(String.format(ApplicationConstants.PATIENT_NOT_FOUND, requestDTO.getPatientId())));

        appointment.setAppointmentDate(requestDTO.getAppointmentDate());
        appointment.setAppointmentTime(requestDTO.getAppointmentTime());
        appointment.setAppointmentStatus(requestDTO.getAppointmentStatus());
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);

        Appointment updatedAppointment = appointmentRepository.save(appointment);
        log.info("Appointment updated successfully with id {}", updatedAppointment.getAppointmentId());
        return AppointmentMapper.toDto(updatedAppointment);
    }

    @Override
    @Transactional
    public void deleteAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ApplicationConstants.APPOINTMENT_NOT_FOUND, id)));

        appointment.setDeleted(true);
        appointmentRepository.save(appointment);
        log.info("Appointment soft-deleted successfully with id {}", id);
    }
}
