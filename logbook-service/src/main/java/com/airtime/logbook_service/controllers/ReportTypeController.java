package com.airtime.logbook_service.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.airtime.logbook_service.persistence.model.ReportType;
import com.airtime.logbook_service.service.ReportTypeService;
import com.airtime.logbook_service.web.dto.ReportTypeDTO;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RestController
public class ReportTypeController {
    private final ReportTypeService reportTypeService;

    public ReportTypeController(ReportTypeService reportTypeService) {
        this.reportTypeService = reportTypeService;
    }

    @GetMapping(value = "/api/report-types")
    public List<ReportTypeDTO> findAll() {
        return reportTypeService.findAll();
    }

    @PostMapping(value = "/api/report-type/")
    public ResponseEntity<String> addAircraft(@RequestBody @Validated ReportTypeDTO reportTypeDTO) {
        boolean saved = false;

        try {
            ReportType reportType = new ReportType();
            reportType.setName(reportTypeDTO.getName());
            reportTypeService.save(reportType);
            saved = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (saved) ? new ResponseEntity<>("", HttpStatus.OK) : new ResponseEntity<>("Not saved", HttpStatus.BAD_REQUEST);
    }
}
