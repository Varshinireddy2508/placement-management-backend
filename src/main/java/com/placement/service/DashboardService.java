package com.placement.service;

import com.placement.dto.DashboardStatsResponse;
import com.placement.entity.ApplicationStatus;
import com.placement.repository.ApplicationRepository;
import com.placement.repository.CompanyDriveRepository;
import com.placement.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DashboardService {

    private final StudentRepository studentRepository;
    private final CompanyDriveRepository driveRepository;
    private final ApplicationRepository applicationRepository;

    public DashboardService(StudentRepository studentRepository,
                            CompanyDriveRepository driveRepository,
                            ApplicationRepository applicationRepository) {
        this.studentRepository = studentRepository;
        this.driveRepository = driveRepository;
        this.applicationRepository = applicationRepository;
    }

    public DashboardStatsResponse getStats() {
        long totalStudents = studentRepository.count();
        long totalDrives = driveRepository.count();
        long activeDrives = driveRepository.findByStatusIgnoreCase("OPEN").size();
        long totalApplications = applicationRepository.count();
        long selectedCount = applicationRepository.countByStatus(ApplicationStatus.SELECTED);
        long shortlistedCount = applicationRepository.countByStatus(ApplicationStatus.SHORTLISTED);

        return new DashboardStatsResponse(
                totalStudents,
                totalDrives,
                activeDrives,
                totalApplications,
                selectedCount,
                shortlistedCount
        );
    }
}
