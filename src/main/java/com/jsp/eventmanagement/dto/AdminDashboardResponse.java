
package com.jsp.eventmanagement.dto;

public class AdminDashboardResponse {

    private long totalUsers;
    private long totalEvents;
    private long totalVenues;
    private long totalCategories;
    private long totalRegistrations;
    private long totalPayments;

    public AdminDashboardResponse() {
    }

    public AdminDashboardResponse(
            long totalUsers,
            long totalEvents,
            long totalVenues,
            long totalCategories,
            long totalRegistrations,
            long totalPayments) {

        this.totalUsers = totalUsers;
        this.totalEvents = totalEvents;
        this.totalVenues = totalVenues;
        this.totalCategories = totalCategories;
        this.totalRegistrations = totalRegistrations;
        this.totalPayments = totalPayments;
    }

    public long getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(long totalUsers) {
        this.totalUsers = totalUsers;
    }

    public long getTotalEvents() {
        return totalEvents;
    }

    public void setTotalEvents(long totalEvents) {
        this.totalEvents = totalEvents;
    }

    public long getTotalVenues() {
        return totalVenues;
    }

    public void setTotalVenues(long totalVenues) {
        this.totalVenues = totalVenues;
    }

    public long getTotalCategories() {
        return totalCategories;
    }

    public void setTotalCategories(long totalCategories) {
        this.totalCategories = totalCategories;
    }

    public long getTotalRegistrations() {
        return totalRegistrations;
    }

    public void setTotalRegistrations(long totalRegistrations) {
        this.totalRegistrations = totalRegistrations;
    }

    public long getTotalPayments() {
        return totalPayments;
    }

    public void setTotalPayments(long totalPayments) {
        this.totalPayments = totalPayments;
    }
}
