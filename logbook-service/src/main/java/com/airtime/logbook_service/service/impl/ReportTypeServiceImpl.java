package com.airtime.logbook_service.service.impl;

import org.springframework.stereotype.Service;
import com.airtime.logbook_service.persistence.dao.ReportTypeRepository;
import com.airtime.logbook_service.persistence.model.ReportType;
import com.airtime.logbook_service.service.ReportTypeService;
import com.airtime.logbook_service.web.dto.ReportTypeDTO;

import java.util.ArrayList;
import java.util.List;

@Service("reportTypeService")
public class ReportTypeServiceImpl implements ReportTypeService {

    private final ReportTypeRepository reportTypeRepository;

    public ReportTypeServiceImpl(ReportTypeRepository reportTypeRepository) {
        this.reportTypeRepository = reportTypeRepository;
    }

    @Override
    public List<ReportTypeDTO> findAll() {
        List<ReportTypeDTO> reportTypeDTOList = new ArrayList<>();

        List<ReportType> reportTypeList = reportTypeRepository.findAll();

        if (!reportTypeList.isEmpty()) {
            reportTypeList.forEach(reportType -> {
                reportTypeDTOList.add(reportType.dto());
            });
        }

        return reportTypeDTOList;
    }

    @Override
    public boolean save(ReportType reportType) {
        boolean saved = false;
        if (!reportTypeRepository.existsByName(reportType.getName())) {
            try {
                this.reportTypeRepository.save(reportType);
                saved = true;
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        return saved;
    }
}
