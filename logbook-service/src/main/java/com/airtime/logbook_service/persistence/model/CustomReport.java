package com.airtime.logbook_service.persistence.model;

import jakarta.persistence.*;
import com.airtime.logbook_service.web.dto.CustomReportDTO;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "custom_report", schema = "airtime", catalog = "")
public class CustomReport {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "report_type", referencedColumnName = "id")
    private ReportType reportType;
    @Basic
    @Column(name = "report_name", nullable = false)
    private String reportName;
    @Basic
    @Column(name = "start_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Basic
    @Column(name = "end_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "aircraft", referencedColumnName = "id")
    private Aircraft aircraft;
    @Basic
    @Column(name = "in_use", nullable = false)
    private boolean inUse;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ReportType getReportType() {
        return reportType;
    }

    public void setReportType(ReportType reportType) {
        this.reportType = reportType;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomReport that = (CustomReport) o;
        return id == that.id && reportType == that.reportType && inUse == that.inUse && reportName.equals(that.reportName) && startDate.equals(that.startDate) && endDate.equals(that.endDate) && aircraft.equals(that.aircraft);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reportType, reportName, startDate, endDate, aircraft, inUse);
    }

    public CustomReportDTO dto() {
        return CustomReportDTO.builder()
                .id(id)
                .reportType(reportType.dto())
                .reportName(reportName)
                .startDate(startDate)
                .endDate(endDate)
                .aircraft(aircraft.dto())
                .inUse(inUse)
                .build();
    }
}
