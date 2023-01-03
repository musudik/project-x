package com.optum.portal.api.repository;

import com.optum.portal.api.model.ProgramActivityProgress;
import com.optum.portal.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProgramActivityProgressRepository extends JpaRepository<ProgramActivityProgress, Long> {


    @Query("Select pa from ProgramActivityProgress pa WHERE pa.program=:program and pa.user=:user")
    List<ProgramActivityProgress> getUserActivitiesForProgram(@Param("program") Long program, @Param("user") Long user);

    @Query("Select pa from ProgramActivityProgress pa WHERE pa.program=:program and pa.user=:user and pa.user=:user and pa.activity=:activity")
    ProgramActivityProgress getStatusOfProgramActivityByUser(@Param("program") Long program, @Param("user") Long user, @Param("activity") Long activity);

    @Modifying
    @Query("delete from ProgramActivityProgress pa WHERE pa.program=:program and pa.user=:user")
    void deleteUserActivitiesForProgram(@Param("program") Long program, @Param("user") Long user);
}
