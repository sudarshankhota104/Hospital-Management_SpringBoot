#!/bin/bash

BASE_URL="http://localhost:8080"

# Create Department
curl -X POST "$BASE_URL/departments" \
  -H "Content-Type: application/json" \
  -d '{
    "departmentName": "Cardiology",
    "departmentCode": "CARD",
    "location": "Block A"
  }'

echo

# Create Doctor
curl -X POST "$BASE_URL/doctors" \
  -H "Content-Type: application/json" \
  -d '{
    "doctorName": "Dr. Priya Singh",
    "specialization": "Cardiology",
    "email": "priya.singh@example.com",
    "phone": "9876543210",
    "experience": 12,
    "consultationFee": 350.00,
    "departmentId": 1
  }'

echo

# Create Patient
curl -X POST "$BASE_URL/patients" \
  -H "Content-Type: application/json" \
  -d '{
    "patientName": "Amit Kumar",
    "gender": "MALE",
    "bloodGroup": "O_POSITIVE",
    "age": 32,
    "phone": "9123456780",
    "email": "amit.kumar@example.com",
    "address": "123 Gandhi Nagar"
  }'

echo

# Create Appointment
curl -X POST "$BASE_URL/appointments" \
  -H "Content-Type: application/json" \
  -d '{
    "appointmentDate": "2026-07-20",
    "appointmentTime": "10:30:00",
    "appointmentStatus": "BOOKED",
    "doctorId": 1,
    "patientId": 1
  }'

echo

# Create Prescription
curl -X POST "$BASE_URL/prescriptions" \
  -H "Content-Type: application/json" \
  -d '{
    "diagnosis": "Acute bronchitis",
    "medicines": "Medicine A, Medicine B",
    "notes": "Follow up after 7 days",
    "appointmentId": 1
  }'

echo

# Search Doctors
curl "$BASE_URL/doctors?specialization=Cardiology&departmentId=1&experience=5&page=0&size=10"
echo

# Search Patients
curl "$BASE_URL/patients?name=Amit&bloodGroup=O_POSITIVE&page=0&size=10"
echo

# Search Appointments
curl "$BASE_URL/appointments?status=BOOKED&doctorId=1&patientId=1&appointmentDate=2026-07-20&page=0&size=10"

echo
