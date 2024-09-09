package com.airtime.logbook_service.service;

import com.airtime.logbook_service.persistence.model.Goal;
import com.airtime.logbook_service.web.dto.GoalDTO;

import java.util.List;

public interface GoalService {
    List<GoalDTO> goalDTOList();

    Goal findById(int id);

    boolean save(Goal goal);
}
