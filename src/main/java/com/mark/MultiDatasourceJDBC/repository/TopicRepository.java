package com.mark.MultiDatasourceJDBC.repository;

import com.mark.MultiDatasourceJDBC.model.Todo;
import com.mark.MultiDatasourceJDBC.model.Topic;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository {
    List<Topic> findAll();
    void save(Topic topic);
    Topic findById(Long id);
    void update(Topic topic);
    void deleteById(Long id);
}
