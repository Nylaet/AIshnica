package com.genico.aishnica.service;

import com.genico.aishnica.dto.CommitHistoryDto;
import com.genico.aishnica.entity.CommitHistory;
import com.genico.aishnica.repository.CommitHistoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CommitHistoryServiceTest {

    @Mock
    private CommitHistoryRepository commitHistoryRepository;

    @InjectMocks
    private CommitHistoryService commitHistoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllCommitHistories() {
        List<CommitHistory> commitHistories = Arrays.asList(
                new CommitHistory("Commit 1", LocalDateTime.now(), "AI interaction 1", 1L, 1L),
                new CommitHistory("Commit 2", LocalDateTime.now(), "AI interaction 2", 2L, 2L)
        );
        when(commitHistoryRepository.findAll()).thenReturn(commitHistories);

        List<CommitHistoryDto> commitHistoryDtos = commitHistoryService.getAllCommitHistories();

        assertEquals(2, commitHistoryDtos.size());
        assertEquals("Commit 1", commitHistoryDtos.get(0).getCommitMessage());
    }

    @Test
    public void testGetCommitHistoryById() {
        CommitHistory commitHistory = new CommitHistory("Commit 1", LocalDateTime.now(), "AI interaction 1", 1L, 1L);
        when(commitHistoryRepository.findById(1L)).thenReturn(Optional.of(commitHistory));

        Optional<CommitHistoryDto> commitHistoryDto = commitHistoryService.getCommitHistoryById(1L);

        assertEquals("Commit 1", commitHistoryDto.get().getCommitMessage());
    }

    @Test
    public void testCreateCommitHistory() {
        CommitHistoryDto commitHistoryDto = new CommitHistoryDto(null, "Commit 1", LocalDateTime.now(), "AI interaction 1", 1L, 1L);
        CommitHistory commitHistory = new CommitHistory("Commit 1", LocalDateTime.now(), "AI interaction 1", 1L, 1L);
        when(commitHistoryRepository.save(any(CommitHistory.class))).thenReturn(commitHistory);

        CommitHistoryDto createdCommitHistoryDto = commitHistoryService.createCommitHistory(commitHistoryDto);

        assertEquals("Commit 1", createdCommitHistoryDto.getCommitMessage());
    }

    @Test
    public void testUpdateCommitHistory() {
        CommitHistoryDto commitHistoryDto = new CommitHistoryDto(1L, "Commit 2", LocalDateTime.now(), "AI interaction 2", 2L, 2L);
        CommitHistory commitHistory = new CommitHistory("Commit 2", LocalDateTime.now(), "AI interaction 2", 2L, 2L);
        when(commitHistoryRepository.save(any(CommitHistory.class))).thenReturn(commitHistory);

        CommitHistoryDto updatedCommitHistoryDto = commitHistoryService.updateCommitHistory(1L, commitHistoryDto);

        assertEquals("Commit 2", updatedCommitHistoryDto.getCommitMessage());
    }

    @Test
    public void testDeleteCommitHistory() {
        commitHistoryService.deleteCommitHistory(1L);
        verify(commitHistoryRepository, times(1)).deleteById(1L);
    }
}