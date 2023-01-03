package com.optum.portal.api.repository;

import com.optum.portal.api.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IProgramRepository extends JpaRepository<Program, Long> {

    @Query("Select p from Program p WHERE p.program_name=:program_name")
    Program findByName(@Param("program_name") String program_name);
}