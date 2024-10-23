package com.airtime.todo_service.persistence.model;

import com.airtime.todo_service.web.dto.TodoStatusDTO;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "todo_status", schema = "u283435265_dev_airtime", catalog = "")
public class TodoStatus {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "status")
    private String status;

    public TodoStatus() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TodoStatusDTO dto() {
        return TodoStatusDTO.builder()
                .id(this.getId())
                .status(this.getStatus())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TodoStatus that)) return false;
        return id == that.id && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status);
    }
}
