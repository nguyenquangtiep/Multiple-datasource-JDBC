package com.mark.MultiDatasourceJDBC.repository;

import com.mark.MultiDatasourceJDBC.model.Todo;
import com.mark.MultiDatasourceJDBC.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TopicRepositoryImpl implements TopicRepository{

    @Autowired
    private final JdbcTemplate topicsJdbcTemplate;

    public TopicRepositoryImpl(JdbcTemplate topicsJdbcTemplate) {
        this.topicsJdbcTemplate = topicsJdbcTemplate;
    }

    @Override
    public List<Topic> findAll() {
        String sql = "SELECT * FROM topics";
        return topicsJdbcTemplate.query(sql, new TopicRowMapper());
    }

    @Override
    public void save(Topic topic) {
        String sql = "INSERT INTO topics(name) VALUES (?)";
        topicsJdbcTemplate.update(sql, topic.getName());
    }

    @Override
    public Topic findById(Long id) {
        String sql = "SELECT * FROM topics WHERE id=?";
        return topicsJdbcTemplate.queryForObject(sql, new TopicRowMapper(), id);
    }

    @Override
    public void update(Topic topic) {
        String sql = "UPDATE topics SET name=? WHERE id=?";
        topicsJdbcTemplate.update(sql, topic.getName(), topic.getId());
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM topics WHERE id=?";
        topicsJdbcTemplate.update(sql, id);
    }

    private static class TopicRowMapper implements RowMapper<Topic> {
        @Override
        public Topic mapRow(ResultSet rs, int rowNum) throws SQLException {
            Topic topic = new Topic();
            topic.setId(rs.getLong("id"));
            topic.setName(rs.getString("name"));
            return topic;
        }
    }
}
