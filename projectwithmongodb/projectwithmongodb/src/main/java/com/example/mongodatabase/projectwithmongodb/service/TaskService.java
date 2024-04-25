package com.example.mongodatabase.projectwithmongodb.service;

import com.example.mongodatabase.projectwithmongodb.model.Task;
import com.example.mongodatabase.projectwithmongodb.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    //crud operation
    public Task addTask(Task task){
        task.setTaskId(UUID.randomUUID().toString().split("-")[0]);
     return taskRepository.save(task);
    }

    public List<Task> fetchAllTask(){
        return taskRepository.findAll();
    }

    public Task getTaskById(String taskId){
        return taskRepository.findById(taskId).get();
    }

    public List<Task> getTaskBySeverity(int severity){
        return  taskRepository.findBySeverity(severity);
    }

    public List<Task> getTaskByAssignee(String assigneeName){
        return taskRepository.findByAssigneeName(assigneeName);
    }

    public Task updateTask(Task requestedTask){
        //get the task from the document from DB
        //populate new value from the request to the existing object
       Task existingTask= taskRepository.findById(requestedTask.getTaskId()).get();
        existingTask.setDescription(existingTask.getDescription());
        existingTask.setSeverity(requestedTask.getSeverity());
        existingTask.setAssignee(requestedTask.getAssignee());
        existingTask.setStoryPoint(requestedTask.getStoryPoint());

       return taskRepository.save(existingTask);

    }

    public String deleteTask(String taskId){
         taskRepository.deleteById(taskId);
         return "Task deleted from Database";
    }
}
