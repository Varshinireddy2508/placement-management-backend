package com.placement.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "company_drives")
public class CompanyDrive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String companyName;

    @Column(nullable = false, length = 150)
    private String jobRole;

    @Column(nullable = false)
    private Double packageLpa;

    @Column(length = 150)
    private String location;

    @Column(nullable = false)
    private Double minCgpa;

    @Column(length = 255)
    private String eligibleDepartments;

    private LocalDate driveDate;

    @Column(nullable = false, length = 30)
    private String status = "OPEN";

    @Column(columnDefinition = "TEXT")
    private String description;

    public CompanyDrive() {}

    public CompanyDrive(String companyName, String jobRole, Double packageLpa, String location, Double minCgpa, String eligibleDepartments, LocalDate driveDate, String status, String description) {
        this.companyName = companyName;
        this.jobRole = jobRole;
        this.packageLpa = packageLpa;
        this.location = location;
        this.minCgpa = minCgpa;
        this.eligibleDepartments = eligibleDepartments;
        this.driveDate = driveDate;
        this.status = status != null ? status : "OPEN";
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public Double getPackageLpa() {
        return packageLpa;
    }

    public void setPackageLpa(Double packageLpa) {
        this.packageLpa = packageLpa;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getMinCgpa() {
        return minCgpa;
    }

    public void setMinCgpa(Double minCgpa) {
        this.minCgpa = minCgpa;
    }

    public String getEligibleDepartments() {
        return eligibleDepartments;
    }

    public void setEligibleDepartments(String eligibleDepartments) {
        this.eligibleDepartments = eligibleDepartments;
    }

    public LocalDate getDriveDate() {
        return driveDate;
    }

    public void setDriveDate(LocalDate driveDate) {
        this.driveDate = driveDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
