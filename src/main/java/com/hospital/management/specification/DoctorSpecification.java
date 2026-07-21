package com.hospital.management.specification;

import com.hospital.management.entity.Doctor;
import org.springframework.data.jpa.domain.Specification;

public final class DoctorSpecification {

    private DoctorSpecification() {
        throw new UnsupportedOperationException("Specification utility class should not be instantiated");
    }

    public static Specification<Doctor> hasSpecialization(String specialization) {
        return (root, query, criteriaBuilder) -> {
            if (specialization == null || specialization.isBlank()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("specialization")), "%" + specialization.toLowerCase() + "%");
        };
    }

    public static Specification<Doctor> hasDepartmentId(Long departmentId) {
        return (root, query, criteriaBuilder) -> {
            if (departmentId == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("department").get("departmentId"), departmentId);
        };
    }

    public static Specification<Doctor> hasExperienceGreaterThanEqual(Integer experience) {
        return (root, query, criteriaBuilder) -> {
            if (experience == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.greaterThanOrEqualTo(root.get("experience"), experience);
        };
    }
}
