package com.airtime.logbook_service.service.impl;

import com.airtime.logbook_service.persistence.dao.GoalRepository;
import com.airtime.logbook_service.persistence.model.Goal;
import com.airtime.logbook_service.service.GoalService;
import com.airtime.logbook_service.web.dto.GoalDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("goalService")
public class GoalServiceImpl implements GoalService {
    private final GoalRepository goalRepository;

    public GoalServiceImpl(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    @Override
    public List<GoalDTO> goalDTOList() {
        List<GoalDTO> goalDTOList = new ArrayList<>();
        List<Goal> goals = goalRepository.findAll();
        if (!goals.isEmpty()) {
            goals.forEach(goal -> goalDTOList.add(goal.dto()));
        }

        return goalDTOList;
    }

    @Override
    public Goal findById(int id) {
        Optional<Goal> goal = goalRepository.findById(id);

        return goal.orElse(null);
    }

    @Override
    public boolean save(Goal goal) {
        boolean saved = false;

        try {
            goalRepository.save(goal);
            saved = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return saved;
    }
}
