package com.hospital.management.controller;

import com.hospital.management.dto.AppointmentRequestDTO;
import com.hospital.management.dto.AppointmentResponseDTO;
import com.hospital.management.enums.AppointmentStatus;
import com.hospital.management.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> createAppointment(@Valid @RequestBody AppointmentRequestDTO requestDTO) {
        AppointmentResponseDTO responseDTO = appointmentService.createAppointment(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<AppointmentResponseDTO>> getAllAppointments(
            @RequestParam(required = false) AppointmentStatus status,
            @RequestParam(required = false) Long doctorId,
            @RequestParam(required = false) Long patientId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate appointmentDate,
            Pageable pageable) {
        Page<AppointmentResponseDTO> appointments = appointmentService.getAllAppointments(status, doctorId, patientId, appointmentDate, pageable);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> getAppointmentById(@PathVariable Long id) {
        AppointmentResponseDTO responseDTO = appointmentService.getAppointmentById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> updateAppointment(@PathVariable Long id,
                                                                    @Valid @RequestBody AppointmentRequestDTO requestDTO) {
        AppointmentResponseDTO responseDTO = appointmentService.updateAppointment(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.ok("Appointment marked as deleted");
    }
}
