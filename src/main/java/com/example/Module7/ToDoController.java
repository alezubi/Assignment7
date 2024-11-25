package com.example.Module7;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class ToDoController {

    private final ToDoItemRepository toDoItemRepository;
    private static final Logger logger = LoggerFactory.getLogger(ToDoController.class);

    public ToDoController(ToDoItemRepository toDoItemRepository) {
        this.toDoItemRepository = toDoItemRepository;
    }

    // GET: Retrieve all tasks
    @GetMapping
    public List<ToDoItem> getAllToDoItems() {
        logger.info("Fetching all tasks");
        return toDoItemRepository.findAll();
    }

    // POST: Add a new task
    @PostMapping
    public ResponseEntity<ToDoItem> addToDoItem(@RequestBody ToDoItem toDoItem) {
        logger.info("Adding task: {}", toDoItem.getTask());
        ToDoItem savedToDoItem = toDoItemRepository.save(toDoItem);
        logger.info("Task added with ID: {}", savedToDoItem.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedToDoItem);
    }

    // GET: Retrieve a task by ID
    @GetMapping("/{id}")
    public ResponseEntity<ToDoItem> getToDoItemById(@PathVariable Long id) {
        logger.info("Fetching task with ID: {}", id);
        return toDoItemRepository.findById(id)
                .map(toDoItem -> ResponseEntity.ok(toDoItem))
                .orElseGet(() -> {
                    logger.warn("Task with ID: {} not found", id);
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                });
    }

    // DELETE: Delete a task by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteToDoItem(@PathVariable Long id) {
        if (toDoItemRepository.existsById(id)) {
            logger.info("Deleting task with ID: {}", id);
            toDoItemRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            logger.warn("Attempted to delete non-existent task with ID: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
