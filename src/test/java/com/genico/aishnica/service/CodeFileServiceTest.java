package com.genico.aishnica.service;

import com.genico.aishnica.dto.CodeFileDto;
import com.genico.aishnica.entity.CodeFile;
import com.genico.aishnica.repository.CodeFileRepository;
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

public class CodeFileServiceTest {

    @Mock
    private CodeFileRepository codeFileRepository;

    @InjectMocks
    private CodeFileService codeFileService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllCodeFiles() {
        List<CodeFile> codeFiles = Arrays.asList(
                new CodeFile("File1.java", "/path/to/file1.java", "public class File1 {}", 1L),
                new CodeFile("File2.java", "/path/to/file2.java", "public class File2 {}", 2L)
        );
        when(codeFileRepository.findAll()).thenReturn(codeFiles);

        List<CodeFileDto> codeFileDtos = codeFileService.getAllCodeFiles();

        assertEquals(2, codeFileDtos.size());
        assertEquals("File1.java", codeFileDtos.get(0).getName());
    }

    @Test
    public void testGetCodeFileById() {
        CodeFile codeFile = new CodeFile("File1.java", "/path/to/file1.java", "public class File1 {}", 1L);
        when(codeFileRepository.findById(1L)).thenReturn(Optional.of(codeFile));

        Optional<CodeFileDto> codeFileDto = codeFileService.getCodeFileById(1L);

        assertEquals("File1.java", codeFileDto.get().getName());
    }

    @Test
    public void testCreateCodeFile() {
        CodeFileDto codeFileDto = new CodeFileDto(null, "File1.java", "/path/to/file1.java", "public class File1 {}", 1L);
        CodeFile codeFile = new CodeFile("File1.java", "/path/to/file1.java", "public class File1 {}", 1L);
        when(codeFileRepository.save(any(CodeFile.class))).thenReturn(codeFile);

        CodeFileDto createdCodeFileDto = codeFileService.createCodeFile(codeFileDto);

        assertEquals("File1.java", createdCodeFileDto.getName());
    }

    @Test
    public void testUpdateCodeFile() {
        CodeFileDto codeFileDto = new CodeFileDto(1L, "File1.java", "/path/to/file1.java", "public class File1 {}", 1L);
        CodeFile codeFile = new CodeFile("File1.java", "/path/to/file1.java", "public class File1 {}", 1L);
        when(codeFileRepository.save(any(CodeFile.class))).thenReturn(codeFile);

        CodeFileDto updatedCodeFileDto = codeFileService.updateCodeFile(1L, codeFileDto);

        assertEquals("File1.java", updatedCodeFileDto.getName());
    }

    @Test
    public void testDeleteCodeFile() {
        codeFileService.deleteCodeFile(1L);
        verify(codeFileRepository, times(1)).deleteById(1L);
    }
}