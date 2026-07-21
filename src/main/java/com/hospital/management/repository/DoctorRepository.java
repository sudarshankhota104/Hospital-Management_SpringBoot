package com.hospital.management.repository;

import com.hospital.management.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long>, JpaSpecificationExecutor<Doctor> {

    List<Doctor> findBySpecializationContainingIgnoreCase(String specialization);

    List<Doctor> findByDepartmentDepartmentId(Long departmentId);

    List<Doctor> findByExperienceGreaterThanEqual(Integer experience);

    boolean existsByEmail(String email);
}
