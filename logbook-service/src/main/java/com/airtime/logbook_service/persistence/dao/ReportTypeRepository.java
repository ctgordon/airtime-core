package com.airtime.logbook_service.persistence.dao;

import com.airtime.logbook_service.persistence.model.ReportType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportTypeRepository extends JpaRepository<ReportType, Integer> {
    boolean existsByName(String name);
}
