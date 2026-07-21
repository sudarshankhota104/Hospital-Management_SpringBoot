package com.hospital.management.constants;

public final class ApplicationConstants {

    private ApplicationConstants() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    public static final String DEPARTMENT_NOT_FOUND = "Department not found with id %d";
    public static final String DOCTOR_NOT_FOUND = "Doctor not found with id %d";
    public static final String PATIENT_NOT_FOUND = "Patient not found with id %d";
    public static final String APPOINTMENT_NOT_FOUND = "Appointment not found with id %d";
    public static final String PRESCRIPTION_NOT_FOUND = "Prescription not found with id %d";
    public static final String DUPLICATE_RESOURCE = "Resource already exists: %s";
    public static final String ACTIVE_APPOINTMENTS_EXIST = "Cannot delete %s with active appointments";
    public static final String APPOINTMENT_MUST_HAVE_DOCTOR = "Appointment must have a valid doctor";
    public static final String APPOINTMENT_MUST_HAVE_PATIENT = "Appointment must have a valid patient";
    public static final String PRESCRIPTION_MUST_HAVE_APPOINTMENT = "Prescription must be linked to an existing appointment";
}