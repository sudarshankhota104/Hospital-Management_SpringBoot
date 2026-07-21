package com.hospital.management.controller;

import com.hospital.management.dto.PatientRequestDTO;
import com.hospital.management.dto.PatientResponseDTO;
import com.hospital.management.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientResponseDTO> createPatient(@Valid @RequestBody PatientRequestDTO requestDTO) {
        PatientResponseDTO responseDTO = patientService.createPatient(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<PatientResponseDTO>> getAllPatients(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String bloodGroup,
            Pageable pageable) {
        Page<PatientResponseDTO> patients = patientService.getAllPatients(name, bloodGroup, pageable);
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable Long id) {
        PatientResponseDTO responseDTO = patientService.getPatientById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable Long id,
                                                            @Valid @RequestBody PatientRequestDTO requestDTO) {
        PatientResponseDTO responseDTO = patientService.updatePatient(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok("Patient "+id+" is deleted");
    }
}
