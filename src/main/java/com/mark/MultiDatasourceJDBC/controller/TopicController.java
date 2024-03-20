package com.mark.MultiDatasourceJDBC.controller;

import com.mark.MultiDatasourceJDBC.model.Todo;
import com.mark.MultiDatasourceJDBC.model.Topic;
import com.mark.MultiDatasourceJDBC.repository.TodoRepository;
import com.mark.MultiDatasourceJDBC.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topic")
public class TopicController {
    @Autowired
    TopicRepository topicRepository;

    @GetMapping
    public ResponseEntity<List<Topic>> getAllTopics() {
        List<Topic> topics = topicRepository.findAll();
        return new ResponseEntity<>(topics, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addTopic(@RequestBody Topic topic) {
        topicRepository.save(topic);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topic> getById(@PathVariable Long id) {
        Topic topic = topicRepository.findById(id);
        return new ResponseEntity<>(topic, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Topic> updateData(@RequestBody Topic topic) {
        topicRepository.update(topic);
        Topic updatedTopic = topicRepository.findById(topic.getId());
        return new ResponseEntity<>(updatedTopic, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        topicRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
