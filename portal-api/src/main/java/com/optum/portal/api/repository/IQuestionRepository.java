package com.optum.portal.api.repository;

import com.optum.portal.api.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IQuestionRepository extends JpaRepository<Question, Long> {

    @Query("Select q from Question q WHERE q.category=:category and q.questionLevel=:questionLevel")
    Question findQuestionByLevelAndCategory(@Param("category") String category, @Param("questionLevel") long questionLevel);
}
