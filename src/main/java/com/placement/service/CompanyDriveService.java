package com.placement.service;

import com.placement.entity.CompanyDrive;
import com.placement.exception.BadRequestException;
import com.placement.exception.ResourceNotFoundException;
import com.placement.repository.CompanyDriveRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CompanyDriveService {

    private final CompanyDriveRepository driveRepository;

    public CompanyDriveService(CompanyDriveRepository driveRepository) {
        this.driveRepository = driveRepository;
    }

    @Transactional(readOnly = true)
    public List<CompanyDrive> getAllDrives() {
        return driveRepository.findAll();
    }

    @Transactional(readOnly = true)
    public CompanyDrive getDriveById(Long id) {
        return driveRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company Drive not found with ID: " + id));
    }

    public CompanyDrive createDrive(CompanyDrive drive) {
        validateDrive(drive);
        if (drive.getStatus() == null || drive.getStatus().isBlank()) {
            drive.setStatus("OPEN");
        } else {
            drive.setStatus(drive.getStatus().trim().toUpperCase());
        }
        return driveRepository.save(drive);
    }

    public CompanyDrive updateDrive(Long id, CompanyDrive updatedData) {
        validateDrive(updatedData);
        CompanyDrive existing = getDriveById(id);

        existing.setCompanyName(updatedData.getCompanyName().trim());
        existing.setJobRole(updatedData.getJobRole().trim());
        existing.setPackageLpa(updatedData.getPackageLpa());
        existing.setLocation(updatedData.getLocation());
        existing.setMinCgpa(updatedData.getMinCgpa());
        existing.setEligibleDepartments(updatedData.getEligibleDepartments());
        existing.setDriveDate(updatedData.getDriveDate());
        existing.setStatus(updatedData.getStatus() != null ? updatedData.getStatus().trim().toUpperCase() : "OPEN");
        existing.setDescription(updatedData.getDescription());

        return driveRepository.save(existing);
    }

    public void deleteDrive(Long id) {
        CompanyDrive existing = getDriveById(id);
        driveRepository.delete(existing);
    }

    private void validateDrive(CompanyDrive d) {
        if (d == null) {
            throw new BadRequestException("Drive payload cannot be empty");
        }
        if (d.getCompanyName() == null || d.getCompanyName().trim().isEmpty()) {
            throw new BadRequestException("Company name is required");
        }
        if (d.getJobRole() == null || d.getJobRole().trim().isEmpty()) {
            throw new BadRequestException("Job role is required");
        }
        if (d.getPackageLpa() == null || d.getPackageLpa() < 0.0) {
            throw new BadRequestException("Package (LPA) cannot be negative");
        }
        if (d.getMinCgpa() == null || d.getMinCgpa() < 0.0 || d.getMinCgpa() > 10.0) {
            throw new BadRequestException("Minimum CGPA must be between 0.0 and 10.0");
        }
    }
}
