package com.example.mongodatabase.projectwithmongodb.controller;

import com.example.mongodatabase.projectwithmongodb.model.Task;
import com.example.mongodatabase.projectwithmongodb.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task){
        return taskService.addTask(task);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getTaskList(){
        return taskService.fetchAllTask();
    }

    @GetMapping("/taskById/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public Task getTaskById(@PathVariable String taskId){
        return taskService.getTaskById(taskId);
    }

    @GetMapping("/taskBySeverity/{severity}")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getTaskBySeverity(@PathVariable int severity){
        return taskService.getTaskBySeverity(severity);
    }

    @GetMapping("/tasksByAssignee/{assignee}")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getTaskByAssignee(@PathVariable String assignee){
        return taskService.getTaskByAssignee(assignee);
    }

    @PutMapping
    public Task modifyTask(@RequestBody Task task){
        return taskService.updateTask(task);
    }

    @DeleteMapping("/{taskId}")
    public String deleteTask(@PathVariable String taskId){
        return taskService.deleteTask(taskId);
    }
}
