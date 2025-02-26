package com.genico.aishnica.controller;

import com.genico.aishnica.dto.CommitHistoryDto;
import com.genico.aishnica.service.CommitHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commit-history")
public class CommitHistoryController {

    private final CommitHistoryService commitHistoryService;

    @Autowired
    public CommitHistoryController(CommitHistoryService commitHistoryService) {
        this.commitHistoryService = commitHistoryService;
    }

    @GetMapping
    public ResponseEntity<List<CommitHistoryDto>> getAllCommitHistories() {
        return new ResponseEntity<>(commitHistoryService.getAllCommitHistories(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommitHistoryDto> getCommitHistoryById(@PathVariable Long id) {
        return commitHistoryService.getCommitHistoryById(id)
                .map(commitHistoryDto -> new ResponseEntity<>(commitHistoryDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<CommitHistoryDto> createCommitHistory(@RequestBody CommitHistoryDto commitHistoryDto) {
        return new ResponseEntity<>(commitHistoryService.createCommitHistory(commitHistoryDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommitHistoryDto> updateCommitHistory(@PathVariable Long id, @RequestBody CommitHistoryDto commitHistoryDto) {
        return new ResponseEntity<>(commitHistoryService.updateCommitHistory(id, commitHistoryDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommitHistory(@PathVariable Long id) {
        commitHistoryService.deleteCommitHistory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}