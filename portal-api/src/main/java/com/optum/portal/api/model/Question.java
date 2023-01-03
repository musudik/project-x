package com.optum.portal.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;


@Entity
@Table(name = "question", uniqueConstraints={@UniqueConstraint(columnNames = {"question", "category", "question_level"})})
@SequenceGenerator(name="seq_question", initialValue=1000, allocationSize=1)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Question extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    protected Question() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_question")
    @Column(name = "question_id", nullable = false, insertable = false, updatable = false)
    Long questionId;

    @NotEmpty(message = "question can't be empty")
    @Column(name = "question", nullable = false)
    private String question;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id",referencedColumnName="question_id")
    private List<Answer> answers = new ArrayList<>();

    @Column(name = "category")
    private String category;

    @Column(name = "question_level")
    private Long questionLevel;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getQuestionLevel() {
        return questionLevel;
    }

    public void setQuestionLevel(Long questionLevel) {
        this.questionLevel = questionLevel;
    }
}