package com.hospital.management.dto;

import jakarta.validation.constraints.NotBlank;
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
public class DepartmentRequestDTO {

    @NotBlank(message = "Department name is required")
    private String departmentName;

    @NotBlank(message = "Department code is required")
    private String departmentCode;

    @NotBlank(message = "Location is required")
    private String location;
}
