package com.genico.aishnica.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private LocalDateTime creationDate;
    private String repositoryUrl;

    // Конструкторы, геттеры и сеттеры
    public Project() {
    }

    public Project(String name, String description, LocalDateTime creationDate, String repositoryUrl) {
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.repositoryUrl = repositoryUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    public void setRepositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
    }
}