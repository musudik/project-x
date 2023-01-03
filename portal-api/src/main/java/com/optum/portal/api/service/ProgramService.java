package com.optum.portal.api.service;

import com.optum.portal.api.model.*;
import com.optum.portal.api.repository.IProgramActivityProgressRepository;
import com.optum.portal.api.repository.IProgramRepository;
import com.optum.portal.api.repository.IRewardRepository;
import com.optum.portal.api.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProgramService {

    @Autowired
    private IProgramRepository programRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRewardRepository rewardRepository;

    @Autowired
    private IProgramActivityProgressRepository activityProgressRepository;

    /**
     * save program
     * @param program
     * @return
     */
    public Program save(Program program) {
        if(program.getProgramId() != null) {
            program.setUpdatedDate(LocalDate.now());
            program.setUpdatedBy("System");
        } else {
            program.setCreatedDate(LocalDate.now());
            program.setCreatedBy("System");
        }
        return programRepository.save(program);
    }

    /**
     * list all available programs
     * @return
     */
    public List<Program> listPrograms() { return programRepository.findAll(); }

    /**
     * get a program by name
     * @param name
     * @return
     */
    public Program getProgramByName(String name) { return programRepository.findByName(name); }

    /**
     * get a program by id
     * @param id
     * @return
     */
    public Optional<Program> getProgramById(Long id) { return programRepository.findById(id); }

    /**
     * register user for a program
     * @param request
     * @return
     */
    public User registerProgram(EnrollProgramRequest request) {
        User selectedUser = null;
        Optional<User> user = userRepository.findById(request.getUserId());
        if(user.isPresent()) {
            selectedUser = user.get();
            List<Program> programs = listPrograms();
            List<Program> selectedPrograms = programs.stream().filter(p -> request.getProgramIds().contains(p.getProgramId())).collect(Collectors.toList());
            if(selectedUser.getPrograms() == null) {
                selectedUser.setPrograms(new ArrayList<>());
            }
            selectedUser.getPrograms().addAll(selectedPrograms);
            selectedUser = userRepository.save(selectedUser);

            //Create activity for Users (default)
            /*for(Program program : programs) {
                ProgramActivityProgress activityProgress = new ProgramActivityProgress();
                activityProgress.setProgram(program.getProgramId());
                activityProgress.setActivities(program.getActivities().stream().map(a->a.getActivityId()).collect(Collectors.toList()));
                activityProgress.setUser(selectedUser.getId());
                activityProgress.setComplete(false);

                createActivity(activityProgress);
            }*/

            /*int programsRegistered =  selectedUser.getPrograms().size();
            if(programsRegistered > 1) {
                List<Reward> rewardsList = rewardRepository.findAll();
                switch (programsRegistered) {
                    case 2:
                        selectedUser.getRewards().add(rewardsList.get(0));
                        break;
                    case 4:
                        selectedUser.getRewards().add(rewardsList.get(1));
                        break;
                    case 5:
                        selectedUser.getRewards().add(rewardsList.get(2));
                        break;
                    case 6:
                        selectedUser.getRewards().add(rewardsList.get(3));
                        break;
                }
                selectedUser = userRepository.save(selectedUser);
            }*/
        }
        return selectedUser;
    }

    /**
     * deregister user for a program
     * @param request
     * @return
     */
    @Transactional
    public User deregisterProgram(EnrollProgramRequest request) {
        User selectedUser = null;
        Optional<User> user = userRepository.findById(request.getUserId());
        if(user.isPresent()) {
            selectedUser = user.get();
            selectedUser.getPrograms().removeIf(p -> request.getProgramIds().contains(p.getProgramId()));
            selectedUser = userRepository.save(selectedUser);
            for (Long program : request.getProgramIds()) {
                activityProgressRepository.deleteUserActivitiesForProgram(program, selectedUser.getId());
            }
        }
        return selectedUser;
    }

    @Transactional
    public List<ProgramActivityProgress> createActivity(ProgramActivityProgress activityProgress) {

        List<ProgramActivityProgress> savedActivities = new ArrayList<>();
        //delete all the activities of User for the program
        activityProgressRepository.deleteUserActivitiesForProgram(activityProgress.getProgram(), activityProgress.getUser());
        //create activities of User for the program
        for (Long activity : activityProgress.getActivities()) {
            ProgramActivityProgress programActivityProgress = new ProgramActivityProgress(activityProgress.getProgram(), activity,
                    activityProgress.getUser(), activityProgress.isComplete());
            savedActivities.add(activityProgressRepository.save(programActivityProgress));
        }

        addRewards(activityProgress);
        return savedActivities;
    }

    private void addRewards(ProgramActivityProgress activityProgress) {
        //Check if all activities are done by User and add Rewards.
        Optional<Program> program = getProgramById(activityProgress.getProgram());
        if(program.get().getActivities().size() == activityProgress.getActivities().size()) {
            Optional<User> user = userRepository.findById(activityProgress.getUser());
            if(user.isPresent()) {
                User selectedUser = user.get();
                int programsRegistered = selectedUser.getPrograms().size();
                if(programsRegistered > 0) {
                    List<Reward> rewardsList = rewardRepository.findAll();
                    switch (programsRegistered) {
                        case 1:
                            selectedUser.getRewards().add(rewardsList.get(0));
                            break;
                        case 2:
                            selectedUser.getRewards().add(rewardsList.get(1));
                            break;
                        case 3:
                            selectedUser.getRewards().add(rewardsList.get(2));
                            break;
                        case 4:
                            selectedUser.getRewards().add(rewardsList.get(3));
                            break;
                    }
                    userRepository.save(selectedUser);
                }
            }
        }
    }

    public List<Long> getUserActivities(ProgramActivityProgress activityProgress) {
        List<ProgramActivityProgress> activityProgressList = activityProgressRepository.getUserActivitiesForProgram(activityProgress.getProgram(), activityProgress.getUser());
        return activityProgressList.stream().map(p -> p.getActivity()).collect(Collectors.toList());
    }
}
