package com.hospital.management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrescriptionRequestDTO {

    @NotBlank(message = "Diagnosis is required")
    private String diagnosis;

    @NotBlank(message = "Medicines are required")
    private String medicines;

    private String notes;

    @NotNull(message = "Appointment id is required")
    private Long appointmentId;
}
