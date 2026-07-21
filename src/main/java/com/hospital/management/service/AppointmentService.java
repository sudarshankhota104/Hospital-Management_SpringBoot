package com.hospital.management.service;

import com.hospital.management.dto.AppointmentRequestDTO;
import com.hospital.management.dto.AppointmentResponseDTO;
import com.hospital.management.enums.AppointmentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface AppointmentService {

    AppointmentResponseDTO createAppointment(AppointmentRequestDTO requestDTO);

    AppointmentResponseDTO getAppointmentById(Long id);

    Page<AppointmentResponseDTO> getAllAppointments(AppointmentStatus status, Long doctorId, Long patientId, LocalDate appointmentDate, Pageable pageable);

    AppointmentResponseDTO updateAppointment(Long id, AppointmentRequestDTO requestDTO);

    void deleteAppointment(Long id);
}
