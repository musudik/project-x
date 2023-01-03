//package com.optum.portal.api.model;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotEmpty;
//import java.io.Serializable;
//
//@Entity
//@Table(name = "UserQuestionnaire")
//@SequenceGenerator(name="seq_user_questionnaire_id", initialValue=1000, allocationSize=1)
//public class UserQuestionnaire extends BaseEntity implements Serializable {
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_user_questionnaire_id")
//    @Column(name = "user_questionnaire_id", nullable = false, insertable = false, updatable = false)
//    Long userQuestionnaireId;
//
//    @OneToOne()
//    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
//    private User user;
//
//    @OneToOne()
//    @JoinColumn(name = "question_id", referencedColumnName = "question_id")
//    private Question question;
//
//    @OneToOne()
//    @JoinColumn(name = "answer_id", referencedColumnName = "answer_id")
//    private Answer answer;
//
//    @Column(name = "answer_text")
//    private String answerText;
//
//    public Long getUserQuestionnaireId() {
//        return userQuestionnaireId;
//    }
//
//    public void setUserQuestionnaireId(Long userQuestionnaireId) {
//        this.userQuestionnaireId = userQuestionnaireId;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public Question getQuestion() {
//        return question;
//    }
//
//    public void setQuestion(Question question) {
//        this.question = question;
//    }
//
//    public Answer getAnswer() {
//        return answer;
//    }
//
//    public void setAnswer(Answer answer) {
//        this.answer = answer;
//    }
//
//    public String getAnswerText() {
//        return answerText;
//    }
//
//    public void setAnswerText(String answerText) {
//        this.answerText = answerText;
//    }
//}
