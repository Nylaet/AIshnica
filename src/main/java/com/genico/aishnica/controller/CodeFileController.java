package com.genico.aishnica.controller;

import com.genico.aishnica.dto.CodeFileDto;
import com.genico.aishnica.service.CodeFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/code-files")
public class CodeFileController {

    private final CodeFileService codeFileService;

    @Autowired
    public CodeFileController(CodeFileService codeFileService) {
        this.codeFileService = codeFileService;
    }

    @GetMapping
    public ResponseEntity<List<CodeFileDto>> getAllCodeFiles() {
        return new ResponseEntity<>(codeFileService.getAllCodeFiles(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CodeFileDto> getCodeFileById(@PathVariable Long id) {
        return codeFileService.getCodeFileById(id)
                .map(codeFileDto -> new ResponseEntity<>(codeFileDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<CodeFileDto> createCodeFile(@RequestBody CodeFileDto codeFileDto) {
        return new ResponseEntity<>(codeFileService.createCodeFile(codeFileDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CodeFileDto> updateCodeFile(@PathVariable Long id, @RequestBody CodeFileDto codeFileDto) {
        return new ResponseEntity<>(codeFileService.updateCodeFile(id, codeFileDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCodeFile(@PathVariable Long id) {
        codeFileService.deleteCodeFile(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}