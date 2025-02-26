package com.genico.aishnica.service;

import com.genico.aishnica.dto.AiSolutionDto;
import com.genico.aishnica.entity.AiSolution;
import com.genico.aishnica.repository.AiSolutionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AiSolutionServiceTest {

    @Mock
    private AiSolutionRepository aiSolutionRepository;

    @InjectMocks
    private AiSolutionService aiSolutionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllAiSolutions() {
        List<AiSolution> aiSolutions = Arrays.asList(
                new AiSolution("AI1", "Solution1", 5, 1L),
                new AiSolution("AI2", "Solution2", 4, 2L)
        );
        when(aiSolutionRepository.findAll()).thenReturn(aiSolutions);

        List<AiSolutionDto> aiSolutionDtos = aiSolutionService.getAllAiSolutions();

        assertEquals(2, aiSolutionDtos.size());
        assertEquals("AI1", aiSolutionDtos.get(0).getAiName());
    }

    // Добавьте другие тесты для методов сервиса
}