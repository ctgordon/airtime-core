package com.airtime.logbook_service.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.airtime.logbook_service.persistence.model.CustomReport;

@Repository
public interface CustomReportRepository extends JpaRepository<CustomReport, Integer> {
}
