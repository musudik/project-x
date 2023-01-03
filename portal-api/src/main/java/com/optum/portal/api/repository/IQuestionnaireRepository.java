package com.optum.portal.api.repository;

import com.optum.portal.api.model.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IQuestionnaireRepository extends JpaRepository<Questionnaire, Long> {
}