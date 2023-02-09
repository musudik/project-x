package com.optum.portal.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "program", uniqueConstraints={@UniqueConstraint(columnNames = {"program_name"})})
@SequenceGenerator(name="seq_program", initialValue=1000, allocationSize=1)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Program extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	protected Program() {
	}
	public Program(String program_name, String description) {
		super();
		this.program_name = program_name;
		this.description = description;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="seq_program")
	@Column(name = "program_id", nullable = false)
	Long programId;
	
	@NotEmpty(message = "program name can't be empty")
	@Column(name = "program_name", nullable = false)
    private String program_name;
	
	@NotEmpty(message = "description can't be empty")
	@Column(name = "description", nullable = false)
    private String description;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "program_id", referencedColumnName="program_id")
	private List<Activity> activities = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;

	@Column(name = "active")
	private Boolean active;

	public Long getProgramId() {
		return programId;
	}

	public void setProgramId(Long programId) {
		this.programId = programId;
	}

	public String getProgram_name() {
		return program_name;
	}

	public void setProgram_name(String program_name) {
		this.program_name = program_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}
