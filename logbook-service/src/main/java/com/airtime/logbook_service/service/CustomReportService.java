package com.airtime.logbook_service.service;

import com.airtime.logbook_service.persistence.model.CustomReport;
import com.airtime.logbook_service.web.dto.CustomReportDTO;

import java.util.List;

public interface CustomReportService {
    List<CustomReportDTO> getCustomReportList();

    CustomReport findById(int id);
}
