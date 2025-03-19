package com.mothdev.taskmanager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private boolean completed;

    private LocalDateTime dueTime;

    public Task() {
    }

    public Task(String title, String description, LocalDateTime dueTime) {
        this.title = title;
        this.description = description;
        this.completed = false;
        this.dueTime = dueTime;
    }
}
