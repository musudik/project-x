package com.optum.portal.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "Answer", uniqueConstraints={@UniqueConstraint(columnNames = {"answer_id", "answer"})})
@SequenceGenerator(name="seq_answer", initialValue=1000, allocationSize=1)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Answer extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    protected Answer() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_answer")
    @Column(name = "answer_id", nullable = false, insertable = false, updatable = false)
    Long answerId;

    @NotEmpty(message = "answer can't be empty")
    @Column(name = "answer", nullable = false, insertable = false, updatable = false)
    private String answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Question question;

    @Column(name = "program_id")
    private Long recommendedProgram;

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Long getRecommendedProgram() {
        return recommendedProgram;
    }

    public void setRecommendedProgram(Long recommendedProgram) {
        this.recommendedProgram = recommendedProgram;
    }
}