package com.genico.aishnica.service;

import com.genico.aishnica.dto.ProjectDto;
import com.genico.aishnica.entity.Project;
import com.genico.aishnica.repository.ProjectRepository;
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

public class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProjects() {
        List<Project> projects = Arrays.asList(
                new Project("Project 1", "Description 1", LocalDateTime.now(), "https://github.com/project1"),
                new Project("Project 2", "Description 2", LocalDateTime.now(), "https://github.com/project2")
        );
        when(projectRepository.findAll()).thenReturn(projects);

        List<ProjectDto> projectDtos = projectService.getAllProjects();

        assertEquals(2, projectDtos.size());
        assertEquals("Project 1", projectDtos.get(0).getName());
    }

    @Test
    public void testGetProjectById() {
        Project project = new Project("Project 1", "Description 1", LocalDateTime.now(), "https://github.com/project1");
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        Optional<ProjectDto> projectDto = projectService.getProjectById(1L);

        assertEquals("Project 1", projectDto.get().getName());
    }

    @Test
    public void testCreateProject() {
        ProjectDto projectDto = new ProjectDto(null, "Project 1", "Description 1", LocalDateTime.now(), "https://github.com/project1");
        Project project = new Project("Project 1", "Description 1", LocalDateTime.now(), "https://github.com/project1");
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        ProjectDto createdProjectDto = projectService.createProject(projectDto);

        assertEquals("Project 1", createdProjectDto.getName());
    }

    @Test
    public void testUpdateProject() {
        ProjectDto projectDto = new ProjectDto(1L, "Project 2", "Description 2", LocalDateTime.now(), "https://github.com/project2");
        Project project = new Project("Project 2", "Description 2", LocalDateTime.now(), "https://github.com/project2");
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        ProjectDto updatedProjectDto = projectService.updateProject(1L, projectDto);

        assertEquals("Project 2", updatedProjectDto.getName());
    }

    @Test
    public void testDeleteProject() {
        projectService.deleteProject(1L);
        verify(projectRepository, times(1)).deleteById(1L);
    }
}