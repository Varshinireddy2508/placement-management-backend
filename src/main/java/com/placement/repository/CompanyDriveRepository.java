package com.placement.repository;

import com.placement.entity.CompanyDrive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyDriveRepository extends JpaRepository<CompanyDrive, Long> {
    List<CompanyDrive> findByStatusIgnoreCase(String status);
}
