package com.placement.config;

import com.placement.entity.Application;
import com.placement.entity.ApplicationStatus;
import com.placement.entity.CompanyDrive;
import com.placement.entity.Student;
import com.placement.repository.ApplicationRepository;
import com.placement.repository.CompanyDriveRepository;
import com.placement.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseSeeder.class);

    private final StudentRepository studentRepository;
    private final CompanyDriveRepository driveRepository;
    private final ApplicationRepository applicationRepository;

    public DatabaseSeeder(StudentRepository studentRepository,
                          CompanyDriveRepository driveRepository,
                          ApplicationRepository applicationRepository) {
        this.studentRepository = studentRepository;
        this.driveRepository = driveRepository;
        this.applicationRepository = applicationRepository;
    }

    @Override
    public void run(String... args) {
        try {
            if (studentRepository.count() == 0) {
                logger.info("Database is empty. Seeding initial student and drive records...");

                Student s1 = studentRepository.save(new Student(
                        "2022CS101", "Aarav Sharma", "aarav.sharma@college.edu",
                        "Computer Science", 8.85, 2026, "+91 9876543210", "Java, Spring Boot, React, SQL"
                ));

                Student s2 = studentRepository.save(new Student(
                        "2022IT102", "Priya Patel", "priya.patel@college.edu",
                        "Information Technology", 9.15, 2026, "+91 9876543211", "Python, AWS, Node.js, Docker"
                ));

                Student s3 = studentRepository.save(new Student(
                        "2022EC103", "Rohan Verma", "rohan.verma@college.edu",
                        "Electronics & Comm.", 7.40, 2026, "+91 9876543212", "C++, Embedded Systems, IoT"
                ));

                Student s4 = studentRepository.save(new Student(
                        "2022CS104", "Ananya Iyer", "ananya.iyer@college.edu",
                        "Computer Science", 8.20, 2026, "+91 9876543213", "React, TypeScript, Figma, UI/UX"
                ));

                CompanyDrive d1 = driveRepository.save(new CompanyDrive(
                        "Google", "Associate Software Engineer", 24.50,
                        "Bangalore / Hyderabad", 8.50, "Computer Science, Information Technology",
                        LocalDate.now().plusDays(15), "OPEN",
                        "Full-time engineering role focused on distributed systems and cloud scale services."
                ));

                CompanyDrive d2 = driveRepository.save(new CompanyDrive(
                        "Microsoft", "Software Development Engineer (SDE-1)", 22.00,
                        "Hyderabad", 8.00, "Computer Science, Information Technology, Electronics",
                        LocalDate.now().plusDays(20), "OPEN",
                        "Design and implement scalable microservices on Azure cloud infrastructure."
                ));

                CompanyDrive d3 = driveRepository.save(new CompanyDrive(
                        "TCS Digital", "Systems Engineer - Digital", 7.50,
                        "Pan India", 7.00, "All Engineering Branches",
                        LocalDate.now().plusDays(7), "OPEN",
                        "Fast-track digital career track focusing on AI/ML, cloud, and modern web applications."
                ));

                CompanyDrive d4 = driveRepository.save(new CompanyDrive(
                        "Infosys", "Specialist Programmer (Power Programmer)", 9.50,
                        "Pune / Mysore", 7.50, "Computer Science, Information Technology",
                        LocalDate.now().minusDays(5), "CLOSED",
                        "Competitive programming and algorithmic problem solving focus."
                ));

                // Sample applications
                applicationRepository.save(new Application(
                        s1, d1, LocalDateTime.now().minusDays(2), ApplicationStatus.SHORTLISTED,
                        "Shortlisted for Technical Round 1."
                ));

                applicationRepository.save(new Application(
                        s2, d2, LocalDateTime.now().minusDays(3), ApplicationStatus.SELECTED,
                        "Offer letter generated. Package: 22.0 LPA."
                ));

                applicationRepository.save(new Application(
                        s3, d3, LocalDateTime.now().minusDays(1), ApplicationStatus.APPLIED,
                        "Application under review by recruiter."
                ));

                logger.info("Successfully seeded database with students, drives, and applications.");
            }
        } catch (Exception e) {
            logger.warn("Seeding skipped or encountered non-fatal error: {}", e.getMessage());
        }
    }
}
