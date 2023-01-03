package com.optum.portal.api.repository;

import com.optum.portal.api.model.Reward;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRewardRepository extends JpaRepository<Reward, Long> {
}