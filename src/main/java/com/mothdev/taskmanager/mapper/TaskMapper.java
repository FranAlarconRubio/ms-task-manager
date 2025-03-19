package com.mothdev.taskmanager.mapper;

import com.mothdev.taskmanager.dto.TaskRequestDTO;
import com.mothdev.taskmanager.dto.TaskResponseDTO;
import com.mothdev.taskmanager.model.Task;

public abstract class TaskMapper {

    public static Task toEntity(TaskRequestDTO dto) {
        Task task = new Task();

        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setDueTime(dto.getDueTime());
        task.setCompleted(false);

        return task;
    }

    public static TaskResponseDTO toDto(Task task) {
        return new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.isCompleted(),
                task.getDueTime()
        );
    }

    public static void updateEntityFromDto(TaskRequestDTO dto, Task task) {
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setDueTime(task.getDueTime());
    }
}
