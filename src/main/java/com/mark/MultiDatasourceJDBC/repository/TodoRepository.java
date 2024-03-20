package com.mark.MultiDatasourceJDBC.repository;

import com.mark.MultiDatasourceJDBC.model.Todo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository {
    List<Todo> findAll();
    void save(Todo todo);
    Todo findById(Long id);
    void update(Todo todo);
    void deleteById(Long id);
}
