package com.genico.aishnica.controller;

import com.genico.aishnica.dto.CodeFileDto;
import com.genico.aishnica.service.CodeFileService;
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

public class CodeFileControllerTest {

    @Mock
    private CodeFileService codeFileService;

    @InjectMocks
    private CodeFileController codeFileController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(codeFileController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetAllCodeFiles() throws Exception {
        List<CodeFileDto> codeFileDtos = Arrays.asList(
                new CodeFileDto(1L, "File1.java", "/path/to/file1.java", "public class File1 {}", 1L),
                new CodeFileDto(2L, "File2.java", "/path/to/file2.java", "public class File2 {}", 2L)
        );
        when(codeFileService.getAllCodeFiles()).thenReturn(codeFileDtos);

        mockMvc.perform(get("/code-files")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("File1.java"))
                .andExpect(jsonPath("$[1].name").value("File2.java"));
    }

    @Test
    public void testGetCodeFileById() throws Exception {
        CodeFileDto codeFileDto = new CodeFileDto(1L, "File1.java", "/path/to/file1.java", "public class File1 {}", 1L);
        when(codeFileService.getCodeFileById(1L)).thenReturn(Optional.of(codeFileDto));

        mockMvc.perform(get("/code-files/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("File1.java"));
    }

    @Test
    public void testCreateCodeFile() throws Exception {
        CodeFileDto codeFileDto = new CodeFileDto(null, "File1.java", "/path/to/file1.java", "public class File1 {}", 1L);
        when(codeFileService.createCodeFile(codeFileDto)).thenReturn(codeFileDto);

        mockMvc.perform(post("/code-files")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(codeFileDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("File1.java"));
    }

    @Test
    public void testUpdateCodeFile() throws Exception {
        CodeFileDto codeFileDto = new CodeFileDto(1L, "File2.java", "/path/to/file2.java", "public class File2 {}", 2L);
        when(codeFileService.updateCodeFile(1L, codeFileDto)).thenReturn(codeFileDto);

        mockMvc.perform(put("/code-files/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(codeFileDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("File2.java"));
    }

    @Test
    public void testDeleteCodeFile() throws Exception {
        mockMvc.perform(delete("/code-files/1"))
                .andExpect(status().isNoContent());
    }
}