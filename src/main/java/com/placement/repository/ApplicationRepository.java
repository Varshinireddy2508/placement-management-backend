package com.placement.repository;

import com.placement.entity.Application;
import com.placement.entity.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByStudentId(Long studentId);
    List<Application> findByCompanyDriveId(Long companyDriveId);
    boolean existsByStudentIdAndCompanyDriveId(Long studentId, Long companyDriveId);
    long countByStatus(ApplicationStatus status);
}
