package com.genico.aishnica.service;

import com.genico.aishnica.dto.CommitHistoryDto;
import com.genico.aishnica.entity.CommitHistory;
import com.genico.aishnica.repository.CommitHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommitHistoryService {

    private final CommitHistoryRepository commitHistoryRepository;

    @Autowired
    public CommitHistoryService(CommitHistoryRepository commitHistoryRepository) {
        this.commitHistoryRepository = commitHistoryRepository;
    }

    public List<CommitHistoryDto> getAllCommitHistories() {
        return commitHistoryRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<CommitHistoryDto> getCommitHistoryById(Long id) {
        return commitHistoryRepository.findById(id)
                .map(this::convertToDto);
    }

    public CommitHistoryDto createCommitHistory(CommitHistoryDto commitHistoryDto) {
        CommitHistory commitHistory = convertToEntity(commitHistoryDto);
        CommitHistory createdCommitHistory = commitHistoryRepository.save(commitHistory);
        return convertToDto(createdCommitHistory);
    }

    public CommitHistoryDto updateCommitHistory(Long id, CommitHistoryDto commitHistoryDto) {
        CommitHistory commitHistory = convertToEntity(commitHistoryDto);
        commitHistory.setId(id);
        CommitHistory updatedCommitHistory = commitHistoryRepository.save(commitHistory);
        return convertToDto(updatedCommitHistory);
    }

    public void deleteCommitHistory(Long id) {
        commitHistoryRepository.deleteById(id);
    }

    private CommitHistoryDto convertToDto(CommitHistory commitHistory) {
        return new CommitHistoryDto(commitHistory.getId(), commitHistory.getCommitMessage(), commitHistory.getCommitDate(), commitHistory.getAiInteraction(), commitHistory.getProjectId(), commitHistory.getTaskId());
    }

    private CommitHistory convertToEntity(CommitHistoryDto commitHistoryDto) {
        return new CommitHistory(commitHistoryDto.getCommitMessage(), commitHistoryDto.getCommitDate(), commitHistoryDto.getAiInteraction(), commitHistoryDto.getProjectId(), commitHistoryDto.getTaskId());
    }
}