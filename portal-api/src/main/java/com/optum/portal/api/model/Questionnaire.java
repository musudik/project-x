package com.optum.portal.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "questionnaire", uniqueConstraints={@UniqueConstraint(columnNames = {"question"})})
@SequenceGenerator(name="seq_questionnaire", initialValue=1000, allocationSize=1)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Questionnaire extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	protected Questionnaire() {
	}
	public Questionnaire(String question, String answer) {
		super();
		this.question = question;
		this.answer = answer;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="seq_Questionnaire")
	@Column(name = "questionnaire_id", nullable = false, insertable = false, updatable = false)
	Long questionnaireId;

	@NotEmpty(message = "question can't be empty")
	@Column(name = "question", nullable = false)
    private String question;

	@Column(name = "answer")
	private String answer;

	public Long getQuestionnaireId() {
		return questionnaireId;
	}

	public void setQuestionnaireId(Long questionnaireId) {
		this.questionnaireId = questionnaireId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
