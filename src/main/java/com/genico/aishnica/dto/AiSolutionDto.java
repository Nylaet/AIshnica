package com.genico.aishnica.dto;

public class AiSolutionDto {

    private Long id;
    private String aiName;
    private String solution;
    private Integer rating;
    private Long taskId;

    // Конструкторы, геттеры и сеттеры
    public AiSolutionDto() {
    }

    public AiSolutionDto(Long id, String aiName, String solution, Integer rating, Long taskId) {
        this.id = id;
        this.aiName = aiName;
        this.solution = solution;
        this.rating = rating;
        this.taskId = taskId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAiName() {
        return aiName;
    }

    public void setAiName(String aiName) {
        this.aiName = aiName;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}