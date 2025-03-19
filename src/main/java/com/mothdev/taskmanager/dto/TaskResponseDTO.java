package com.mothdev.taskmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class TaskResponseDTO {

    private Long id;
    private String title;
    private String description;
    private boolean completed;
    private LocalDateTime dueTime;

}
