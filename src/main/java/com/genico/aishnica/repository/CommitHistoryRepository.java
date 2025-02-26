package com.genico.aishnica.repository;

import com.genico.aishnica.entity.CommitHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommitHistoryRepository extends JpaRepository<CommitHistory, Long> {
}