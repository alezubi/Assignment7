package com.example.Module7;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoItemRepository extends JpaRepository<ToDoItem, Long> {
}