package com.genico.aishnica.controller;

import com.genico.aishnica.dto.AiSolutionDto;
import com.genico.aishnica.service.AiSolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ai-solutions")
public class AiSolutionController {

    private final AiSolutionService aiSolutionService;

    @Autowired
    public AiSolutionController(AiSolutionService aiSolutionService) {
        this.aiSolutionService = aiSolutionService;
    }

    @GetMapping
    public ResponseEntity<List<AiSolutionDto>> getAllAiSolutions() {
        return new ResponseEntity<>(aiSolutionService.getAllAiSolutions(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AiSolutionDto> getAiSolutionById(@PathVariable Long id) {
        return aiSolutionService.getAiSolutionById(id)
                .map(aiSolutionDto -> new ResponseEntity<>(aiSolutionDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<AiSolutionDto> createAiSolution(@RequestBody AiSolutionDto aiSolutionDto) {
        AiSolutionDto createdDto = aiSolutionService.createAiSolution(aiSolutionDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(createdDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AiSolutionDto> updateAiSolution(@PathVariable Long id, @RequestBody AiSolutionDto aiSolutionDto) {
        return new ResponseEntity<>(aiSolutionService.updateAiSolution(id, aiSolutionDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAiSolution(@PathVariable Long id) {
        aiSolutionService.deleteAiSolution(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}