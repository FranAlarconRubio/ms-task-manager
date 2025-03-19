package com.mothdev.taskmanager.controller;

import com.mothdev.taskmanager.dto.TaskRequestDTO;
import com.mothdev.taskmanager.dto.TaskResponseDTO;
import com.mothdev.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable Long id) {
        return this.taskService.getTaskById(id).
                map(ResponseEntity::ok).
                orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getAllTasks() {
        List<TaskResponseDTO> allTasks = taskService.getAllTasks();

        if(allTasks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(allTasks);
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@Valid @RequestBody TaskRequestDTO task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long id, @Valid @RequestBody TaskRequestDTO task) {
        return ResponseEntity.ok(taskService.updateTask(id, task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }
}
