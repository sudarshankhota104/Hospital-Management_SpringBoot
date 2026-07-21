package com.hospital.management.dto;

import com.hospital.management.enums.BloodGroup;
import com.hospital.management.enums.Gender;
import jakarta.validation.constraints.*;
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
public class PatientRequestDTO {

    @NotBlank(message = "Patient name is required")
    private String patientName;

    @NotNull(message = "Gender is required")
    private Gender gender;

    @NotNull(message = "Blood group is required")
    private BloodGroup bloodGroup;

    @NotNull(message = "Age is required")
    @Min(value = 0, message = "Age cannot be negative")
    @Max(value = 120, message = "Age must be realistic")
    private Integer age;

    @NotBlank(message = "Phone is required")
    private String phone;

    @Email(message = "Valid email is required")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Address is required")
    private String address;
}
