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

    @Basic
    @Column(name = "title")
    private String title;
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
    @Column(name = "user_id")
    private UUID userId;

    @Basic
    @Column(name = "priority")
    private int priority;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "status", referencedColumnName = "id")
    private TodoStatus todoStatus;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private Profile profile;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID ownerId) {
        this.userId = ownerId;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public TodoStatus getTodoStatus() {
        return todoStatus;
    }

    public void setTodoStatus(TodoStatus todoStatus) {
        this.todoStatus = todoStatus;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
