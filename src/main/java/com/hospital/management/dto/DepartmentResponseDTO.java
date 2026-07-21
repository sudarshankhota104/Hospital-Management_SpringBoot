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
public class DepartmentResponseDTO {

    private Long departmentId;
    private String departmentName;
    private String departmentCode;
    private String location;
    private Boolean deleted;
}
