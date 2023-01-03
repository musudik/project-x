package com.optum.portal.api.repository;

import com.optum.portal.api.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAnswerRepository extends JpaRepository<Answer, Long> {
}
