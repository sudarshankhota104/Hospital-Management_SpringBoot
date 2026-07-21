package com.hospital.management.mapper;

import com.hospital.management.dto.AppointmentRequestDTO;
import com.hospital.management.dto.AppointmentResponseDTO;
import com.hospital.management.entity.Appointment;
import com.hospital.management.entity.Doctor;
import com.hospital.management.entity.Patient;

public final class AppointmentMapper {

    private AppointmentMapper() {
        throw new UnsupportedOperationException("Mapper class should not be instantiated");
    }

    public static Appointment toEntity(AppointmentRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        return Appointment.builder()
                .appointmentDate(dto.getAppointmentDate())
                .appointmentTime(dto.getAppointmentTime())
                .appointmentStatus(dto.getAppointmentStatus())
                .build();
    }

    public static AppointmentResponseDTO toDto(Appointment entity) {
        if (entity == null) {
            return null;
        }

        Long doctorId = null;
        String doctorName = null;
        if (entity.getDoctor() != null) {
            doctorId = entity.getDoctor().getDoctorId();
            doctorName = entity.getDoctor().getDoctorName();
        }

        Long patientId = null;
        String patientName = null;
        if (entity.getPatient() != null) {
            patientId = entity.getPatient().getPatientId();
            patientName = entity.getPatient().getPatientName();
        }

        return AppointmentResponseDTO.builder()
                .appointmentId(entity.getAppointmentId())
                .appointmentDate(entity.getAppointmentDate())
                .appointmentTime(entity.getAppointmentTime())
                .appointmentStatus(entity.getAppointmentStatus())
                .doctorId(doctorId)
                .doctorName(doctorName)
                .patientId(patientId)
                .patientName(patientName)
                .prescriptionId(entity.getPrescription() != null ? entity.getPrescription().getPrescriptionId() : null)
                .deleted(entity.getDeleted())
                .build();
    }
}
