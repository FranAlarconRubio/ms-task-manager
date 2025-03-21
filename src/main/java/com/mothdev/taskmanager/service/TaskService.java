package com.mothdev.taskmanager.service;

import com.mothdev.taskmanager.dto.TaskRequestDTO;
import com.mothdev.taskmanager.dto.TaskResponseDTO;
import com.mothdev.taskmanager.exception.TaskNotFoundException;
import com.mothdev.taskmanager.mapper.PaginationMapper;
import com.mothdev.taskmanager.mapper.TaskMapper;
import com.mothdev.taskmanager.model.Task;
import com.mothdev.taskmanager.payload.PagedResponse;
import com.mothdev.taskmanager.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Optional;


@Service
public class TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public PagedResponse<TaskResponseDTO> getAllTasks(Pageable pageable) {
        logger.info("Se van a obtener el listado de tareas");

        Page<TaskResponseDTO> tasks = this.taskRepository.findAll(pageable).
                map(TaskMapper::toDto);

        return PaginationMapper.map(tasks);
    }

    public PagedResponse<TaskResponseDTO> getTasksFiltered(boolean completed, String title, Pageable pageable) {
        logger.info("Se van a obtener las tareas filtradas por titulo: {}", title);
        Page<TaskResponseDTO> tasks = taskRepository.findByCompletedAndTitleContainingIgnoreCase(completed, title, pageable).
                map(TaskMapper::toDto);

        return PaginationMapper.map(tasks);
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
                orElseThrow(() -> new TaskNotFoundException(id));

        TaskMapper.updateEntityFromDto(dto, task);
        Task updatedTask = taskRepository.save(task);

        return TaskMapper.toDto(updatedTask);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
