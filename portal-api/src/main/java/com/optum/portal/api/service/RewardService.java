package com.optum.portal.api.service;

import com.optum.portal.api.model.Reward;
import com.optum.portal.api.repository.IRewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class RewardService {

    @Autowired
    private IRewardRepository rewardRepository;

    /**
     * save reward
     * @param reward
     * @return
     */
    public Reward save(Reward reward) {
        if(reward.getRewardId() != null) {
            reward.setUpdatedDate(LocalDate.now());
            reward.setUpdatedBy("System");
        } else {
            reward.setCreatedDate(LocalDate.now());
            reward.setCreatedBy("System");
        }
        return rewardRepository.save(reward);
    }

    /**
     * list all rewards
     * @return
     */
    public List<Reward> listRewards() { return rewardRepository.findAll(); }
}
