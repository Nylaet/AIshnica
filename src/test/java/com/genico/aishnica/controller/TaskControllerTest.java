package com.genico.aishnica.controller;

import com.genico.aishnica.dto.TaskDto;
import com.genico.aishnica.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetAllTasks() throws Exception {
        List<TaskDto> taskDtos = Arrays.asList(
                new TaskDto(1L, "Task 1", "Description 1", "TODO", LocalDateTime.now(), 1L),
                new TaskDto(2L, "Task 2", "Description 2", "IN_PROGRESS", LocalDateTime.now(), 2L)
        );
        when(taskService.getAllTasks()).thenReturn(taskDtos);

        mockMvc.perform(get("/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Task 1"))
                .andExpect(jsonPath("$[1].name").value("Task 2"));
    }

    @Test
    public void testGetTaskById() throws Exception {
        TaskDto taskDto = new TaskDto(1L, "Task 1", "Description 1", "TODO", LocalDateTime.now(), 1L);
        when(taskService.getTaskById(1L)).thenReturn(Optional.of(taskDto));

        mockMvc.perform(get("/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Task 1"));
    }

    @Test
    public void testCreateTask() throws Exception {
        TaskDto taskDto = new TaskDto(null, "Task 1", "Description 1", "TODO", LocalDateTime.now(), 1L);
        when(taskService.createTask(taskDto)).thenReturn(taskDto);

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taskDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Task 1"));
    }

    @Test
    public void testUpdateTask() throws Exception {
        TaskDto taskDto = new TaskDto(1L, "Task 2", "Description 2", "IN_PROGRESS", LocalDateTime.now(), 2L);
        when(taskService.updateTask(1L, taskDto)).thenReturn(taskDto);

        mockMvc.perform(put("/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taskDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Task 2"));
    }

    @Test
    public void testDeleteTask() throws Exception {
        mockMvc.perform(delete("/tasks/1"))
                .andExpect(status().isNoContent());
    }
}