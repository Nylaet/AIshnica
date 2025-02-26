package com.genico.aishnica.repository;

import com.genico.aishnica.entity.CodeFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeFileRepository extends JpaRepository<CodeFile, Long> {
}