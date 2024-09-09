package com.airtime.logbook_service.persistence.model;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import com.airtime.logbook_service.web.dto.TodoDTO;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
public class Todo {

    @Id
    @JdbcTypeCode(SqlTypes.UUID)
    @Column(name = "ID", columnDefinition = "uuid", unique = true)
    private UUID id;

    @Basic
    @Column(name = "description")
    private String description;
    //@Basic
    //@Column(name = "status")
    //private int status;
    @Basic
    @Column(name = "created_by")
    private UUID createdBy;
    @Basic
    @Column(name = "created_date")
    private Timestamp createdDate;
    @Basic
    @Column(name = "updated_by")
    private UUID updatedBy;
    @Basic
    @Column(name = "updated_date")
    private Timestamp updatedDate;

    @Basic
    @Column(name = "owner_id")
    private UUID ownerId;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "status", referencedColumnName = "id")
    private TodoStatus todoStatus;

    public Todo() {
    }

    public TodoDTO dto() {
        return TodoDTO.builder()
                .id(this.getId())
                .description(this.getDescription())
                .todoStatus(this.getTodoStatus())
                .createdBy(this.getCreatedBy())
                .createdDate(this.getCreatedDate())
                .updatedBy(this.getUpdatedBy())
                .updatedDate(this.getUpdatedDate())
                .build();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UUID createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public UUID getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(UUID updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    public TodoStatus getTodoStatus() {
        return todoStatus;
    }

    public void setTodoStatus(TodoStatus todoStatus) {
        this.todoStatus = todoStatus;
    }
}
