package com.hospital.management.controller;

import com.hospital.management.dto.PrescriptionRequestDTO;
import com.hospital.management.dto.PrescriptionResponseDTO;
import com.hospital.management.service.PrescriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prescriptions")
@RequiredArgsConstructor
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    @PostMapping
    public ResponseEntity<PrescriptionResponseDTO> createPrescription(@Valid @RequestBody PrescriptionRequestDTO requestDTO) {
        PrescriptionResponseDTO responseDTO = prescriptionService.createPrescription(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<PrescriptionResponseDTO>> getAllPrescriptions(Pageable pageable) {
        Page<PrescriptionResponseDTO> prescriptions = prescriptionService.getAllPrescriptions(pageable);
        return ResponseEntity.ok(prescriptions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrescriptionResponseDTO> getPrescriptionById(@PathVariable Long id) {
        PrescriptionResponseDTO responseDTO = prescriptionService.getPrescriptionById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrescriptionResponseDTO> updatePrescription(@PathVariable Long id,
                                                                      @Valid @RequestBody PrescriptionRequestDTO requestDTO) {
        PrescriptionResponseDTO responseDTO = prescriptionService.updatePrescription(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePrescription(@PathVariable Long id) {
        prescriptionService.deletePrescription(id);
        return ResponseEntity.ok("Prescription marked as deleted");
    }
}
