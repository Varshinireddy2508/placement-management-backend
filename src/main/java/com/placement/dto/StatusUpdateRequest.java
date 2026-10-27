package com.placement.dto;

import com.placement.entity.ApplicationStatus;

public class StatusUpdateRequest {

    private ApplicationStatus status;
    private String remarks;

    public StatusUpdateRequest() {}

    public StatusUpdateRequest(ApplicationStatus status, String remarks) {
        this.status = status;
        this.remarks = remarks;
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
