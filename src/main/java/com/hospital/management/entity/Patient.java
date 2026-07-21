package com.hospital.management.entity;

import com.hospital.management.enums.BloodGroup;
import com.hospital.management.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;

    @Column(nullable = false)
    private String patientName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BloodGroup bloodGroup;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Appointment> appointments = new ArrayList<>();

    @Column(name = "is_deleted", nullable = false)
    @Builder.Default
    private Boolean deleted = false;
}
