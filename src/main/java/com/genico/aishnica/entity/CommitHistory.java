package com.genico.aishnica.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class CommitHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String commitMessage;
    private LocalDateTime commitDate;
    private String aiInteraction;
    private Long projectId;
    private Long taskId;

    // Конструкторы, геттеры и сеттеры
    public CommitHistory() {
    }

    public CommitHistory(String commitMessage, LocalDateTime commitDate, String aiInteraction, Long projectId, Long taskId) {
        this.commitMessage = commitMessage;
        this.commitDate = commitDate;
        this.aiInteraction = aiInteraction;
        this.projectId = projectId;
        this.taskId = taskId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommitMessage() {
        return commitMessage;
    }

    public void setCommitMessage(String commitMessage) {
        this.commitMessage = commitMessage;
    }

    public LocalDateTime getCommitDate() {
        return commitDate;
    }

    public void setCommitDate(LocalDateTime commitDate) {
        this.commitDate = commitDate;
    }

    public String getAiInteraction() {
        return aiInteraction;
    }

    public void setAiInteraction(String aiInteraction) {
        this.aiInteraction = aiInteraction;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}