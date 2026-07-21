package com.hospital.management.dto;

import com.hospital.management.enums.AppointmentStatus;
import jakarta.validation.constraints.NotNull;
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
public class AppointmentRequestDTO {

    @NotNull(message = "Appointment date is required")
    private LocalDate appointmentDate;

    @NotNull(message = "Appointment time is required")
    private LocalTime appointmentTime;

    @NotNull(message = "Appointment status is required")
    private AppointmentStatus appointmentStatus;

    @NotNull(message = "Doctor id is required")
    private Long doctorId;

    @NotNull(message = "Patient id is required")
    private Long patientId;
}
