package com.genico.aishnica.service;

import com.genico.aishnica.dto.TaskDto;
import com.genico.aishnica.entity.Task;
import com.genico.aishnica.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<TaskDto> getTaskById(Long id) {
        return taskRepository.findById(id)
                .map(this::convertToDto);
    }

    public TaskDto createTask(TaskDto taskDto) {
        Task task = convertToEntity(taskDto);
        Task createdTask = taskRepository.save(task);
        return convertToDto(createdTask);
    }

    public TaskDto updateTask(Long id, TaskDto taskDto) {
        Task task = convertToEntity(taskDto);
        task.setId(id);
        Task updatedTask = taskRepository.save(task);
        return convertToDto(updatedTask);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    private TaskDto convertToDto(Task task) {
        return new TaskDto(task.getId(), task.getName(), task.getDescription(), task.getStatus(), task.getCreationDate(), task.getProjectId());
    }

    private Task convertToEntity(TaskDto taskDto) {
        return new Task(taskDto.getName(), taskDto.getDescription(), taskDto.getStatus(), taskDto.getCreationDate(), taskDto.getProjectId());
    }
}