# Hospital Appointment Management System

## Overview

This is a Spring Boot backend application for managing hospital departments, doctors, patients, appointments, and prescriptions. It exposes REST APIs only, with JSON request and response payloads.

## Tech Stack

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Lombok
- Bean Validation

## Project Structure

- `com.hospital.management`
  - `controller`
  - `service`
  - `service.impl`
  - `repository`
  - `entity`
  - `dto`
  - `mapper`
  - `specification`
  - `exception`
  - `constants`
  - `config`
  - `util`
  - `security`
  - `enums`
- `ProjectApplication.java`
- `src/main/resources/application.properties`
- `src/main/resources/application-dev.properties`
- `src/main/resources/application-prod.properties`

## Run the application

1. Create a MySQL database named `hospital_db`.
2. Update `src/main/resources/application.properties` if your database username or password differs.
3. Run from terminal:

```bash
cd "d:\Java SpringBoot\Java Task Today"
mvn spring-boot:run
```

The application starts on `http://localhost:8080`.

## Configuration

### `application.properties`

- `spring.datasource.url`: MySQL connection string
- `spring.datasource.username`: database username
- `spring.datasource.password`: database password
- `spring.datasource.driver-class-name`: MySQL driver class
- `spring.jpa.hibernate.ddl-auto=update`: let Hibernate create or update tables automatically
- `spring.jpa.show-sql=true`: show SQL queries in logs
- `spring.jpa.properties.hibernate.format_sql=true`: format SQL output
- `spring.jpa.properties.hibernate.dialect`: MySQL dialect for Hibernate
- `spring.jackson.serialization.write-dates-as-timestamps=false`: JSON date formatting
- `server.port=8080`: application port
- logging levels for Spring and application packages

### Profiles

- `application-dev.properties`: development-specific configuration
- `application-prod.properties`: production-ready placeholders

## API Endpoints

### Department

- `POST /departments`
- `GET /departments`
- `GET /departments/{id}`
- `PUT /departments/{id}`
- `DELETE /departments/{id}`

### Doctor

- `POST /doctors`
- `GET /doctors`
- `GET /doctors/{id}`
- `PUT /doctors/{id}`
- `DELETE /doctors/{id}`

### Patient

- `POST /patients`
- `GET /patients`
- `GET /patients/{id}`
- `PUT /patients/{id}`
- `DELETE /patients/{id}`

### Appointment

- `POST /appointments`
- `GET /appointments`
- `GET /appointments/{id}`
- `PUT /appointments/{id}`
- `DELETE /appointments/{id}`

### Prescription

- `POST /prescriptions`
- `GET /prescriptions`
- `GET /prescriptions/{id}`
- `PUT /prescriptions/{id}`
- `DELETE /prescriptions/{id}`

## Sample Requests

### Create Department

#### Request

`POST /departments`

```json
{
  "departmentName": "Cardiology",
  "departmentCode": "CARD",
  "location": "Block A"
}
```

#### Response

```json
{
  "departmentId": 1,
  "departmentName": "Cardiology",
  "departmentCode": "CARD",
  "location": "Block A"
}
```

### Create Doctor

#### Request

`POST /doctors`

```json
{
  "doctorName": "Dr. Priya Singh",
  "specialization": "Cardiology",
  "email": "priya.singh@example.com",
  "phone": "9876543210",
  "experience": 12,
  "consultationFee": 350.00,
  "departmentId": 1
}
```

#### Response

```json
{
  "doctorId": 1,
  "doctorName": "Dr. Priya Singh",
  "specialization": "Cardiology",
  "email": "priya.singh@example.com",
  "phone": "9876543210",
  "experience": 12,
  "consultationFee": 350.00,
  "departmentId": 1,
  "departmentName": "Cardiology"
}
```

### Create Patient

#### Request

`POST /patients`

```json
{
  "patientName": "Amit Kumar",
  "gender": "MALE",
  "bloodGroup": "O_POSITIVE",
  "age": 32,
  "phone": "9123456780",
  "email": "amit.kumar@example.com",
  "address": "123 Gandhi Nagar"
}
```

#### Response

```json
{
  "patientId": 1,
  "patientName": "Amit Kumar",
  "gender": "MALE",
  "bloodGroup": "O_POSITIVE",
  "age": 32,
  "phone": "9123456780",
  "email": "amit.kumar@example.com",
  "address": "123 Gandhi Nagar"
}
```

### Create Appointment

#### Request

`POST /appointments`

```json
{
  "appointmentDate": "2026-07-20",
  "appointmentTime": "10:30:00",
  "appointmentStatus": "BOOKED",
  "doctorId": 1,
  "patientId": 1
}
```

#### Response

```json
{
  "appointmentId": 1,
  "appointmentDate": "2026-07-20",
  "appointmentTime": "10:30:00",
  "appointmentStatus": "BOOKED",
  "doctorId": 1,
  "doctorName": "Dr. Priya Singh",
  "patientId": 1,
  "patientName": "Amit Kumar",
  "prescriptionId": null
}
```

### Create Prescription

#### Request

`POST /prescriptions`

```json
{
  "diagnosis": "Acute bronchitis",
  "medicines": "Medicine A, Medicine B",
  "notes": "Follow up after 7 days",
  "appointmentId": 1
}
```

#### Response

```json
{
  "prescriptionId": 1,
  "diagnosis": "Acute bronchitis",
  "medicines": "Medicine A, Medicine B",
  "notes": "Follow up after 7 days",
  "appointmentId": 1,
  "doctorId": 1,
  "patientId": 1
}
```

## Search Examples

### Search doctors

`GET /doctors?specialization=Cardiology&departmentId=1&experience=5&page=0&size=10`

### Search patients

`GET /patients?name=Amit&bloodGroup=O_POSITIVE&page=0&size=10`

### Search appointments

`GET /appointments?status=BOOKED&doctorId=1&patientId=1&appointmentDate=2026-07-20&page=0&size=10`

## MySQL Tables

The schema is generated from entities by Hibernate. Main tables:

- `departments`
- `doctors`
- `patients`
- `appointments`
- `prescriptions`

## HTTP Status Codes

- `201 Created` for successful creation
- `200 OK` for successful reads and updates
- `204 No Content` for successful deletes
- `400 Bad Request` for validation or invalid input
- `404 Not Found` when resources are missing
- `409 Conflict` for duplicates
- `500 Internal Server Error` for unexpected failures

## Notes

- Use JSON only; no frontend is included.
- Do not delete departments if doctors still reference them indirectly via business rules.
- Doctor and patient deletions are blocked when they have active appointments.
- Prescription creation requires an existing appointment.

## API Testing Helpers

### Postman collection

A ready-made Postman collection is available at `postman_collection.json`.

1. Open Postman.
2. Import `postman_collection.json`.
3. Set an environment variable `baseUrl` to `http://localhost:8080`.
4. Use the collection requests to create and query departments, doctors, patients, appointments, and prescriptions.

### Curl script

A shell script with sample requests is available at `curl_commands.sh`.

Run it after starting the application:

```bash
bash curl_commands.sh
```

### OpenAPI / Swagger UI

The project includes OpenAPI support via SpringDoc.
Once the application is running, access the documentation at:

`http://localhost:8080/swagger-ui.html`

or

`http://localhost:8080/swagger-ui/index.html`

---

If you want, I can also add a ready-made collection file for Postman or sample curl commands for every endpoint.
