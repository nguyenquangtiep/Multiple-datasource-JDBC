package com.mark.MultiDatasourceJDBC.repository;

import com.mark.MultiDatasourceJDBC.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TodoRepositoryImpl implements TodoRepository{

    @Autowired
    private final JdbcTemplate todosJdbcTemplate;

    public TodoRepositoryImpl(JdbcTemplate todosJdbcTemplate) {
        this.todosJdbcTemplate = todosJdbcTemplate;
    }

    @Override
    public List<Todo> findAll() {
        String sql = "SELECT * FROM todos";
        return todosJdbcTemplate.query(sql, new TodoRowMapper());
    }

    @Override
    public void save(Todo todo) {
        String sql = "INSERT INTO todos(name) VALUES (?)";
        todosJdbcTemplate.update(sql, todo.getName());
    }

    @Override
    public Todo findById(Long id) {
        String sql = "SELECT * FROM todos WHERE id=?";
        return todosJdbcTemplate.queryForObject(sql, new TodoRowMapper(), id);
    }

    @Override
    public void update(Todo todo) {
        String sql = "UPDATE todos SET name=? WHERE id=?";
        todosJdbcTemplate.update(sql, todo.getName(), todo.getId());
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM todos WHERE id=?";
        todosJdbcTemplate.update(sql, id);
    }

    private static class TodoRowMapper implements RowMapper<Todo>{
        @Override
        public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
            Todo todo = new Todo();
            todo.setId(rs.getLong("id"));
            todo.setName(rs.getString("name"));
            return todo;
        }
    }
}
