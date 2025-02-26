package com.genico.aishnica.service;

import com.genico.aishnica.dto.ProjectDto;
import com.genico.aishnica.entity.Project;
import com.genico.aishnica.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<ProjectDto> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<ProjectDto> getProjectById(Long id) {
        return projectRepository.findById(id)
                .map(this::convertToDto);
    }

    public ProjectDto createProject(ProjectDto projectDto) {
        Project project = convertToEntity(projectDto);
        Project createdProject = projectRepository.save(project);
        return convertToDto(createdProject);
    }

    public ProjectDto updateProject(Long id, ProjectDto projectDto) {
        Project project = convertToEntity(projectDto);
        project.setId(id);
        Project updatedProject = projectRepository.save(project);
        return convertToDto(updatedProject);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    private ProjectDto convertToDto(Project project) {
        return new ProjectDto(project.getId(), project.getName(), project.getDescription(), project.getCreationDate(), project.getRepositoryUrl());
    }

    private Project convertToEntity(ProjectDto projectDto) {
        return new Project(projectDto.getName(), projectDto.getDescription(), projectDto.getCreationDate(), projectDto.getRepositoryUrl());
    }
}