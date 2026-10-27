package com.placement.service;

import com.placement.dto.ApplicationRequest;
import com.placement.dto.StatusUpdateRequest;
import com.placement.entity.Application;
import com.placement.entity.ApplicationStatus;
import com.placement.entity.CompanyDrive;
import com.placement.entity.Student;
import com.placement.exception.BadRequestException;
import com.placement.exception.ResourceNotFoundException;
import com.placement.repository.ApplicationRepository;
import com.placement.repository.CompanyDriveRepository;
import com.placement.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final StudentRepository studentRepository;
    private final CompanyDriveRepository driveRepository;

    public ApplicationService(ApplicationRepository applicationRepository,
                              StudentRepository studentRepository,
                              CompanyDriveRepository driveRepository) {
        this.applicationRepository = applicationRepository;
        this.studentRepository = studentRepository;
        this.driveRepository = driveRepository;
    }

    @Transactional(readOnly = true)
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Application getApplicationById(Long id) {
        return applicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with ID: " + id));
    }

    public Application apply(ApplicationRequest request) {
        if (request == null) {
            throw new BadRequestException("Application request cannot be empty");
        }
        if (request.getStudentId() == null) {
            throw new BadRequestException("Student ID is required");
        }
        if (request.getCompanyDriveId() == null) {
            throw new BadRequestException("Company Drive ID is required");
        }

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + request.getStudentId()));

        CompanyDrive drive = driveRepository.findById(request.getCompanyDriveId())
                .orElseThrow(() -> new ResourceNotFoundException("Company Drive not found with ID: " + request.getCompanyDriveId()));

        // Check if drive is still open
        if (!"OPEN".equalsIgnoreCase(drive.getStatus())) {
            throw new BadRequestException("This drive is currently " + drive.getStatus() + " and not accepting new applications");
        }

        // Check if student has already applied
        if (applicationRepository.existsByStudentIdAndCompanyDriveId(student.getId(), drive.getId())) {
            throw new BadRequestException("Student has already applied to this drive");
        }

        // Check eligibility: student CGPA must be >= drive minimum CGPA
        if (student.getCgpa() < drive.getMinCgpa()) {
            throw new BadRequestException("Ineligible: Student CGPA (" + student.getCgpa() + ") is below the cutoff criteria of " + drive.getMinCgpa());
        }

        Application application = new Application(
                student,
                drive,
                LocalDateTime.now(),
                ApplicationStatus.APPLIED,
                request.getRemarks()
        );

        return applicationRepository.save(application);
    }

    public Application updateStatus(Long id, StatusUpdateRequest request) {
        if (request == null || request.getStatus() == null) {
            throw new BadRequestException("New application status must be provided");
        }
        Application application = getApplicationById(id);
        application.setStatus(request.getStatus());
        if (request.getRemarks() != null && !request.getRemarks().isBlank()) {
            application.setRemarks(request.getRemarks().trim());
        }
        return applicationRepository.save(application);
    }

    @Transactional(readOnly = true)
    public List<Application> getApplicationsByStudent(Long studentId) {
        return applicationRepository.findByStudentId(studentId);
    }

    @Transactional(readOnly = true)
    public List<Application> getApplicationsByDrive(Long driveId) {
        return applicationRepository.findByCompanyDriveId(driveId);
    }

    public void deleteApplication(Long id) {
        Application application = getApplicationById(id);
        applicationRepository.delete(application);
    }
}
