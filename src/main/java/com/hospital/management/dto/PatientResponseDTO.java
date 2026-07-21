package com.hospital.management.dto;

import com.hospital.management.enums.BloodGroup;
import com.hospital.management.enums.Gender;
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
public class PatientResponseDTO {

    private Long patientId;
    private String patientName;
    private Gender gender;
    private BloodGroup bloodGroup;
    private Integer age;
    private String phone;
    private String email;
    private String address;
    private Boolean deleted;
}
