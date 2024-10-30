package com.example.Module7;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoList {

    private final ToDoItemRepository toDoItemRepository;

    @Autowired
    public ToDoList(ToDoItemRepository toDoItemRepository) {
        this.toDoItemRepository = toDoItemRepository;
    }

    public void addToDoItem(ToDoItem item) {
        toDoItemRepository.save(item);
        System.out.println("Added: " + item.getTask());
    }

    public void deleteToDoItem(Long id) {
        if (toDoItemRepository.existsById(id)) {
            toDoItemRepository.deleteById(id);
            System.out.println("Deleted item with ID: " + id);
        } else {
            System.out.println("Invalid item ID.");
        }
    }

    public List<ToDoItem> getAllToDoItems() {
        return toDoItemRepository.findAll();
    }
}