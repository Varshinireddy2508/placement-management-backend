package com.placement.dto;

public class ApplicationRequest {

    private Long studentId;
    private Long companyDriveId;
    private String remarks;

    public ApplicationRequest() {}

    public ApplicationRequest(Long studentId, Long companyDriveId, String remarks) {
        this.studentId = studentId;
        this.companyDriveId = companyDriveId;
        this.remarks = remarks;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCompanyDriveId() {
        return companyDriveId;
    }

    public void setCompanyDriveId(Long companyDriveId) {
        this.companyDriveId = companyDriveId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
