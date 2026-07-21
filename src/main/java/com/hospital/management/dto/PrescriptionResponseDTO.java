package com.hospital.management.dto;

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
public class PrescriptionResponseDTO {

    private Long prescriptionId;
    private String diagnosis;
    private String medicines;
    private String notes;
    private Long appointmentId;
    private Long doctorId;
    private Long patientId;
    private Boolean deleted;
}
