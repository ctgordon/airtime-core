package com.airtime.todo_service.web.dto;

import com.airtime.todo_service.persistence.model.TodoStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoDTO {
    private UUID id;
    private String description;
    private TodoStatus todoStatus;
    private UUID createdBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createdDate;
    private UUID updatedBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updatedDate;
}
