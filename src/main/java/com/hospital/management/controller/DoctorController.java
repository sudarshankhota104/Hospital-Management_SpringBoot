package com.hospital.management.controller;

import com.hospital.management.dto.DoctorRequestDTO;
import com.hospital.management.dto.DoctorResponseDTO;
import com.hospital.management.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping
    public ResponseEntity<DoctorResponseDTO> createDoctor(@Valid @RequestBody DoctorRequestDTO requestDTO) {
        DoctorResponseDTO responseDTO = doctorService.createDoctor(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<DoctorResponseDTO>> getAllDoctors(
            @RequestParam(required = false) String specialization,
            @RequestParam(required = false) Long departmentId,
            @RequestParam(required = false) Integer experience,
            Pageable pageable) {
        Page<DoctorResponseDTO> doctors = doctorService.getAllDoctors(specialization, departmentId, experience, pageable);
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> getDoctorById(@PathVariable Long id) {
        DoctorResponseDTO responseDTO = doctorService.getDoctorById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> updateDoctor(@PathVariable Long id,
                                                          @Valid @RequestBody DoctorRequestDTO requestDTO) {
        DoctorResponseDTO responseDTO = doctorService.updateDoctor(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.ok("Doctor marked as deleted");
    }
}
