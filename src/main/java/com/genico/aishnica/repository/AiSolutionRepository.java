package com.genico.aishnica.repository;

import com.genico.aishnica.entity.AiSolution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AiSolutionRepository extends JpaRepository<AiSolution, Long> {
}