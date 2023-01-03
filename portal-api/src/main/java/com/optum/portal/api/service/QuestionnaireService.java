package com.optum.portal.api.service;

import com.optum.portal.api.model.*;
import com.optum.portal.api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionnaireService {

    @Autowired
    private IQuestionnaireRepository questionnaireRepository;

    @Autowired
    private IQuestionRepository questionRepository;

    @Autowired
    private IAnswerRepository answerRepository;

    @Autowired
    private IUserRepository userRepository;

//    @Autowired
//    private IUserQuestionnaireRepository iUserQuestionnaireRepository;

    /**
     * save questionnaire
     * @param questionnaire
     * @return
     */
    public Questionnaire save(Questionnaire questionnaire) {
        if(questionnaire.getQuestionnaireId() != null) {
            questionnaire.setUpdatedDate(LocalDate.now());
            questionnaire.setUpdatedBy("System");
        } else {
            questionnaire.setCreatedDate(LocalDate.now());
            questionnaire.setCreatedBy("System");
        }
        return questionnaireRepository.save(questionnaire);
    }

    /**
     * list all Questionnaire
     * @return
     */
    public List<Questionnaire> listQuestionnaires() { return questionnaireRepository.findAll(); }

//    /**
//     * save userQuestionnaire
//     * @param userQuestionnaireRequest
//     * @return
//     */
//    public UserQuestionnaire saveUserQuestionnaire(UserQuestionnaireRequest userQuestionnaireRequest) {
//        try {
//            Optional<User> user = userRepository.findById(userQuestionnaireRequest.getUserId());
//            Optional<Question> question = questionRepository.findById(userQuestionnaireRequest.getQuestionId());
//            Optional<Answer> answer = answerRepository.findById(userQuestionnaireRequest.getAnswerId());
//
//            UserQuestionnaire userQuestionnaire = new UserQuestionnaire();
//            userQuestionnaire.setUser(user.get());
//            userQuestionnaire.setQuestion(question.get());
//            userQuestionnaire.setAnswer(answer.get());
//            userQuestionnaire.setAnswerText(userQuestionnaireRequest.getAnswer());
//
//            if (userQuestionnaire.getUserQuestionnaireId() != null) {
//                userQuestionnaire.setUpdatedDate(new Date());
//                userQuestionnaire.setUpdatedBy("System");
//            } else {
//                userQuestionnaire.setCreatedDate(new Date());
//                userQuestionnaire.setCreatedBy("System");
//            }
//            return iUserQuestionnaireRepository.save(userQuestionnaire);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    /**
     *
     * @param category
     * @param level
     * @return
     */
    public Question getQuestionsAndAnswers(String category, long level) {
        return questionRepository.findQuestionByLevelAndCategory(category, level);
    }

    /**
     *
     * @param id
     * @return
     */
    public Optional<Question> getQuestionsAndAnswers(long id) {
        return questionRepository.findById(id);
    }
}
