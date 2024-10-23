package com.airtime.todo_service.persistence.model;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JdbcTypeCode(SqlTypes.UUID)
    @Column(name = "UUID", columnDefinition = "uuid", unique = true)
    private UUID uuid;

    @Column(name = "NAME")
    private String name;

    @Column(name = "MONIKER")
    private String moniker;


    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "auth_email_address")
    private String authEmailAddress;

    @Column(name = "auth_user_id")
    private String authUserId;

    @OneToMany
    @JoinColumn(name = "owner_id", referencedColumnName = "uuid")
    private List<Todo> todoList;

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoniker() {
        return moniker;
    }

    public void setMoniker(String moniker) {
        this.moniker = moniker;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getAuthEmailAddress() {
        return authEmailAddress;
    }

    public void setAuthEmailAddress(String authEmailAddress) {
        this.authEmailAddress = authEmailAddress;
    }

    public String getAuthUserId() {
        return authUserId;
    }

    public void setAuthUserId(String authUserId) {
        this.authUserId = authUserId;
    }


    public List<Todo> getTodoList() {
        return todoList;
    }

    public void setTodoList(List<Todo> todoList) {
        this.todoList = todoList;
    }
}
