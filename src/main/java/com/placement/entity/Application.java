package com.placement.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "applications", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"student_id", "company_drive_id"})
})
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(optional = false)
    @JoinColumn(name = "company_drive_id", nullable = false)
    private CompanyDrive companyDrive;

    @Column(nullable = false)
    private LocalDateTime appliedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private ApplicationStatus status = ApplicationStatus.APPLIED;

    @Column(length = 255)
    private String remarks;

    public Application() {}

    public Application(Student student, CompanyDrive companyDrive, LocalDateTime appliedAt, ApplicationStatus status, String remarks) {
        this.student = student;
        this.companyDrive = companyDrive;
        this.appliedAt = appliedAt != null ? appliedAt : LocalDateTime.now();
        this.status = status != null ? status : ApplicationStatus.APPLIED;
        this.remarks = remarks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public CompanyDrive getCompanyDrive() {
        return companyDrive;
    }

    public void setCompanyDrive(CompanyDrive companyDrive) {
        this.companyDrive = companyDrive;
    }

    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(LocalDateTime appliedAt) {
        this.appliedAt = appliedAt;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
