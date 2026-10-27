package com.placement;

import com.placement.dto.ApplicationRequest;
import com.placement.dto.DashboardStatsResponse;
import com.placement.dto.StatusUpdateRequest;
import com.placement.entity.Application;
import com.placement.entity.ApplicationStatus;
import com.placement.entity.CompanyDrive;
import com.placement.entity.Student;
import com.placement.exception.BadRequestException;
import com.placement.service.ApplicationService;
import com.placement.service.CompanyDriveService;
import com.placement.service.DashboardService;
import com.placement.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PlacementApplicationTests {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CompanyDriveService driveService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private DashboardService dashboardService;

    @Test
    void testStudentLifecycle() {
        Student student = new Student(
                "TEST2026", "Test Student", "test.student@college.edu",
                "Computer Science", 8.5, 2026, "+91 9999999999", "Java, React"
        );
        Student created = studentService.createStudent(student);
        assertNotNull(created.getId());
        assertEquals("TEST2026", created.getRollNumber());

        created.setCgpa(9.0);
        Student updated = studentService.updateStudent(created.getId(), created);
        assertEquals(9.0, updated.getCgpa());

        List<Student> students = studentService.getAllStudents();
        assertFalse(students.isEmpty());
    }

    @Test
    void testCompanyDriveCreation() {
        CompanyDrive drive = new CompanyDrive(
                "Acme Corp", "Software Developer", 14.0, "Pune",
                8.0, "Computer Science", LocalDate.now().plusDays(10), "OPEN", "Job Description"
        );
        CompanyDrive saved = driveService.createDrive(drive);
        assertNotNull(saved.getId());
        assertEquals("Acme Corp", saved.getCompanyName());
    }

    @Test
    void testApplicationEligibilityAndApplicationFlow() {
        // High CGPA student (eligible)
        Student eligibleStudent = studentService.createStudent(new Student(
                "EL2026", "Eligible Student", "eligible@college.edu",
                "Computer Science", 8.9, 2026, "+91 8888888888", "Spring"
        ));

        // Low CGPA student (ineligible)
        Student lowCgpaStudent = studentService.createStudent(new Student(
                "LOW2026", "Low CGPA Student", "lowcgpa@college.edu",
                "Computer Science", 6.5, 2026, "+91 7777777777", "Python"
        ));

        // Drive with cutoff 7.5
        CompanyDrive drive = driveService.createDrive(new CompanyDrive(
                "Elite Tech", "Backend Engineer", 18.0, "Remote",
                7.5, "Computer Science", LocalDate.now().plusDays(5), "OPEN", "Backend role"
        ));

        // Eligible student applies successfully
        Application app = applicationService.apply(new ApplicationRequest(eligibleStudent.getId(), drive.getId(), "Looking forward"));
        assertNotNull(app.getId());
        assertEquals(ApplicationStatus.APPLIED, app.getStatus());

        // Ineligible student should be rejected with BadRequestException
        assertThrows(BadRequestException.class, () -> {
            applicationService.apply(new ApplicationRequest(lowCgpaStudent.getId(), drive.getId(), "Trying anyway"));
        });

        // Updating application status
        Application updatedApp = applicationService.updateStatus(app.getId(), new StatusUpdateRequest(ApplicationStatus.SELECTED, "Congrats!"));
        assertEquals(ApplicationStatus.SELECTED, updatedApp.getStatus());

        // Verify dashboard stats
        DashboardStatsResponse stats = dashboardService.getStats();
        assertTrue(stats.getTotalStudents() >= 2);
        assertTrue(stats.getTotalApplications() >= 1);
        assertTrue(stats.getSelectedCount() >= 1);
    }
}
