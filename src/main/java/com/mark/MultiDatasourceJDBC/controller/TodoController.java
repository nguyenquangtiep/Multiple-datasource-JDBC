package com.mark.MultiDatasourceJDBC.controller;

import com.mark.MultiDatasourceJDBC.model.Todo;
import com.mark.MultiDatasourceJDBC.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    TodoRepository todoRepository;

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() {
        List<Todo> todos = todoRepository.findAll();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addTodo(@RequestBody Todo todo) {
        todoRepository.save(todo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getById(@PathVariable Long id) {
        Todo todo = todoRepository.findById(id);
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Todo> updateData(@RequestBody Todo todo) {
        todoRepository.update(todo);
        Todo updatedTodo = todoRepository.findById(todo.getId());
        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        todoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
