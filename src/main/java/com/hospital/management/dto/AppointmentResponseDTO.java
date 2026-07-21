package com.hospital.management.dto;

import com.hospital.management.enums.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentResponseDTO {

    private Long appointmentId;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private AppointmentStatus appointmentStatus;
    private Long doctorId;
    private String doctorName;
    private Long patientId;
    private String patientName;
    private Long prescriptionId;
    private Boolean deleted;
}
