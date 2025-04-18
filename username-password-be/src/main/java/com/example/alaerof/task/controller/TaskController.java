package com.example.alaerof.task.controller;

import com.example.alaerof.task.dto.TaskDto;
import com.example.alaerof.task.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public void addTask(@Valid @RequestBody TaskDto task) {
        taskService.createTask(task);
    }

    @GetMapping
    public List<TaskDto> getTasks() {
        return taskService.getCurrentUserTasks();
    }

    // this should be available only for ADMIN
    @GetMapping("/all")
    public List<TaskDto> getAllTasks() {
        return taskService.getAllTasks();
    }

    // ADMIN should be able to delete any
    // Regular USER should be able to delete only when task belongs to him
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
