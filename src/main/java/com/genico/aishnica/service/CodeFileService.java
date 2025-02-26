package com.genico.aishnica.service;

import com.genico.aishnica.dto.CodeFileDto;
import com.genico.aishnica.entity.CodeFile;
import com.genico.aishnica.repository.CodeFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CodeFileService {

    private final CodeFileRepository codeFileRepository;

    @Autowired
    public CodeFileService(CodeFileRepository codeFileRepository) {
        this.codeFileRepository = codeFileRepository;
    }

    public List<CodeFileDto> getAllCodeFiles() {
        return codeFileRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<CodeFileDto> getCodeFileById(Long id) {
        return codeFileRepository.findById(id)
                .map(this::convertToDto);
    }

    public CodeFileDto createCodeFile(CodeFileDto codeFileDto) {
        CodeFile codeFile = convertToEntity(codeFileDto);
        CodeFile createdCodeFile = codeFileRepository.save(codeFile);
        return convertToDto(createdCodeFile);
    }

    public CodeFileDto updateCodeFile(Long id, CodeFileDto codeFileDto) {
        CodeFile codeFile = convertToEntity(codeFileDto);
        codeFile.setId(id);
        CodeFile updatedCodeFile = codeFileRepository.save(codeFile);
        return convertToDto(updatedCodeFile);
    }

    public void deleteCodeFile(Long id) {
        codeFileRepository.deleteById(id);
    }

    private CodeFileDto convertToDto(CodeFile codeFile) {
        return new CodeFileDto(codeFile.getId(), codeFile.getName(), codeFile.getPath(), codeFile.getContent(), codeFile.getProjectId());
    }

    private CodeFile convertToEntity(CodeFileDto codeFileDto) {
        return new CodeFile(codeFileDto.getName(), codeFileDto.getPath(), codeFileDto.getContent(), codeFileDto.getProjectId());
    }
}