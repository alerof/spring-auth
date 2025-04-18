package com.example.alaerof.task.service;

import com.example.alaerof.auth.dto.UserDto;
import com.example.alaerof.auth.service.UserService;
import com.example.alaerof.task.dto.TaskDto;
import com.example.alaerof.task.persistence.Task;
import com.example.alaerof.task.persistence.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;

    public void createTask(TaskDto taskDto) {
        Task task = Task.builder()
                .user(userService.getCurrentUser())
                .description(taskDto.getDescription())
                .status(taskDto.getStatus())
                .createdAt(Instant.now())
                .build();
        taskRepository.save(task);
    }

    public List<TaskDto> getCurrentUserTasks() {
        return taskRepository.findByUser(userService.getCurrentUser())
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    private TaskDto mapToDto(Task task) {
        return TaskDto.builder()
                .id(task.getId())
                .user(UserDto.builder()
                        .id(task.getUser().getId())
                        .username(task.getUser().getUsername())
                        .email(task.getUser().getEmail())
                        .build())
                .description(task.getDescription())
                .status(task.getStatus())
                .createdAt(task.getCreatedAt())
                .build();
    }

    private Task mapToEntity(TaskDto taskDto) {
        return Task.builder()
                .id(taskDto.getId())
                .user(userService.getUser(taskDto.getUser().getId()))
                .description(taskDto.getDescription())
                .status(taskDto.getStatus())
                .createdAt(taskDto.getCreatedAt())
                .build();
    }
}
