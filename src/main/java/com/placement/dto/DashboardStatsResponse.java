package com.placement.dto;

public class DashboardStatsResponse {
    private long totalStudents;
    private long totalDrives;
    private long activeDrives;
    private long totalApplications;
    private long selectedCount;
    private long shortlistedCount;

    public DashboardStatsResponse() {}

    public DashboardStatsResponse(long totalStudents, long totalDrives, long activeDrives, long totalApplications, long selectedCount, long shortlistedCount) {
        this.totalStudents = totalStudents;
        this.totalDrives = totalDrives;
        this.activeDrives = activeDrives;
        this.totalApplications = totalApplications;
        this.selectedCount = selectedCount;
        this.shortlistedCount = shortlistedCount;
    }

    public long getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(long totalStudents) {
        this.totalStudents = totalStudents;
    }

    public long getTotalDrives() {
        return totalDrives;
    }

    public void setTotalDrives(long totalDrives) {
        this.totalDrives = totalDrives;
    }

    public long getActiveDrives() {
        return activeDrives;
    }

    public void setActiveDrives(long activeDrives) {
        this.activeDrives = activeDrives;
    }

    public long getTotalApplications() {
        return totalApplications;
    }

    public void setTotalApplications(long totalApplications) {
        this.totalApplications = totalApplications;
    }

    public long getSelectedCount() {
        return selectedCount;
    }

    public void setSelectedCount(long selectedCount) {
        this.selectedCount = selectedCount;
    }

    public long getShortlistedCount() {
        return shortlistedCount;
    }

    public void setShortlistedCount(long shortlistedCount) {
        this.shortlistedCount = shortlistedCount;
    }
}
