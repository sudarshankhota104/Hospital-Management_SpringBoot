package com.hospital.management.repository;

import com.hospital.management.entity.Appointment;
import com.hospital.management.enums.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long>, JpaSpecificationExecutor<Appointment> {

    List<Appointment> findByAppointmentStatus(AppointmentStatus appointmentStatus);

    List<Appointment> findByDoctorDoctorId(Long doctorId);

    List<Appointment> findByPatientPatientId(Long patientId);

    List<Appointment> findByAppointmentDate(LocalDate appointmentDate);

    boolean existsByDoctorDoctorIdAndAppointmentStatusIn(Long doctorId, List<AppointmentStatus> statuses);

    boolean existsByPatientPatientIdAndAppointmentStatusIn(Long patientId, List<AppointmentStatus> statuses);
}
