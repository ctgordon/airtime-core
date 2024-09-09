package com.airtime.logbook_service.service.impl;

import com.airtime.logbook_service.persistence.dao.CustomReportRepository;
import com.airtime.logbook_service.persistence.model.CustomReport;
import com.airtime.logbook_service.service.CustomReportService;
import com.airtime.logbook_service.web.dto.CustomReportDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("customReportService")
public class CustomReportServiceImpl implements CustomReportService {

    private final CustomReportRepository customReportRepository;

    public CustomReportServiceImpl(CustomReportRepository customReportRepository) {
        this.customReportRepository = customReportRepository;
    }

    @Override
    public CustomReport findById(int id) {
        Optional<CustomReport> customReport = customReportRepository.findById(id);

        return customReport.orElse(null);
    }

    @Override
    public List<CustomReportDTO> getCustomReportList() {
        List<CustomReportDTO> customReportDTOList = new ArrayList<>();
        List<CustomReport> customReportList = customReportRepository.findAll();

        if (!customReportList.isEmpty()) {
            customReportList.forEach(customReport -> {
                customReportDTOList.add(customReport.dto());
            });
        }

        return customReportDTOList;
    }
}
