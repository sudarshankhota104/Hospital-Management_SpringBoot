package com.hospital.management.specification;

import com.hospital.management.entity.Appointment;
import com.hospital.management.enums.AppointmentStatus;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public final class AppointmentSpecification {

    private AppointmentSpecification() {
        throw new UnsupportedOperationException("Specification utility class should not be instantiated");
    }

    public static Specification<Appointment> hasStatus(AppointmentStatus status) {
        return (root, query, criteriaBuilder) -> {
            if (status == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("appointmentStatus"), status);
        };
    }

    public static Specification<Appointment> hasDoctorId(Long doctorId) {
        return (root, query, criteriaBuilder) -> {
            if (doctorId == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("doctor").get("doctorId"), doctorId);
        };
    }

    public static Specification<Appointment> hasPatientId(Long patientId) {
        return (root, query, criteriaBuilder) -> {
            if (patientId == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("patient").get("patientId"), patientId);
        };
    }

    public static Specification<Appointment> hasDate(LocalDate appointmentDate) {
        return (root, query, criteriaBuilder) -> {
            if (appointmentDate == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("appointmentDate"), appointmentDate);
        };
    }
}
