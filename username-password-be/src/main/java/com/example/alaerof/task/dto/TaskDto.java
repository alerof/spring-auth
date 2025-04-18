package com.example.alaerof.task.dto;

import com.example.alaerof.auth.dto.UserDto;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class TaskDto {
    Long id;
    UserDto user;
    String description;
    String status;
    Instant createdAt;
}
