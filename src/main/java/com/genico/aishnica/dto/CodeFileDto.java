package com.genico.aishnica.dto;

public class CodeFileDto {

    private Long id;
    private String name;
    private String path;
    private String content;
    private Long projectId;

    // Конструкторы, геттеры и сеттеры
    public CodeFileDto() {
    }

    public CodeFileDto(Long id, String name, String path, String content, Long projectId) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.content = content;
        this.projectId = projectId;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}