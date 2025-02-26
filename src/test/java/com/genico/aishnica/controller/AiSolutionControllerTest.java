package com.genico.aishnica.controller;

import com.genico.aishnica.dto.AiSolutionDto;
import com.genico.aishnica.service.AiSolutionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AiSolutionControllerTest {

    @Mock
    private AiSolutionService aiSolutionService;

    @InjectMocks
    private AiSolutionController aiSolutionController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(aiSolutionController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetAllAiSolutions() throws Exception {
        List<AiSolutionDto> aiSolutionDtos = Arrays.asList(
                new AiSolutionDto(1L, "AI1", "Solution1", 5, 1L),
                new AiSolutionDto(2L, "AI2", "Solution2", 4, 2L)
        );
        when(aiSolutionService.getAllAiSolutions()).thenReturn(aiSolutionDtos);

        mockMvc.perform(get("/ai-solutions")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].aiName").value("AI1"))
                .andExpect(jsonPath("$[1].aiName").value("AI2"));
    }

    @Test
    public void testGetAiSolutionById() throws Exception {
        AiSolutionDto aiSolutionDto = new AiSolutionDto(1L, "AI1", "Solution1", 5, 1L);
        when(aiSolutionService.getAiSolutionById(1L)).thenReturn(Optional.of(aiSolutionDto));

        mockMvc.perform(get("/ai-solutions/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.aiName").value("AI1"));
    }

    @Test
    public void testCreateAiSolution() throws Exception {
        AiSolutionDto aiSolutionDto = new AiSolutionDto(null, "AI1", "Solution1", 5, 1L);
        when(aiSolutionService.createAiSolution(aiSolutionDto)).thenReturn(aiSolutionDto);

        mockMvc.perform(post("/ai-solutions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(aiSolutionDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.aiName").value("AI1"));
    }

    @Test
    public void testUpdateAiSolution() throws Exception {
        AiSolutionDto aiSolutionDto = new AiSolutionDto(1L, "AI2", "Solution2", 4, 2L);
        when(aiSolutionService.updateAiSolution(1L, aiSolutionDto)).thenReturn(aiSolutionDto);

        mockMvc.perform(put("/ai-solutions/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(aiSolutionDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.aiName").value("AI2"));
    }

    @Test
    public void testDeleteAiSolution() throws Exception {
        mockMvc.perform(delete("/ai-solutions/1"))
                .andExpect(status().isNoContent());
    }
}