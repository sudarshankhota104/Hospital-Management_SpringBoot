package com.hospital.management.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;

    @Column(nullable = false)
    private String doctorName;

    @Column(nullable = false)
    private String specialization;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private Integer experience;

    @Column(nullable = false)
    private BigDecimal consultationFee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Appointment> appointments = new ArrayList<>();

    @Column(name = "is_deleted", nullable = false)
    @Builder.Default
    private Boolean deleted = false;
}
