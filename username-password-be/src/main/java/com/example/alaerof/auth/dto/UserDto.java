package com.example.alaerof.auth.dto;

import lombok.Builder;
import lombok.Value;

import java.time.Instant;
import java.util.UUID;

@Value
@Builder
public class UserDto {
    UUID id;
    String username;
    String email;
    Instant createdAt;
}
