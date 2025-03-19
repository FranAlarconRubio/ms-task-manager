package com.mothdev.taskmanager.service;

import com.mothdev.taskmanager.dto.TaskRequestDTO;
import com.mothdev.taskmanager.dto.TaskResponseDTO;
import com.mothdev.taskmanager.mapper.TaskMapper;
import com.mothdev.taskmanager.model.Task;
import com.mothdev.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskResponseDTO> getAllTasks() {
        return this.taskRepository.findAll().
                stream().
                map(TaskMapper::toDto).
                collect(Collectors.toList());
    }

    public Optional<TaskResponseDTO> getTaskById(Long id) {
        return taskRepository.findById(id).
                map(TaskMapper::toDto);
    }

    public TaskResponseDTO createTask(TaskRequestDTO dto) {
        Task task = TaskMapper.toEntity(dto);
        Task savedTask = taskRepository.save(task);
        return TaskMapper.toDto(savedTask);
    }

    public TaskResponseDTO updateTask(Long id, TaskRequestDTO dto) {
        Task task = taskRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Task not found"));

        TaskMapper.updateEntityFromDto(dto, task);
        Task updatedTask = taskRepository.save(task);

        return TaskMapper.toDto(updatedTask);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
