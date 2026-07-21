package com.hospital.management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorResponseDTO {

    private Long doctorId;
    private String doctorName;
    private String specialization;
    private String email;
    private String phone;
    private Integer experience;
    private BigDecimal consultationFee;
    private Long departmentId;
    private String departmentName;
    private Boolean deleted;
}
