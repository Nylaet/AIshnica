package com.genico.aishnica.controller;

import com.genico.aishnica.dto.ProjectDto;
import com.genico.aishnica.service.ProjectService;
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

public class ProjectControllerTest {

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private ProjectController projectController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(projectController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetAllProjects() throws Exception {
        List<ProjectDto> projectDtos = Arrays.asList(
                new ProjectDto(1L, "Project 1", "Description 1", LocalDateTime.now(), "https://github.com/project1"),
                new ProjectDto(2L, "Project 2", "Description 2", LocalDateTime.now(), "https://github.com/project2")
        );
        when(projectService.getAllProjects()).thenReturn(projectDtos);

        mockMvc.perform(get("/projects")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Project 1"))
                .andExpect(jsonPath("$[1].name").value("Project 2"));
    }

    @Test
    public void testGetProjectById() throws Exception {
        ProjectDto projectDto = new ProjectDto(1L, "Project 1", "Description 1", LocalDateTime.now(), "https://github.com/project1");
        when(projectService.getProjectById(1L)).thenReturn(Optional.of(projectDto));

        mockMvc.perform(get("/projects/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Project 1"));
    }

    @Test
    public void testCreateProject() throws Exception {
        ProjectDto projectDto = new ProjectDto(null, "Project 1", "Description 1", null, "https://github.com/project1");
        when(projectService.createProject(projectDto)).thenReturn(projectDto);

        mockMvc.perform(post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(projectDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Project 1"));
    }

    @Test
    public void testUpdateProject() throws Exception {
        ProjectDto projectDto = new ProjectDto(1L, "Project 2", "Description 2", LocalDateTime.now(), "https://github.com/project2");
        when(projectService.updateProject(1L, projectDto)).thenReturn(projectDto);

        mockMvc.perform(put("/projects/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(projectDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Project 2"));
    }

    @Test
    public void testDeleteProject() throws Exception {
        mockMvc.perform(delete("/projects/1"))
                .andExpect(status().isNoContent());
    }
}