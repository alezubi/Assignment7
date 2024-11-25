package com.example.Module7;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoList {

    private final ToDoItemRepository toDoItemRepository;
    private static final Logger logger = LoggerFactory.getLogger(ToDoList.class);

    @Autowired
    public ToDoList(ToDoItemRepository toDoItemRepository) {
        this.toDoItemRepository = toDoItemRepository;
    }

    public void addToDoItem(ToDoItem item) {
        toDoItemRepository.save(item);
        logger.info("Added task: {}", item.getTask());
    }

    public void deleteToDoItem(Long id) {
        if (toDoItemRepository.existsById(id)) {
            toDoItemRepository.deleteById(id);
            logger.info("Deleted task with ID: {}", id);
        } else {
            logger.warn("Invalid task ID: {}", id);
        }
    }

    public List<ToDoItem> getAllToDoItems() {
        logger.info("Retrieving all tasks");
        return toDoItemRepository.findAll();
    }
}
