package com.hospital.management.controller;

import com.hospital.management.dto.DepartmentRequestDTO;
import com.hospital.management.dto.DepartmentResponseDTO;
import com.hospital.management.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentResponseDTO> createDepartment(@Valid @RequestBody DepartmentRequestDTO requestDTO) {
        DepartmentResponseDTO responseDTO = departmentService.createDepartment(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<DepartmentResponseDTO>> getAllDepartments(Pageable pageable) {
        Page<DepartmentResponseDTO> departments = departmentService.getAllDepartments(pageable);
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> getDepartmentById(@PathVariable Long id) {
        DepartmentResponseDTO responseDTO = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> updateDepartment(@PathVariable Long id,
                                                                 @Valid @RequestBody DepartmentRequestDTO requestDTO) {
        DepartmentResponseDTO responseDTO = departmentService.updateDepartment(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok("Department marked as deleted");
    }
}
