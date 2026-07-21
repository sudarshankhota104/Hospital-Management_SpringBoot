package com.hospital.management.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;

    @Column(nullable = false, unique = true)
    private String departmentName;

    @Column(nullable = false, unique = true)
    private String departmentCode;

    @Column(nullable = false)
    private String location;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Doctor> doctors = new ArrayList<>();

    @Column(name = "is_deleted", nullable = false)
    @Builder.Default
    private Boolean deleted = false;
}
