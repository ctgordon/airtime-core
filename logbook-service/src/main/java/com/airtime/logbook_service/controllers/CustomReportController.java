package com.airtime.logbook_service.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.airtime.logbook_service.service.CustomReportService;
import com.airtime.logbook_service.service.ReportTypeService;
import com.airtime.logbook_service.web.dto.CustomReportDTO;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RestController
public class CustomReportController {
    private final CustomReportService customReportService;

    public CustomReportController(CustomReportService customReportService, ReportTypeService reportTypeService) {
        this.customReportService = customReportService;
    }

    @GetMapping(value = "/api/custom-reports")
    public List<CustomReportDTO> findAllAircraft() {
        return customReportService.getCustomReportList();
    }
}
