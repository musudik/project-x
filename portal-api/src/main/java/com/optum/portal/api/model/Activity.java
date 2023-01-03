package com.optum.portal.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "ACTIVITY", uniqueConstraints={@UniqueConstraint(columnNames = {"activity_id", "activity"})})
@SequenceGenerator(name="seq_activity", initialValue=1000, allocationSize=1)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Activity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    protected Activity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="seq_activity")
    @Column(name = "activity_id", nullable = false)
    Long activityId;

    @NotEmpty(message = "activity can't be empty")
    @Column(name = "activity", nullable = false)
    private String activity;

    @NotEmpty(message = "description can't be empty")
    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Program program;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }
}
