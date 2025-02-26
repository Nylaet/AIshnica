package com.genico.aishnica.service;

import com.genico.aishnica.dto.AiSolutionDto;
import com.genico.aishnica.entity.AiSolution;
import com.genico.aishnica.repository.AiSolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AiSolutionService {

    private final AiSolutionRepository aiSolutionRepository;

    @Autowired
    public AiSolutionService(AiSolutionRepository aiSolutionRepository) {
        this.aiSolutionRepository = aiSolutionRepository;
    }

    public List<AiSolutionDto> getAllAiSolutions() {
        return aiSolutionRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<AiSolutionDto> getAiSolutionById(Long id) {
        return aiSolutionRepository.findById(id)
                .map(this::convertToDto);
    }

    public AiSolutionDto createAiSolution(AiSolutionDto aiSolutionDto) {
        AiSolution aiSolution = convertToEntity(aiSolutionDto);
        AiSolution createdAiSolution = aiSolutionRepository.save(aiSolution);
        return convertToDto(createdAiSolution);
    }

    public AiSolutionDto updateAiSolution(Long id, AiSolutionDto aiSolutionDto) {
        AiSolution aiSolution = convertToEntity(aiSolutionDto);
        aiSolution.setId(id);
        AiSolution updatedAiSolution = aiSolutionRepository.save(aiSolution);
        return convertToDto(updatedAiSolution);
    }

    public void deleteAiSolution(Long id) {
        aiSolutionRepository.deleteById(id);
    }

    private AiSolutionDto convertToDto(AiSolution aiSolution) {
        return new AiSolutionDto(aiSolution.getId(), aiSolution.getAiName(), aiSolution.getSolution(), aiSolution.getRating(), aiSolution.getTaskId());
    }

    private AiSolution convertToEntity(AiSolutionDto aiSolutionDto) {
        return new AiSolution(aiSolutionDto.getAiName(), aiSolutionDto.getSolution(), aiSolutionDto.getRating(), aiSolutionDto.getTaskId());
    }
}