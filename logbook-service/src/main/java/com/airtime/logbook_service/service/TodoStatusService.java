package com.airtime.logbook_service.service;

import com.airtime.logbook_service.web.dto.TodoStatusDTO;

import java.util.List;

public interface TodoStatusService {
    List<TodoStatusDTO> getTodoStatusDTOList();
}
