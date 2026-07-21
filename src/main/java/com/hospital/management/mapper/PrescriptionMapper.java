package com.hospital.management.mapper;

import com.hospital.management.dto.PrescriptionRequestDTO;
import com.hospital.management.dto.PrescriptionResponseDTO;
import com.hospital.management.entity.Appointment;
import com.hospital.management.entity.Prescription;

public final class PrescriptionMapper {

    private PrescriptionMapper() {
        throw new UnsupportedOperationException("Mapper class should not be instantiated");
    }

    public static Prescription toEntity(PrescriptionRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        return Prescription.builder()
                .diagnosis(dto.getDiagnosis())
                .medicines(dto.getMedicines())
                .notes(dto.getNotes())
                .build();
    }

    public static PrescriptionResponseDTO toDto(Prescription entity) {
        if (entity == null) {
            return null;
        }

        Long appointmentId = null;
        Long doctorId = null;
        Long patientId = null;

        if (entity.getAppointment() != null) {
            Appointment appointment = entity.getAppointment();
            appointmentId = appointment.getAppointmentId();
            if (appointment.getDoctor() != null) {
                doctorId = appointment.getDoctor().getDoctorId();
            }
            if (appointment.getPatient() != null) {
                patientId = appointment.getPatient().getPatientId();
            }
        }

        return PrescriptionResponseDTO.builder()
                .prescriptionId(entity.getPrescriptionId())
                .diagnosis(entity.getDiagnosis())
                .medicines(entity.getMedicines())
                .notes(entity.getNotes())
                .appointmentId(appointmentId)
                .doctorId(doctorId)
                .patientId(patientId)
                .deleted(entity.getDeleted())
                .build();
    }
}
