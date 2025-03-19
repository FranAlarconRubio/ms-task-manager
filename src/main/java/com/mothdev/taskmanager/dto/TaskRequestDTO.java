package com.mothdev.taskmanager.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskRequestDTO {

    @NotBlank(message = "El titulo es obligatorio")
    @Size(max = 100, message = "El título no puede tener más de 100 caracteres")
    private String title;

    @Size(max = 500, message = "La descripción no puede tener más de 500 caracteres")
    private String description;

    @FutureOrPresent(message = "La fecha límite debe de ser futura o actual")
    private LocalDateTime dueTime;

}
