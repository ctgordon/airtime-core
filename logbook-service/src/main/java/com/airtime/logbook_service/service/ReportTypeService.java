package com.airtime.logbook_service.service;

import com.airtime.logbook_service.persistence.model.ReportType;
import com.airtime.logbook_service.web.dto.ReportTypeDTO;

import java.util.List;

public interface ReportTypeService {
    List<ReportTypeDTO> findAll();

    boolean save(ReportType reportType);
}
