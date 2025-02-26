package com.genico.aishnica.controller;

import com.genico.aishnica.dto.CommitHistoryDto;
import com.genico.aishnica.service.CommitHistoryService;
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

public class CommitHistoryControllerTest {

    @Mock
    private CommitHistoryService commitHistoryService;

    @InjectMocks
    private CommitHistoryController commitHistoryController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(commitHistoryController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetAllCommitHistories() throws Exception {
        List<CommitHistoryDto> commitHistoryDtos = Arrays.asList(
                new CommitHistoryDto(1L, "Commit 1", LocalDateTime.now(), "AI interaction 1", 1L, 1L),
                new CommitHistoryDto(2L, "Commit 2", LocalDateTime.now(), "AI interaction 2", 2L, 2L)
        );
        when(commitHistoryService.getAllCommitHistories()).thenReturn(commitHistoryDtos);

        mockMvc.perform(get("/commit-history")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].commitMessage").value("Commit 1"))
                .andExpect(jsonPath("$[1].commitMessage").value("Commit 2"));
    }

    @Test
    public void testGetCommitHistoryById() throws Exception {
        CommitHistoryDto commitHistoryDto = new CommitHistoryDto(1L, "Commit 1", LocalDateTime.now(), "AI interaction 1", 1L, 1L);
        when(commitHistoryService.getCommitHistoryById(1L)).thenReturn(Optional.of(commitHistoryDto));

        mockMvc.perform(get("/commit-history/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.commitMessage").value("Commit 1"));
    }

    @Test
    public void testCreateCommitHistory() throws Exception {
        CommitHistoryDto commitHistoryDto = new CommitHistoryDto(null, "Commit 1", LocalDateTime.now(), "AI interaction 1", 1L, 1L);
        when(commitHistoryService.createCommitHistory(commitHistoryDto)).thenReturn(commitHistoryDto);

        mockMvc.perform(post("/commit-history")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commitHistoryDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.commitMessage").value("Commit 1"));
    }

    @Test
    public void testUpdateCommitHistory() throws Exception {
        CommitHistoryDto commitHistoryDto = new CommitHistoryDto(1L, "Commit 2", LocalDateTime.now(), "AI interaction 2", 2L, 2L);
        when(commitHistoryService.updateCommitHistory(1L, commitHistoryDto)).thenReturn(commitHistoryDto);

        mockMvc.perform(put("/commit-history/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commitHistoryDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.commitMessage").value("Commit 2"));
    }

    @Test
    public void testDeleteCommitHistory() throws Exception {
        mockMvc.perform(delete("/commit-history/1"))
                .andExpect(status().isNoContent());
    }
}