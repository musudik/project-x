package com.optum.portal.api.controller;

import com.optum.portal.api.model.*;
import com.optum.portal.api.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/program")
public class ProgramController {

    @Autowired
    private ProgramService programService;

    /**
     *
     * @param program
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<Result> create(@RequestBody Program program) {
        Result result = new Result();
        try {
            Program newProgram = new Program(program.getProgram_name(), program.getDescription());
            newProgram = programService.save(newProgram);
            result.setResult(Result.SUCCESS);
            result.setMessage("Program creation successful : " + newProgram.getProgram_name());
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            result.setResult(Result.FAILED);
            result.setMessage("Program creation failed : " + program.getProgram_name()+ "." +
                    " Reason: The program given already exists, please try another program name");
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            result.setResult(Result.FAILED);
            result.setMessage("Program creation failed: " + program.getProgram_name()+ "." +
                    " Reason: "+e.getCause());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Program>> getPrograms() {
        try {
            List<Program> programs = programService.listPrograms();
            if (programs == null) {
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.CREATED);
            }
            return new ResponseEntity<>(programs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param name
     * @return
     */
    @GetMapping("/{name}")
    public ResponseEntity<Program> getProgramByName(@PathVariable("name") String name) {
        try {
            Program program = programService.getProgramByName(name);
            return new ResponseEntity<>(program, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param programId
     * @return
     */
    @GetMapping("/getProgramById/{programId}")
    public ResponseEntity<Program> getProgramById(@PathVariable("programId") String programId) {
        try {
            Program program = programService.getProgramById(Long.valueOf(programId)).get();
            return new ResponseEntity<>(program, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param enrollProgramRequest
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<Result> registerProgram(@RequestBody EnrollProgramRequest enrollProgramRequest) {
        Result result = new Result();
        try {
            User user = programService.registerProgram(enrollProgramRequest);
            result.setResult(Result.SUCCESS);
            result.setMessage("Program(s) registered successful for " + user.getUsername());
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.setResult(Result.FAILED);
            result.setMessage("Program(s) registered failed Reason: "+e.getCause());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param enrollProgramRequest
     * @return
     */
    @PostMapping("/deregister")
    public ResponseEntity<Result> deregisterProgram(@RequestBody EnrollProgramRequest enrollProgramRequest) {
        Result result = new Result();
        try {
            User user = programService.deregisterProgram(enrollProgramRequest);
            result.setResult(Result.SUCCESS);
            result.setMessage("Program(s) deregistered successful for " + user.getUsername());
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.setResult(Result.FAILED);
            result.setMessage("Program(s) deregistered failed Reason: "+e.getCause());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param activityProgress
     * @return
     */
    @PostMapping("/createActivity")
    public ResponseEntity<Result> createActivity(@RequestBody ProgramActivityProgress activityProgress) {
        Result result = new Result();
        try {
            List<ProgramActivityProgress> activities = programService.createActivity(activityProgress);
            result.setResult(Result.SUCCESS);
            result.setOutput(activities);
            result.setMessage("Activities created successful for " + activityProgress.getActivities());
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.setResult(Result.FAILED);
            result.setMessage("Activities created failed, Reason: "+e.getCause());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param activityProgress
     * @return
     */
    @PostMapping("/getUserActivities")
    public ResponseEntity<Result> getUserActivities(@RequestBody ProgramActivityProgress activityProgress) {
        Result result = new Result();
        try {
            List<Long> activities = programService.getUserActivities(activityProgress);
            result.setResult(Result.SUCCESS);
            result.setOutput(activities);
            result.setMessage("Activities for user" + activityProgress.getActivities());
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.setResult(Result.FAILED);
            result.setMessage("Activities fetch failed, Reason: "+e.getCause());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
