package com.example.Module7;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class ToDoController {

    @Autowired
    private ToDoList toDoList;

    @PostMapping
    public ResponseEntity<Void> addToDoItem(@RequestBody ToDoItem item) {
        toDoList.addToDoItem(item);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteToDoItem(@PathVariable Long id) {
        toDoList.deleteToDoItem(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ToDoItem>> getAllToDoItems() {
        List<ToDoItem> items = toDoList.getAllToDoItems();
        return ResponseEntity.ok(items);
    }
}
