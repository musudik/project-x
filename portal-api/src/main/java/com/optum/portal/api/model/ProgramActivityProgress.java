package com.optum.portal.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ProgramActivityProgress", uniqueConstraints={@UniqueConstraint(columnNames = {"program","activity","user"})})
@SequenceGenerator(name="seq_program_activity", initialValue=1000, allocationSize=1)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProgramActivityProgress {

    public ProgramActivityProgress() {
    }

    public ProgramActivityProgress(Long program, Long activity, Long user, Boolean complete) {
        this.program = program;
        this.activity = activity;
        this.user = user;
        this.complete = complete;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_program_activity")
    @Column(name = "program_activity_id", nullable = false, insertable = false, updatable = false)
    Long program_activity_id;

    @Column(name = "program", nullable = false)
    private Long program;

    @Column(name = "activity", nullable = false)
    private Long activity;

    @Column(name = "user", nullable = false)
    private Long user;

    @Column(name = "complete", nullable = false)
    private Boolean complete;

    @Transient
    private List<Long> activities;

    public Long getProgram_activity_id() {
        return program_activity_id;
    }

    public void setProgram_activity_id(Long program_activity_id) {
        this.program_activity_id = program_activity_id;
    }

    public Long getProgram() {
        return program;
    }

    public void setProgram(Long program) {
        this.program = program;
    }

    public Long getActivity() {
        return activity;
    }

    public void setActivity(Long activity) {
        this.activity = activity;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Boolean isComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public List<Long> getActivities() {
        return activities;
    }

    public void setActivities(List<Long> activities) {
        this.activities = activities;
    }
}
