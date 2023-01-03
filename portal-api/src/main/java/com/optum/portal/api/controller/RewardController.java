package com.optum.portal.api.controller;

import com.optum.portal.api.model.Reward;
import com.optum.portal.api.model.Result;
import com.optum.portal.api.model.Reward;
import com.optum.portal.api.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/reward")
public class RewardController {

    @Autowired
    private RewardService rewardService;

    /**
     *
     * @param Reward
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<Result> create(@RequestBody Reward Reward) {
        Result result = new Result();
        try {
            Reward newReward = new Reward(Reward.getReward_name(), Reward.getDescription());
            newReward = rewardService.save(newReward);
            result.setResult(Result.SUCCESS);
            result.setMessage("Reward creation successful : " + newReward.getReward_name());
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            result.setResult(Result.FAILED);
            result.setMessage("Reward creation failed : " + Reward.getReward_name()+ "." +
                    " Reason: The Reward given already exists, please try another Reward name");
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            result.setResult(Result.FAILED);
            result.setMessage("Reward creation failed: " + Reward.getReward_name()+ "." +
                    " Reason: "+e.getCause());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Reward>> getRewards() {
        try {
            List<Reward> rewards = rewardService.listRewards();
            if (rewards == null) {
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.CREATED);
            }
            return new ResponseEntity<>(rewards, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
