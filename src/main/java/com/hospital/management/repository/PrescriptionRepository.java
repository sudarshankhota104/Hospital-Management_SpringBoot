package com.hospital.management.repository;

import com.hospital.management.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

    boolean existsByAppointmentAppointmentId(Long appointmentId);
}
