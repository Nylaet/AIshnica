package com.genico.aishnica.service;

import com.genico.aishnica.dto.TaskDto;
import com.genico.aishnica.entity.Task;
import com.genico.aishnica.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllTasks() {
        List<Task> tasks = Arrays.asList(
                new Task("Task 1", "Description 1", "TODO", LocalDateTime.now(), 1L),
                new Task("Task 2", "Description 2", "IN_PROGRESS", LocalDateTime.now(), 2L)
        );
        when(taskRepository.findAll()).thenReturn(tasks);

        List<TaskDto> taskDtos = taskService.getAllTasks();

        assertEquals(2, taskDtos.size());
        assertEquals("Task 1", taskDtos.get(0).getName());
    }

    @Test
    public void testGetTaskById() {
        Task task = new Task("Task 1", "Description 1", "TODO", LocalDateTime.now(), 1L);
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Optional<TaskDto> taskDto = taskService.getTaskById(1L);

        assertEquals("Task 1", taskDto.get().getName());
    }

    @Test
    public void testCreateTask() {
        TaskDto taskDto = new TaskDto(null, "Task 1", "Description 1", "TODO", LocalDateTime.now(), 1L);
        Task task = new Task("Task 1", "Description 1", "TODO", LocalDateTime.now(), 1L);
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        TaskDto createdTaskDto = taskService.createTask(taskDto);

        assertEquals("Task 1", createdTaskDto.getName());
    }

    @Test
    public void testUpdateTask() {
        TaskDto taskDto = new TaskDto(1L, "Task 2", "Description 2", "IN_PROGRESS", LocalDateTime.now(), 2L);
        Task task = new Task("Task 2", "Description 2", "IN_PROGRESS", LocalDateTime.now(), 2L);
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        TaskDto updatedTaskDto = taskService.updateTask(1L, taskDto);

        assertEquals("Task 2", updatedTaskDto.getName());
    }

    @Test
    public void testDeleteTask() {
        taskService.deleteTask(1L);
        verify(taskRepository, times(1)).deleteById(1L);
    }
}