package com.optum.portal.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "REWARD", uniqueConstraints={@UniqueConstraint(columnNames = {"reward_name"})})
@SequenceGenerator(name="seq_reward", initialValue=1000, allocationSize=1)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Reward extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	protected Reward() {
	}
	public Reward(String reward_name, String description) {
		super();
		this.reward_name = reward_name;
		this.description = description;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="seq_reward")
	@Column(name = "reward_id", nullable = false, insertable = false, updatable = false)
	Long rewardId;
	
	@NotEmpty(message = "Reward name can't be empty")
	@Column(name = "reward_name", nullable = false)
    private String reward_name;

	@NotEmpty(message = "Reward description can't be empty")
	@Column(name = "description", nullable = false)
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reward", nullable = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;

	public Long getRewardId() {
		return rewardId;
	}

	public void setRewardId(Long rewardId) {
		this.rewardId = rewardId;
	}

	public String getReward_name() {
		return reward_name;
	}

	public void setReward_name(String reward_name) {
		this.reward_name = reward_name;
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
}
