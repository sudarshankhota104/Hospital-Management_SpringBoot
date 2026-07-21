package com.hospital.management.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "prescriptions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prescriptionId;

    @Column(nullable = false)
    private String diagnosis;

    @Column(nullable = false)
    private String medicines;

    @Column(nullable = true)
    private String notes;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id", nullable = false, unique = true)
    private Appointment appointment;

    @Column(name = "is_deleted", nullable = false)
    @Builder.Default
    private Boolean deleted = false;
}
