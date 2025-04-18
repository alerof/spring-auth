package com.example.alaerof.task.persistence;

import com.example.alaerof.auth.persistence.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(UserApp user);
}
