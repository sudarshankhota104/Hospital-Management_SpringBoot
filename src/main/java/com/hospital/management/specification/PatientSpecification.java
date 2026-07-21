package com.hospital.management.specification;

import com.hospital.management.entity.Patient;
import org.springframework.data.jpa.domain.Specification;

public final class PatientSpecification {

    private PatientSpecification() {
        throw new UnsupportedOperationException("Specification utility class should not be instantiated");
    }

    public static Specification<Patient> hasName(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.isBlank()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("patientName")), "%" + name.toLowerCase() + "%");
        };
    }

    public static Specification<Patient> hasBloodGroup(String bloodGroup) {
        return (root, query, criteriaBuilder) -> {
            if (bloodGroup == null || bloodGroup.isBlank()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("bloodGroup"), bloodGroup);
        };
    }
}
