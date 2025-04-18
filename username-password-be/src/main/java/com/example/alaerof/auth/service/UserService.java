package com.example.alaerof.auth.service;

import com.example.alaerof.auth.dto.UserDto;
import com.example.alaerof.auth.persistence.UserApp;
import com.example.alaerof.auth.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void createUser(String username, String email, String rawPassword) {
        String encodedPassword = passwordEncoder.encode(rawPassword);
        UserApp user = UserApp.builder()
                .username(username)
                .email(email)
                .password(encodedPassword)
                .createdAt(Instant.now())
                .build();
        userRepository.save(user);
    }

    public UserApp getUser(String userEmail){
        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + userEmail));
    }

    public UserApp getUser(UUID userid){
        return userRepository.findById(userid)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + userid));
    }

    public UserApp getCurrentUser() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUser(userEmail);
    }

    public UserDto getCurrentUserDto() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        UserApp userApp = getUser(userEmail);
        return UserDto.builder()
                .id(userApp.getId())
                .username(userApp.getUsername())
                .email(userApp.getEmail())
                .createdAt(userApp.getCreatedAt())
                .build();
    }

}
