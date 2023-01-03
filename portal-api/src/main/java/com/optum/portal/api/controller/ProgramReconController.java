package com.optum.portal.api.controller;

import com.optum.portal.api.model.NextQuestionRequest;
import com.optum.portal.api.model.Question;
import com.optum.portal.api.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/programRecon")
public class ProgramReconController {

    @Autowired
    private QuestionnaireService questionnaireService;

    /**
     *
     * @param nextQuestionRequest
     * @return
     */
    @PostMapping("nextQuestion")
    public ResponseEntity<Question> getNextQuestion(@RequestBody NextQuestionRequest nextQuestionRequest) {
        try {
            System.out.println("questionId:" +nextQuestionRequest.getQuestionId() +"/n Level:" +nextQuestionRequest.getLevel()+" /n Category:" +nextQuestionRequest.getCategory());
            Question question = null;
            long questionId = 0;
            if(nextQuestionRequest.getQuestionId() == null) {
                questionId = 1;
            } else {
                questionId = Long.valueOf(nextQuestionRequest.getQuestionId()) + 1;
            }
            if(nextQuestionRequest.getLevel() == 0) {
                Optional<Question> question1 = questionnaireService.getQuestionsAndAnswers(questionId);
                if(question1.isPresent()) {
                    question = question1.get();
                }
            } else {
                long levelId = nextQuestionRequest.getLevel();
                /*if (nextQuestionRequest.getLevel() == 1) {
                    levelId = nextQuestionRequest.getLevel();
                } else {
                    levelId = nextQuestionRequest.getLevel() + 1;
                }*/
                question = questionnaireService.getQuestionsAndAnswers(nextQuestionRequest.getCategory(), levelId);
            }
            return new ResponseEntity<>(question, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param userQuestionnaireRequest
     * @return
     */
//    @PostMapping("saveQuestionnaire")
//    public ResponseEntity<UserQuestionnaire> saveQuestionnaire(@RequestBody UserQuestionnaireRequest userQuestionnaireRequest) {
//        try {
//            UserQuestionnaire userQuestionnaire = questionnaireService.saveUserQuestionnaire(userQuestionnaireRequest);
//            return new ResponseEntity<>(userQuestionnaire, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
